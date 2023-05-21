package edu.samsungit.remsely.proformula.data.models;

public class TeamStandingsPositionDataModel {
    private final String points;
    private final String teamFullName;
    private final int p;
    private final String teamLogo;

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
