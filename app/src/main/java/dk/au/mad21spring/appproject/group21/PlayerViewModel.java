package dk.au.mad21spring.appproject.group21;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dk.au.mad21spring.appproject.group21.Database.Player;

public class PlayerViewModel extends ViewModel {

    private Repository repository;

    public PlayerViewModel(Application app) {
    repository = Repository.getInstance(app);
    }

    // Når repository er færdig
    public LiveData<List<Player>> getAll() {
       // return repository.LoadPlayers();
        return  null;
    }


}
