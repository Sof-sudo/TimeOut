package dk.au.mad21spring.appproject.group21.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dk.au.mad21spring.appproject.group21.Database.Player;
import dk.au.mad21spring.appproject.group21.Database.Team;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM player")
    LiveData<List<Player>> getAllPlayers();

    @Query("SELECT * FROM player WHERE firstname LIKE :firstname AND " + "lastname LIKE :lastname LIMIT 1")
    Player findPlayer(String firstname, String lastname);

    @Update
    void updatePlayer(Player player);
}
