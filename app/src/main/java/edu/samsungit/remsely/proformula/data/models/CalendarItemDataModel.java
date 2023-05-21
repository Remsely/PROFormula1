package edu.samsungit.remsely.proformula.data.models;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CalendarItemDataModel {
    private final StageHeadingDataModel stageHeading;
    private final LiveData<List<StageScheduleDataModel>> stageScheduleList;
    private final int number;

    public CalendarItemDataModel(StageHeadingDataModel stageHeading,
                                 LiveData<List<StageScheduleDataModel>> stageScheduleList, int number){
        this.number = number;
        this.stageHeading = stageHeading;
        this.stageScheduleList = stageScheduleList;
    }

    public int getNumber(){
        return number;
    }

    public LiveData<List<StageScheduleDataModel>> getStageScheduleList(){
        return stageScheduleList;
    }

    public StageHeadingDataModel getStageHeading(){
        return stageHeading;
    }

    @NonNull
    @Override
    public String toString() {
        return "CalendarItemDataModel{" +
                "stageHeading=" + stageHeading +
                ", stageScheduleList=" + stageScheduleList +
                ", number=" + number +
                '}';
    }
}
