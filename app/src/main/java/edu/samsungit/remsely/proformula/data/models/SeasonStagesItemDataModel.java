package edu.samsungit.remsely.proformula.data.models;

public class SeasonStagesItemDataModel {
    private final String seasonsKey;
    private final int stageNumber;
    private final String pilotFlag;
    private final String pilotName;
    private final StageHeadingDataModel stageHeadingDataModel;
    private final boolean readiness;

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
