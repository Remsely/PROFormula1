package edu.samsungit.remsely.proformula.ui.home;

public class StageScheduleDataModel {
    private String date;
    private String event;

    public StageScheduleDataModel(String date, String event){
        this.date = date;
        this.event = event;
    }

    public String getDate(){
        return date;
    }

    public String getEvent(){
        return event;
    }
}
