package edu.samsungit.remsely.proformula.data.models;

public class RaceResultsDataModel {
    private final String pilot;
    private final String points;
    private final String team;
    private final String time;
    private final int p;
    private final String pilotFlag;
    private final String teamLogo;

    public RaceResultsDataModel(int p, String pilotFlag, String pilot, String teamLogo, String team, String time, String points){
        this.pilot = pilot;
        this.points = points;
        this.team = team;
        this.time = time;
        this.p = p;
        this.pilotFlag = pilotFlag;
        this.teamLogo = teamLogo;
    }

    public String getPilot(){
        return pilot;
    }

    public String getPoints(){
        return points;
    }

    public String getTeam(){
        return team;
    }

    public  String getTime(){
        return time;
    }

    public int getP(){
        return p;
    }

    public String getPilotFlag(){
        return pilotFlag;
    }

    public String getTeamLogo(){
        return teamLogo;
    }
}
