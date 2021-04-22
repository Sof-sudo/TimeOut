package dk.au.mad21spring.appproject.group21;

import java.io.Serializable;

public class Team implements Serializable {

    public String TeamName;
    public String CityName;


    // add some more as we go.
    public Team(String TeamName, String CityName){
        this.TeamName = TeamName;
        this.CityName = CityName;
    }
}
