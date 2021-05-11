package dk.au.mad21spring.appproject.group21.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import dk.au.mad21spring.appproject.group21.Teams_API.BasketBallTeamsAPI;
import dk.au.mad21spring.appproject.group21.Teams_API.BasketballPlayerAPI;

@Entity
public class Player {

    @PrimaryKey
    private int playerID;
    private String firstname;
    private String lastname;
    private String position;
    private double height_feet;
    private double height_inches;
    private double weight_pounds;
    private String teamname;

    public Player(int playerID, String firstname, String lastname, String position, double height_feet, double height_inches,
                  double weight_pounds, String teamname){
        this.playerID = playerID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
        this.height_feet = height_feet;
        this.height_inches = height_inches;
        this.weight_pounds = weight_pounds;
        this.teamname = teamname;
    }

    public int getPlayerID(){return playerID;}
    public void setPlayerID(int playerID){this.playerID = playerID;}

    public String getFirstname(){return firstname;}
    public void setFirstname(String firstname){this.firstname = firstname;}

    public String getLastname(){return lastname;}
    public void setLastname(String lastname){this.lastname = lastname;}

    public String getPosition(){return position;}
    public void setPosition(String position){this.position = position;}

    public double getHeight_feet(){return height_feet;}
    public void setHeight_feet(double height_feet){this.height_feet = height_feet;}

    public double getHeight_inches(){return height_inches;}
    public void setHeight_inches(double height_inches){this.height_inches = height_inches;}

    public double getWeight_pounds(){return weight_pounds;}
    public void setWeight_pounds(double weight_pounds){this.weight_pounds = weight_pounds;}

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }
}
