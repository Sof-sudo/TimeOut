package dk.au.mad21spring.appproject.group21;

import dk.au.mad21spring.appproject.group21.Database.Player;
import dk.au.mad21spring.appproject.group21.Location_API.Coord;

public interface VolleyCallback {
        void onSucces(Coord result);
        void onError(Coord result);
        void onSuccesPlayer(Player result);
        void onErrorPlayer();
        void onSuccesGame(Game result);
        void onErrorGame(Game result);
}
