package dk.au.mad21spring.appproject.group21.Interfaces;

import dk.au.mad21spring.appproject.group21.Database.Game;
import dk.au.mad21spring.appproject.group21.Database.Player;
import dk.au.mad21spring.appproject.group21.Location_API.Coord;

public interface VolleyCallbackLocation {
        void onSucces(Coord result);
        void onError(Coord result);
}
