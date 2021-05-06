package dk.au.mad21spring.appproject.group21.Factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dk.au.mad21spring.appproject.group21.Viewmodels.TeamViewModel;

public class TeamViewModelFactory implements ViewModelProvider.Factory {

    private Application app;

    public TeamViewModelFactory(Application app){
        this.app = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TeamViewModel(app);
    }
}
