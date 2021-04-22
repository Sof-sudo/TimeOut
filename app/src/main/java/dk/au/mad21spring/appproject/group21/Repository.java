package dk.au.mad21spring.appproject.group21;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {


    // variables
    private ExecutorService executor;
    private static Repository instance;
    private Application app;


    //contructor
    public Repository(Application app){
        executor = Executors.newSingleThreadExecutor();
        this.app = app;
    }

    // Singleton repository
    public static Repository getInstance(Application app){
        if (instance == null){
            instance = new Repository(app);
        }
        return instance;
    }

    // get all teams and add them to the recyclerview
    public void addAllTeamsFromAPI(){
        String base = "https://www.balldontlie.io/api/v1/teams";

        executor.execute(new Runnable() {
            @Override
            public void run() {
                //sendRequest(base);
            }
        });
    }
}
