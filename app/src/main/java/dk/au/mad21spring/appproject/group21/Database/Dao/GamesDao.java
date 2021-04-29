package dk.au.mad21spring.appproject.group21.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import dk.au.mad21spring.appproject.group21.Database.Games;

@Dao
public interface GamesDao {

    @Query("SELECT * FROM games")
    LiveData<List<Games>> getAllGames();

    @Query("SELECT * FROM games WHERE date LIKE :date")
    Games findGames(String date);
}
