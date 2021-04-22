package dk.au.mad21spring.appproject.group21;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class TeamListViewModel extends ViewModel {

    MutableLiveData<ArrayList<Team>> teams;

    public LiveData<ArrayList<Team>> getTeams() {
        if(teams == null){
            teams = new MutableLiveData<>();

            ArrayList<Team> teamList = new ArrayList<Team>();

            teamList.add(new Team("Los Angeles Lakers", "Los Angeles"));
            teamList.add(new Team("Boston Celtics", "Boston"));
            teamList.add(new Team("Atlanta Hawks", "Atlanta"));

            teams.setValue(teamList);
        }
        return teams;
    }
}