package dk.au.mad21spring.appproject.group21;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import dk.au.mad21spring.appproject.group21.Database.Teams;

public class TeamListViewModel extends ViewModel {

    private Repository repository;

    MutableLiveData<List<Teams>> teams;

    public LiveData<List<Teams>> getTeams() {

        return repository.loadAllTeams();

    }

    public void addTeams(){
        repository.addAllTeams();
    }

}
