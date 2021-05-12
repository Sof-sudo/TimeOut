package dk.au.mad21spring.appproject.group21.Viewmodels;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dk.au.mad21spring.appproject.group21.Database.Player;
import dk.au.mad21spring.appproject.group21.Database.Team;
import dk.au.mad21spring.appproject.group21.Location_API.Coord;
import dk.au.mad21spring.appproject.group21.Repository;
import dk.au.mad21spring.appproject.group21.VolleyCallback;

public class PlayerViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<Team> team;
    private MutableLiveData<Player> player;
    private Application app;

    public PlayerViewModel(Application app) {
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

    VolleyCallback callback = new VolleyCallback() {
        @Override
        public void onSucces(Coord result) {

        }

        @Override
        public void onError(Coord result) {

        }

        @Override
        public void onSuccesPlayer(Player result) {
            if (result.getTeamname().equals(team.getValue().getName()))
            {
                player.setValue(result);
            }
            else {makeToast();}
        }

        @Override
        public void onErrorPlayer(Player result) {

        }
    };

    public MutableLiveData<Player> getPlayer()
    {
        if (player == null)
        {
            player = new MutableLiveData<Player>();
        }

        return player;
    }

    public void loadPlayer(String name){
        repository.loadPlayer(name, callback);
    }

    private void makeToast()
    {
        Toast.makeText(app, "Player does not play for this team", Toast.LENGTH_SHORT).show();
    }
}
