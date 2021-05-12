package dk.au.mad21spring.appproject.group21.Viewmodels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dk.au.mad21spring.appproject.group21.Database.Game;
import dk.au.mad21spring.appproject.group21.Database.Team;
import dk.au.mad21spring.appproject.group21.Repository;
import dk.au.mad21spring.appproject.group21.Interfaces.VolleyCallbackGame;

public class LatestGamesViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<Team> team;
    private MutableLiveData<Game> game;

    public LatestGamesViewModel(Application app){
        repository = Repository.getInstance(app);
    }

    public LiveData<Team> getTeam(String name)
    {
        if (team == null)
        {
            team = new MutableLiveData<Team>();
        }

        team.setValue(repository.getTeamInDatabase(name));
        return team;

    }

    VolleyCallbackGame callback = new VolleyCallbackGame() {
        @Override
        public void onSuccesGame(Game result) {
            game.setValue(result);
        }

        @Override
        public void onErrorGame(Game result) {

        }
    };

    public MutableLiveData<Game> getGame()
    {
        if (game == null)
        {
            game = new MutableLiveData<Game>();
        }

        return game;
    }

    public void loadGame(int teamID, int season, String date){
        repository.loadGame(teamID, season, date, callback);
    }

}
