package edu.samsungit.remsely.proformula.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    private SoonStageHeadingRepository headingRepository;
    private LiveData<SoonStageHeadingDataModel> headingLiveData;

    public HomeViewModel(){
        headingRepository = new SoonStageHeadingRepository();
        headingLiveData = headingRepository.getSoonStageHeading();
    }

    public LiveData<SoonStageHeadingDataModel> getHeadingLiveData(){
        return headingLiveData;
    }
}