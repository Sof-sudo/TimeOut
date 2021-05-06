package dk.au.mad21spring.appproject.group21;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dk.au.mad21spring.appproject.group21.Database.Team;

public class TeamViewModel extends ViewModel
{
    private MutableLiveData<Team> team;
    private Repository repository;

    public TeamViewModel(Application app)
    {
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
