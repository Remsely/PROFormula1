package edu.samsungit.remsely.proformula.ui.seasons;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.SeasonsItemDataModel;
import edu.samsungit.remsely.proformula.data.repositories.SeasonsRepository;

public class SeasonsViewModel extends ViewModel {
    private final MutableLiveData<List<SeasonsItemDataModel>> seasonsLiveData;

    public SeasonsViewModel(){
        SeasonsRepository seasonsRepository = new SeasonsRepository();
        seasonsLiveData = seasonsRepository.getSeasonsLiveData();
    }
    public MutableLiveData<List<SeasonsItemDataModel>> getSeasonsLiveData() {
        return seasonsLiveData;
    }
}