package dk.au.mad21spring.appproject.group21;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dk.au.mad21spring.appproject.group21.Database.Player;
import dk.au.mad21spring.appproject.group21.Database.Team;

public class PlayerViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<Team> team;

    public PlayerViewModel(Application app) {
    repository = Repository.getInstance(app);
    }

    public LiveData<Team> getTeam(String name)
    {
        if (team == null)
        {
            team = new MutableLiveData<Team>();
        }

        team.setValue(repository.getTeamAsync(name));
        return team;
    }
}
