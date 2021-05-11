package dk.au.mad21spring.appproject.group21.Viewmodels;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import dk.au.mad21spring.appproject.group21.Location_API.Coord;
import dk.au.mad21spring.appproject.group21.Repository;

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
