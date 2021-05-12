package dk.au.mad21spring.appproject.group21.Viewmodels;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dk.au.mad21spring.appproject.group21.Database.Game;
import dk.au.mad21spring.appproject.group21.Database.Team;
import dk.au.mad21spring.appproject.group21.R;
import dk.au.mad21spring.appproject.group21.Repository;
import dk.au.mad21spring.appproject.group21.Interfaces.VolleyCallbackGame;

public class LatestGamesViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<Team> team;
    private MutableLiveData<Game> game;
    private Application app;

    public LatestGamesViewModel(Application app){
        this.app = app;
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
        public void onErrorGame() {
            Toast.makeText(app, R.string.Toast_Latestgames, Toast.LENGTH_SHORT).show();
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

    public void loadGame(int teamID, String date){
        repository.loadGame(teamID, date, callback);
    }

}
