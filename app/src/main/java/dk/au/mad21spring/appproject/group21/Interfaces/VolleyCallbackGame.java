package dk.au.mad21spring.appproject.group21.Interfaces;

import dk.au.mad21spring.appproject.group21.Database.Game;

public interface VolleyCallbackGame {
    void onSuccesGame(Game result);
    void onErrorGame();
}
