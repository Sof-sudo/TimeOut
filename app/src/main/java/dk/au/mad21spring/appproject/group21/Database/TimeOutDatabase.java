package dk.au.mad21spring.appproject.group21.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dk.au.mad21spring.appproject.group21.Database.Dao.TeamDao;

//This code is inspired from lesson 4 and the demo about Persistence
//This class is a Room class, that implements the singleton pattern and creates the TeamDao class

@Database(entities = {Team.class, Player.class, Game.class}, version = 8, exportSchema = false)
public abstract class TimeOutDatabase extends RoomDatabase {

    public abstract TeamDao teamDao();

    private static TimeOutDatabase instance; //singleton - database instance

    public static TimeOutDatabase getDatabase(final Context context){
        if(instance == null){
            synchronized (TimeOutDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            TimeOutDatabase.class,"timeout_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
