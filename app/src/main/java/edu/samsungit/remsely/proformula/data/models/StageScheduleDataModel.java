package edu.samsungit.remsely.proformula.data.models;

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

    @Override
    public String toString() {
        return "StageScheduleDataModel{" +
                "date='" + date + '\'' +
                ", event='" + event + '\'' +
                '}';
    }
}
