package dk.au.mad21spring.appproject.group21.Database;

import androidx.room.Entity;

@Entity
public class Games {

    private int gameID;
    private String date;
    private int homeTeamScore;
    private int visitorTeamScore;
    private int season;
    private int period;
    private String status;
    private String time;
    private boolean postseason;

    //Teams
    private Teams homeTeam;
    private Teams visitorTeam;

    public Games(int gameID, String date, int homeTeamScore, int visitorTeamScore, int season, int period, String status, String time, boolean postseason, Teams homeTeam, Teams visitorTeam){
        this.gameID = gameID;
        this.date = date;
        this.homeTeamScore = homeTeamScore;
        this.visitorTeamScore = visitorTeamScore;
        this.season = season;
        this.period = period;
        this.status = status;
        this.time = time;
        this.postseason = postseason;
        this.homeTeam = homeTeam; //ER DET ET TEAMS OBJEKT ELLER SKAL MAN HAVE ALLE VARIABLE SOM I TEAMSKLASSEN HERIND?
        this.visitorTeam = visitorTeam;
    }

    public int getGameID(){return gameID;}
    public void setGameID(int gameID){this.gameID = gameID;}

    public String getDate(){return date;}
    public void setDate(String date){this.date = date;}

    public int getHomeTeamScore(){return homeTeamScore;}
    public void setHomeTeamScore(int homeTeamScore){this.homeTeamScore = homeTeamScore;}

    public int getVisitorTeamScore(){return visitorTeamScore;}
    public void setVisitorTeamScore(int visitorTeamScore){this.visitorTeamScore = visitorTeamScore;}

    public int getSeason(){return season;}
    public void setSeason(int season){this.season = season;}

    public int getPeriod(){return period;}
    public void setPeriod(int period){this.period = period;}

    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}

    public String getTime(){return time;}
    public void setTime(String time){this.time = time;}

    public boolean getPostseason(){return postseason;}
    public void setPostseason(boolean postseason){this.postseason = postseason;}

    public Teams getHomeTeam(){return homeTeam;}
    public void setHomeTeam(Teams homeTeam){this.homeTeam = homeTeam;}

    public Teams getVisitorTeam(){return visitorTeam;}
    public void setVisitorTeam(Teams visitorTeam){this.visitorTeam = visitorTeam;}
}
