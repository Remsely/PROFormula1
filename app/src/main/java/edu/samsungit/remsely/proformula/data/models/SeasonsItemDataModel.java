package edu.samsungit.remsely.proformula.data.models;

public class SeasonsItemDataModel {
    private final String seasonNumber;
    private final String pilotName;
    private final String pilotFlag;
    private final String teamName;
    private final String teamLogo;
    private final String previewImage;

    public SeasonsItemDataModel(String seasonNumber, String pilotName, String pilotFlag,
                                String teamName, String teamLogo, String previewImage){
        this.pilotFlag = pilotFlag;
        this.seasonNumber = seasonNumber;
        this.teamLogo = teamLogo;
        this.previewImage = previewImage;
        this.teamName = teamName;
        this.pilotName = pilotName;
    }

    public String getPilotFlag() {
        return pilotFlag;
    }

    public String getPilotName() {
        return pilotName;
    }

    public String getPreviewImage() {
        return previewImage;
    }

    public String getSeasonNumber() {
        return seasonNumber;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public String getTeamName() {
        return teamName;
    }
}
