package edu.samsungit.remsely.proformula.ui.seasons;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SeasonsViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public SeasonsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is seasons fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}