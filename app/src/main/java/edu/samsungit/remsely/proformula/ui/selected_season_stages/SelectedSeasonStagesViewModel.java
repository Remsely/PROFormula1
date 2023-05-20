package edu.samsungit.remsely.proformula.ui.selected_season_stages;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.SeasonStagesItemDataModel;
import edu.samsungit.remsely.proformula.data.repositories.SelectedSeasonsStagesRepository;

public class SelectedSeasonStagesViewModel extends ViewModel {
    private MutableLiveData<List<SeasonStagesItemDataModel>> seasonStagesLiveData;
    private final SelectedSeasonsStagesRepository selectedSeasonsStagesRepository;

    public SelectedSeasonStagesViewModel(){
        selectedSeasonsStagesRepository = new SelectedSeasonsStagesRepository();
    }

    public void setSeasonStagesLiveData(String seasonKey){
        seasonStagesLiveData = selectedSeasonsStagesRepository.getSeasonStagesLiveData(seasonKey);
    }
    public MutableLiveData<List<SeasonStagesItemDataModel>> getSeasonStagesLiveData() {
        return seasonStagesLiveData;
    }
}