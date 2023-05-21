package edu.samsungit.remsely.proformula.ui.race_against_time;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.QualificationResultsDataModel;
import edu.samsungit.remsely.proformula.data.repositories.RaceAgainstTimeRepository;

public class RaceAgainstTimeResultsViewModel extends ViewModel {
    private LiveData<List<QualificationResultsDataModel>> qualificationResultsLiveData;
    private final RaceAgainstTimeRepository raceAgainstTimeRepository;

    public RaceAgainstTimeResultsViewModel(){
        raceAgainstTimeRepository = new RaceAgainstTimeRepository();
    }

    public void setQualificationResultsLiveData(String seasonsKey, String stageNumber, String eventNumber) {
        qualificationResultsLiveData = raceAgainstTimeRepository.getRaceAgainstTimeLiveData(
                seasonsKey, stageNumber, eventNumber);
    }

    public LiveData<List<QualificationResultsDataModel>> getQualificationResultsLiveData() {
        return qualificationResultsLiveData;
    }
}