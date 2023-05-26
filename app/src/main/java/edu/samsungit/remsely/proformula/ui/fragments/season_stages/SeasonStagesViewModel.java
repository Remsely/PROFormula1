package edu.samsungit.remsely.proformula.ui.fragments.season_stages;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.SeasonStagesItemDataModel;
import edu.samsungit.remsely.proformula.data.repositories.CurrentSeasonKeyRepository;
import edu.samsungit.remsely.proformula.data.repositories.SelectedSeasonsStagesRepository;

public class SeasonStagesViewModel extends ViewModel {
    private MutableLiveData<List<SeasonStagesItemDataModel>> seasonStagesLiveData;
    private final SelectedSeasonsStagesRepository selectedSeasonsStagesRepository;
    private final LiveData<String> currentSeasonKeyLiveData;

    public SeasonStagesViewModel(){
        selectedSeasonsStagesRepository = new SelectedSeasonsStagesRepository();
        CurrentSeasonKeyRepository currentSeasonKeyRepository = new CurrentSeasonKeyRepository();
        currentSeasonKeyLiveData = currentSeasonKeyRepository.getCurrentSeasonKeyLiveData();
    }

    public void setSeasonStagesLiveData(String seasonKey){
        seasonStagesLiveData = selectedSeasonsStagesRepository.getSeasonStagesLiveData(seasonKey);
    }

    public MutableLiveData<List<SeasonStagesItemDataModel>> getSeasonStagesLiveData() {
        return seasonStagesLiveData;
    }

    public LiveData<String> getCurrentSeasonKeyLiveData() {
        return currentSeasonKeyLiveData;
    }
}