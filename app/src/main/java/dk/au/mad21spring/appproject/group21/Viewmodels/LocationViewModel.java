package dk.au.mad21spring.appproject.group21.Viewmodels;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import dk.au.mad21spring.appproject.group21.Location_API.Coord;
import dk.au.mad21spring.appproject.group21.Repository;
import dk.au.mad21spring.appproject.group21.VolleyCallback;

public class LocationViewModel extends ViewModel {
    private Repository repository;


    public LocationViewModel(Application app){
        repository = Repository.getInstance(app);
    }

    public void getLongLat(String cityName, VolleyCallback callback)
    {
        repository.getLongLat(cityName, callback);
    }
}
