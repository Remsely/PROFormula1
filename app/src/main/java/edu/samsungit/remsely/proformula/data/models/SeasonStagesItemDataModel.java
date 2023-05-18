package edu.samsungit.remsely.proformula.data.models;

public class SeasonStagesItemDataModel {
    private String seasonsKey;
    private int stageNumber;
    private String pilotFlag;
    private String pilotName;
    private StageHeadingDataModel stageHeadingDataModel;
    private boolean readiness;

    public SeasonStagesItemDataModel(StageHeadingDataModel stageHeadingDataModel, int stageNumber,
                                     String pilotFlag, String pilotName, boolean readiness,
                                     String seasonsKey){
        this.pilotFlag = pilotFlag;
        this.stageNumber = stageNumber;
        this.stageHeadingDataModel = stageHeadingDataModel;
        this.seasonsKey = seasonsKey;
        this.pilotName = pilotName;
        this.readiness = readiness;
    }

    public String getPilotName() {
        return pilotName;
    }

    public String getPilotFlag() {
        return pilotFlag;
    }

    public StageHeadingDataModel getStageHeadingDataModel() {
        return stageHeadingDataModel;
    }

    public String getSeasonsKey() {
        return seasonsKey;
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public boolean getReadiness(){
        return readiness;
    }
}
