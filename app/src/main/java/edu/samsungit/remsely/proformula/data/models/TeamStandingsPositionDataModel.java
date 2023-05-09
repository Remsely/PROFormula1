package edu.samsungit.remsely.proformula.data.models;

public class TeamStandingsPositionDataModel {
    private String points;
    private String teamFullName;
    private int p;
    private String teamLogo;

    public TeamStandingsPositionDataModel(int p, String teamLogo, String teamFullName, String points){
        this.points = points;
        this.teamFullName = teamFullName;
        this.p = p;
        this.teamLogo = teamLogo;
    }

    public String getPoints(){
        return points;
    }

    public String getTeamFullName(){
        return teamFullName;
    }

    public int getP(){
        return p;
    }

    public String getTeamLogo(){
        return teamLogo;
    }
}
