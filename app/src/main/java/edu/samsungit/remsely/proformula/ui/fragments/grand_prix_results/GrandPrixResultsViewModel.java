package edu.samsungit.remsely.proformula.ui.fragments.grand_prix_results;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.GrandPrixResultsTabLayoutHeadingDataModel;
import edu.samsungit.remsely.proformula.data.repositories.GrandPrixResultsTabLayoutHeadingsRepository;

public class GrandPrixResultsViewModel extends ViewModel {
    private LiveData<List<GrandPrixResultsTabLayoutHeadingDataModel>> headingsLiveData;
    private final GrandPrixResultsTabLayoutHeadingsRepository grandPrixResultsTabLayoutHeadingsRepository;

    public GrandPrixResultsViewModel(){
        grandPrixResultsTabLayoutHeadingsRepository = new GrandPrixResultsTabLayoutHeadingsRepository();
    }

    public void setHeadingsLiveData(String seasonsKey, String stageNumber) {
        this.headingsLiveData = grandPrixResultsTabLayoutHeadingsRepository.
                getHeadingsLiveData(seasonsKey, stageNumber);
    }

    public LiveData<List<GrandPrixResultsTabLayoutHeadingDataModel>> getHeadingsLiveData() {
        return headingsLiveData;
    }
}