package edu.samsungit.remsely.proformula.ui.about_stage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.LinksDataModel;
import edu.samsungit.remsely.proformula.data.repositories.AboutStageRepository;

public class AboutStageViewModel extends ViewModel {
    private LiveData<List<LinksDataModel>> aboutStageLiveData;
    private final AboutStageRepository aboutStageRepository;

    public AboutStageViewModel(){
        aboutStageRepository = new AboutStageRepository();
    }

    public void setAboutStageLiveData(String seasonsKey, String stageNumber) {
        aboutStageLiveData = aboutStageRepository.getAboutStageLiveData(seasonsKey, stageNumber);
    }

    public LiveData<List<LinksDataModel>> getAboutStageLiveData() {
        return aboutStageLiveData;
    }
}