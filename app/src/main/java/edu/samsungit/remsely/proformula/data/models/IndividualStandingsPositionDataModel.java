package edu.samsungit.remsely.proformula.data.models;

public class IndividualStandingsPositionDataModel {
    private String pilot;
    private String points;
    private String team;
    private int p;
    private String pilotFlag;
    private String teamLogo;

    public IndividualStandingsPositionDataModel(int p, String pilotFlag, String pilot, String teamLogo, String team, String points){
        this.pilot = pilot;
        this.points = points;
        this.team = team;
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
