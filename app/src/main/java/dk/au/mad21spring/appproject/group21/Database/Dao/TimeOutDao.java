package dk.au.mad21spring.appproject.group21.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.List;
import dk.au.mad21spring.appproject.group21.Database.Teams;

@Dao
public interface TimeOutDao {

    //TEAMS
    @Query("SELECT * FROM teams")
    LiveData<List<Teams>> getAllTeams();

    @Query("SELECT * FROM teams WHERE name LIKE :name")
    Teams findTeam(String name);

    @Update
    void updateTeam(Teams team);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTeam(Teams team);
}
