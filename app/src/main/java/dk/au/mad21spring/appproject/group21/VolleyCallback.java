package dk.au.mad21spring.appproject.group21;

import dk.au.mad21spring.appproject.group21.Location_API.Coord;

public interface VolleyCallback {
        void onSucces(Coord result);
        void onError(Coord result);
}
