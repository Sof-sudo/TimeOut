package dk.au.mad21spring.appproject.group21.Viewmodels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dk.au.mad21spring.appproject.group21.Database.Team;
import dk.au.mad21spring.appproject.group21.Repository;

public class TeamListViewModel extends ViewModel {

    private Repository repository;

    public TeamListViewModel(Application app){
        repository = Repository.getInstance(app);
    }

    public LiveData<List<Team>> getTeams() {

        return repository.loadAllTeams();

    }

    public void addTeams(){
        repository.addAllTeams();
    }

    public Team searchForTeam(String team){
        return repository.getTeamInDatabase(team);
    }
}
