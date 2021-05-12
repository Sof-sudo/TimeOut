package dk.au.mad21spring.appproject.group21.Interfaces;

import dk.au.mad21spring.appproject.group21.Database.Player;

public interface VolleyCallbackPlayer {
    void onSuccesPlayer(Player result);
    void onErrorPlayer();
}
