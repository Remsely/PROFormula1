package edu.samsungit.remsely.proformula.data.models;

public class StageHeadingDataModel {
    private String flag;
    private String location;
    private String name;

    public StageHeadingDataModel() {}

    public StageHeadingDataModel(String flag, String location, String name){
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

    @Override
    public String toString() {
        return "StageHeadingDataModel{" +
                "flag='" + flag + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
