package dk.au.mad21spring.appproject.group21.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlayerDao {
    //PLAYERS
    @Query("SELECT * FROM player")
    LiveData<List<Player>> getAllPlayers();

    @Query("SELECT * FROM player WHERE firstname LIKE :firstname AND " + "lastname LIKE :lastname LIMIT 1")
    Player findPlayer(String firstname, String lastname);

    @Update
    void updatePlayer(Player player);
}
