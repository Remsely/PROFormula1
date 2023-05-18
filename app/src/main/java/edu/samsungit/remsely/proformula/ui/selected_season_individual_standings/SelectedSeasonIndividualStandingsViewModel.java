package edu.samsungit.remsely.proformula.ui.selected_season_individual_standings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.IndividualStandingsPositionDataModel;
import edu.samsungit.remsely.proformula.data.repositories.SelectedSeasonIndividualStandingsRepository;

public class SelectedSeasonIndividualStandingsViewModel extends ViewModel {
    private MutableLiveData<List<IndividualStandingsPositionDataModel>> individualStandingsLiveData;
    private final SelectedSeasonIndividualStandingsRepository selectedSeasonIndividualStandingsRepository;

    public SelectedSeasonIndividualStandingsViewModel(){
        selectedSeasonIndividualStandingsRepository = new SelectedSeasonIndividualStandingsRepository();
    }

    public void setIndividualStandingsLiveData(String seasonsKey){
        individualStandingsLiveData = selectedSeasonIndividualStandingsRepository
                .getIndividualStandingsLiveData(seasonsKey);
    }

    public MutableLiveData<List<IndividualStandingsPositionDataModel>> getIndividualStandingsLiveData(){
        return individualStandingsLiveData;
    }
}