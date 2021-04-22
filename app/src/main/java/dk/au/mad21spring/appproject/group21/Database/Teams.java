package dk.au.mad21spring.appproject.group21.Database;

public class Teams {

    private int teamID;
    private String abbreviation;
    private String city;
    private String conference;
    private String division;
    private String fullname;
    private String name;

    public Teams(int teamID, String abbreviation, String city, String conference, String division, String fullname, String name){
        this.teamID = teamID;
        this.abbreviation = abbreviation;
        this.city = city;
        this.conference = conference;
        this.division = division;
        this.fullname = fullname;
        this.name = name;
    }

    public int getTeamID(){return teamID;}
    public void setTeamID(int teamID){this.teamID = teamID;}

    public String getAbb(){return abbreviation;}
    public void setAbb(String abbreviation){this.abbreviation = abbreviation;}

    public String getCity(){return city;}
    public void setCity(String city){this.city = city;}

    public String getConference(){return conference;}
    public void setConference(String conference){this.conference = conference;}

    public String getDivision(){return division;}
    public void setDivision(String division){this.division = division;}

    public String getFullname(){return fullname;}
    public void setFullname(String fullname){this.fullname = fullname;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
}
