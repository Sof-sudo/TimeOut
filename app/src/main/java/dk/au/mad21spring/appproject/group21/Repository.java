package dk.au.mad21spring.appproject.group21;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dk.au.mad21spring.appproject.group21.Database.Teams;
import dk.au.mad21spring.appproject.group21.Database.TimeOutDatabase;

public class Repository {

    private TimeOutDatabase db;
    private WEP_API wep_api;
    private ExecutorService executor;
    private static Repository instance;
    private Application app;

    //contructor
    public Repository(Application app){
        db = TimeOutDatabase.getDatabase(app.getApplicationContext());
        executor = Executors.newSingleThreadExecutor();
        this.app = app;
        wep_api = new WEP_API(app);
    }

    // Singleton repository - Created with help from Tri.
    public static Repository getInstance(Application app){
        if (instance == null){
            instance = new Repository(app);
        }
        return instance;
    }


    public LiveData<List<Teams>> loadAllTeams(){
        return db.timeOutDao().getAllTeams();
    }

    //add a new team to the database asynch
    public void addTeamAsynch(Teams team){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.timeOutDao().addTeam(team);
            }
        });
    }


///////////////// Get data from API /////////////////////////////////

    public void addAllTeams(){
        wep_api.getAllTeams();
    }
}
