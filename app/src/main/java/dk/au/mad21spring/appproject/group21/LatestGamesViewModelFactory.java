package dk.au.mad21spring.appproject.group21;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public class LatestGamesViewModelFactory implements ViewModelProvider.Factory {

    private Application app;

    public LatestGamesViewModelFactory(Application app) {
        this.app = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LatestGamesViewModel(app);
    }
}
