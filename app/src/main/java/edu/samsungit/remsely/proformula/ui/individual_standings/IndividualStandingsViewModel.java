package edu.samsungit.remsely.proformula.ui.individual_standings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class IndividualStandingsViewModel extends ViewModel {
    private final MutableLiveData<List<IndividualStandingsPositionDataModel>> individualStandingsLiveData;

    public IndividualStandingsViewModel(){
        IndividualStandingsRepository individualStandingsRepository = new IndividualStandingsRepository();
        individualStandingsLiveData = individualStandingsRepository.getIndividualStandingsLiveData();
    }

    public MutableLiveData<List<IndividualStandingsPositionDataModel>> getIndividualStandingsLiveData(){
        return individualStandingsLiveData;
    }
}