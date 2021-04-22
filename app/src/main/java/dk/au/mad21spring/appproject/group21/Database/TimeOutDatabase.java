package dk.au.mad21spring.appproject.group21.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//This code is inspired from lesson 4 and the demo about Persistence
//This class is a Room class, that implements the singleton pattern and creates the TeamDao class

@Database(entities = {Teams.class}, version = 1)
public abstract class TimeOutDatabase extends RoomDatabase {

    public abstract TimeOutDao teamDao();

    private static TimeOutDatabase instance; //singleton - database instance

    public static TimeOutDatabase getDatabase(final Context context){
        if(instance == null){
            synchronized (TimeOutDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            TimeOutDatabase.class,"team_database")
                            .build();
                }
            }
        }
        return instance;
    }
}
