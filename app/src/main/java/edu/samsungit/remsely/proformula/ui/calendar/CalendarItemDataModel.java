package edu.samsungit.remsely.proformula.ui.calendar;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.StageHeadingDataModel;
import edu.samsungit.remsely.proformula.data.models.StageScheduleDataModel;

public class CalendarItemDataModel {
    private StageHeadingDataModel stageHeading;
    private List<StageScheduleDataModel> stageScheduleList;
    private int number;

    CalendarItemDataModel(StageHeadingDataModel stageHeading,
                          List<StageScheduleDataModel> stageScheduleList, int number){
        this.number = number;
        this.stageHeading = stageHeading;
        this.stageScheduleList = stageScheduleList;
    }

    public int getNumber(){
        return number;
    }

    public List<StageScheduleDataModel> getStageScheduleList(){
        return stageScheduleList;
    }

    public StageHeadingDataModel getStageHeading(){
        return stageHeading;
    }

    @Override
    public String toString() {
        return "CalendarItemDataModel{" +
                "stageHeading=" + stageHeading +
                ", stageScheduleList=" + stageScheduleList +
                ", number=" + number +
                '}';
    }
}
