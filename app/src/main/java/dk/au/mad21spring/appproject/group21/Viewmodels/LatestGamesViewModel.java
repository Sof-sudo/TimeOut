package dk.au.mad21spring.appproject.group21.Viewmodels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dk.au.mad21spring.appproject.group21.Database.Team;
import dk.au.mad21spring.appproject.group21.Repository;

public class LatestGamesViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<Team> team;

    public LatestGamesViewModel(Application app){
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
