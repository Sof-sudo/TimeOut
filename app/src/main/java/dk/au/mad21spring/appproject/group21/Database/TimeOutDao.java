package dk.au.mad21spring.appproject.group21.Database;

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

        /*
        @Query("SELECT * FROM teams")
        LiveData<List<Teams>> getAll();

        @Query("SELECT * FROM teams WHERE team LIKE :name")
        Teams findTeam(String name);

        @Update
        void updateTeam(Teams team);*/

    /* MAN SKAL VEL IKKE KUNNE TILFØJE ELLER SLETTE TEAMS?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTeam(Teams team);

    @Delete
    void delete(Teams team);

     */

}
