package edu.samsungit.remsely.proformula.data.models;

public class GrandPrixResultsTabLayoutHeadingDataModel {
    private final boolean isRace;
    private final String name;

    public GrandPrixResultsTabLayoutHeadingDataModel(boolean isRace, String name){
        this.isRace = isRace;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean getIsRace(){
        return isRace;
    }
}
