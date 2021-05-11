package dk.au.mad21spring.appproject.group21;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import dk.au.mad21spring.appproject.group21.Database.Player;
import dk.au.mad21spring.appproject.group21.Database.Team;
import dk.au.mad21spring.appproject.group21.Database.TimeOutDatabase;
import dk.au.mad21spring.appproject.group21.Location_API.Coord;

public class Repository {

    private TimeOutDatabase db;
    private Team_API team_api;
    private Map_API map_api;
    private Player_API player_api;
    private ExecutorService executor;
    private static Repository instance;
    private Application app;
    private LiveData<List<Team>> teamlist;

    //contructor
    public Repository(Application app){
        db = TimeOutDatabase.getDatabase(app.getApplicationContext());
        executor = Executors.newSingleThreadExecutor();
        this.app = app;
        team_api = new Team_API(app, this);
        map_api = new Map_API(app, this);
        player_api = new Player_API(app, this);
        teamlist = db.teamDao().getAllTeams();
    }

    // Singleton repository - Created with help from Tri.
    public static Repository getInstance(Application app){
        if (instance == null){
            instance = new Repository(app);
        }
        return instance;
    }

//    public String

    public LiveData<List<Team>> loadAllTeams(){
        if (teamlist == null){
            return new MutableLiveData<List<Team>>();
        } else  {
            return teamlist;
        }
    }

    //add a new team to the database asynch
    public void addTeamAsynch(Team team){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.teamDao().addTeam(team);
            }
        });
    }

    //find a team in the database by the name
    public Team getTeamAsync(String name)
    {
        Future<Team> team = executor.submit(new Callable<Team>() {
            @Override
            public Team call()
            {
                return db.teamDao().findTeam(name);
            }
        });

        try
        {
            return team.get();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public void addPlayerAsynch(Player player) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.playerDao().addPlayer(player);
            }
        });
    }


///////////////// Get data from API /////////////////////////////////

    public void addAllTeams(){
        team_api.getAllTeams();
    }

    public Coord getLongLat(String cityName) {
        return map_api.getLongLat(cityName);
    }

    public void loadPlayer(String name){
        player_api.getPlayer(name);
    }
}
