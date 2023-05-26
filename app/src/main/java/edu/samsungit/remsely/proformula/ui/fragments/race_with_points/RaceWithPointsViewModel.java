package edu.samsungit.remsely.proformula.ui.fragments.race_with_points;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.RaceResultsDataModel;
import edu.samsungit.remsely.proformula.data.repositories.RaceWithPointsRepository;

public class RaceWithPointsViewModel extends ViewModel {
    private LiveData<List<RaceResultsDataModel>> raceResultsLiveData;
    private final RaceWithPointsRepository raceWithPointsRepository;

    public RaceWithPointsViewModel(){
        raceWithPointsRepository = new RaceWithPointsRepository();
    }

    public void setRaceResultsLiveData(String seasonsKey, String stageNumber, String eventNumber) {
        raceResultsLiveData = raceWithPointsRepository.getRaceWithPointsLiveData(seasonsKey, stageNumber, eventNumber);
    }

    public LiveData<List<RaceResultsDataModel>> getRaceResultsLiveData() {
        return raceResultsLiveData;
    }
}