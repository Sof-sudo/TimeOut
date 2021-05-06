package dk.au.mad21spring.appproject.group21.Viewmodels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dk.au.mad21spring.appproject.group21.Database.Team;
import dk.au.mad21spring.appproject.group21.Repository;
import dk.au.mad21spring.appproject.group21.Teams_API.Coord;

public class LocationViewModel extends ViewModel {
    private Repository repository;

    public LocationViewModel(Application app){
        repository = Repository.getInstance(app);
    }

    public Coord getLongLat(String cityName)
    {
        return repository.getLongLat(cityName);
    }
}
