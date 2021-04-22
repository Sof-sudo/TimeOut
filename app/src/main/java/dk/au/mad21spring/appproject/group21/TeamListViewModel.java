package dk.au.mad21spring.appproject.group21;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dk.au.mad21spring.appproject.group21.Database.Teams;

public class TeamListViewModel extends ViewModel {

    MutableLiveData<ArrayList<Teams>> teams;

    public LiveData<ArrayList<Teams>> getTeams() {
        if(teams == null){
            teams = new MutableLiveData<>();

            ArrayList<Teams> teamList = new ArrayList<Teams>();

            teamList.add(new Teams(1, "LAL", "Los Angeles", "West", "Pacific", "Los Angeles Lakers", "Lakers"));

            teams.setValue(teamList);
        }
        return teams;
    }
}
