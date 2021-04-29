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

import dk.au.mad21spring.appproject.group21.Database.Team;
import dk.au.mad21spring.appproject.group21.Database.TimeOutDatabase;

public class Repository {

    private TimeOutDatabase db;
    private WEP_API wep_api;
    private ExecutorService executor;
    private static Repository instance;
    private Application app;
    private LiveData<List<Team>> teamlist;

    //contructor
    public Repository(Application app){
        db = TimeOutDatabase.getDatabase(app.getApplicationContext());
        executor = Executors.newSingleThreadExecutor();
        this.app = app;
        wep_api = new WEP_API(app, this);
        teamlist = db.teamDao().getAllTeams();
    }

    // Singleton repository - Created with help from Tri.
    public static Repository getInstance(Application app){
        if (instance == null){
            instance = new Repository(app);
        }
        return instance;
    }


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

///////////////// Get data from API /////////////////////////////////

    public void addAllTeams(){
        wep_api.getAllTeams();
    }
}
