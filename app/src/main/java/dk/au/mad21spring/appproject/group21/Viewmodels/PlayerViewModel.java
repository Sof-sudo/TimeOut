package dk.au.mad21spring.appproject.group21.Viewmodels;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dk.au.mad21spring.appproject.group21.Database.Player;
import dk.au.mad21spring.appproject.group21.Database.Team;
import dk.au.mad21spring.appproject.group21.R;
import dk.au.mad21spring.appproject.group21.Repository;
import dk.au.mad21spring.appproject.group21.Interfaces.VolleyCallbackPlayer;

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

    VolleyCallbackPlayer callback = new VolleyCallbackPlayer() {
        @Override
        public void onSuccesPlayer(Player result) {
            if (result.getTeamname().equals(team.getValue().getName()))
            {
                player.setValue(result);
            }
            else {makeToast();}
        }

        @Override
        public void onErrorPlayer() {
            Toast.makeText(app, R.string.Toast_Player, Toast.LENGTH_SHORT).show();
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
