package dk.au.mad21spring.appproject.group21.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.List;

import dk.au.mad21spring.appproject.group21.Database.Team;

@Dao
public interface TeamDao {

    @Query("SELECT * FROM Team")
    LiveData<List<Team>> getAllTeams();

    /*
    @Query("SELECT * FROM Team WHERE name LIKE :name")
    Team findTeam(String name);*/

    @Query("SELECT * FROM Team WHERE name LIKE :name OR "
            + "fullname LIKE :name OR " + "city LIKE :name")
    Team findTeam(String name);

    @Update
    void updateTeam(Team team);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTeam(Team team);
}
