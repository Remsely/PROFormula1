package edu.samsungit.remsely.proformula.ui.campionship;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChampionshipViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ChampionshipViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is championship fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}