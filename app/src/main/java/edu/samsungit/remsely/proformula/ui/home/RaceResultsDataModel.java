package edu.samsungit.remsely.proformula.ui.home;

public class RaceResultsDataModel {
    private String pilot;
    private String points;
    private String team;
    private String time;
    private int p;
    private String pilotFlag;
    private String teamLogo;

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
