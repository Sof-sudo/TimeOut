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

import static android.icu.text.MessagePattern.ArgType.SELECT;

@Dao
public interface TimeOutDao {

    //TEAMS
    @Query("SELECT * FROM teams")
    LiveData<List<Teams>> getAllTeams();

    @Query("SELECT * FROM teams WHERE name LIKE :name")
    Teams findTeam(String name);

    @Update
    void updateTeam(Teams team);

    //PLAYERS
    @Query("SELECT * FROM player")
    LiveData<List<Player>> getAllPlayers();

    @Query("SELECT * FROM player WHERE firstname LIKE :firstname AND " + "lastname LIKE :lastname LIMIT 1")
    Player findPlayer(String firstname, String lastname);

    @Update
    void updatePlayer(Player player);

    //GAMES
    @Query("SELECT * FROM games")
    LiveData<List<Games>> getAllGames();

    @Query("SELECT * FROM games WHERE date LIKE :date")
    Games findGames(String date);

    //STATS
    @Query("SELECT * FROM stats")
    LiveData<List<Stats>> getAllStats();
}
