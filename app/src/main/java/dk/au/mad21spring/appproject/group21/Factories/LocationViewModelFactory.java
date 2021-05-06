package dk.au.mad21spring.appproject.group21.Factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dk.au.mad21spring.appproject.group21.Viewmodels.LocationViewModel;

public class LocationViewModelFactory implements ViewModelProvider.Factory {
    private Application app;
    public LocationViewModelFactory(Application app) {
        this.app = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LocationViewModel(app);
    }
}
