package edu.samsungit.remsely.proformula.ui.home;

public class SoonStageHeadingDataModel {
    private String flag;
    private String location;
    private String name;

    public SoonStageHeadingDataModel() {}

    public SoonStageHeadingDataModel(String flag, String location, String name){
        this.flag = flag;
        this.location = location;
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}
