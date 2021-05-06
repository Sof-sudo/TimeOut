package dk.au.mad21spring.appproject.group21.Factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dk.au.mad21spring.appproject.group21.Viewmodels.PlayerViewModel;

public class PlayerViewModelFactory implements ViewModelProvider.Factory{

    private Application app;

    public PlayerViewModelFactory(Application app){
        this.app = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlayerViewModel(app);
    }
}
