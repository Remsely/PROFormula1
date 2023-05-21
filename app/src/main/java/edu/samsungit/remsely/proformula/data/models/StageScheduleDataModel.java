package edu.samsungit.remsely.proformula.data.models;

import androidx.annotation.NonNull;

public class StageScheduleDataModel {
    private final String date;
    private final String event;

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

    @NonNull
    @Override
    public String toString() {
        return "StageScheduleDataModel{" +
                "date='" + date + '\'' +
                ", event='" + event + '\'' +
                '}';
    }
}
