
package dk.au.mad21spring.appproject.group21.Players_API;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import dk.au.mad21spring.appproject.group21.Database.Team;


public class BasketballPlayersAPI {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("height_feet")
    @Expose
    private Integer heightFeet;
    @SerializedName("height_inches")
    @Expose
    private Integer heightInches;
    @SerializedName("weight_pounds")
    @Expose
    private Integer weightPounds;
    @SerializedName("team")
    @Expose
    private Team team;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getHeightFeet() {
        return heightFeet;
    }

    public void setHeightFeet(Integer heightFeet) {
        this.heightFeet = heightFeet;
    }

    public Integer getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(Integer heightInches) {
        this.heightInches = heightInches;
    }

    public Integer getWeightPounds() {
        return weightPounds;
    }

    public void setWeightPounds(Integer weightPounds) {
        this.weightPounds = weightPounds;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
