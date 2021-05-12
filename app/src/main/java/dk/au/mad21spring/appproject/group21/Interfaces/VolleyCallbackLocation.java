package dk.au.mad21spring.appproject.group21.Interfaces;

import dk.au.mad21spring.appproject.group21.Location_API_Classes.Coord;

public interface VolleyCallbackLocation {
        void onSucces(Coord result);
        void onError();
}
