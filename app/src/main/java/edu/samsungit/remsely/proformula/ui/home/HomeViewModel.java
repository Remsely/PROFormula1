package edu.samsungit.remsely.proformula.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private SoonStageHeadingRepository soonStageHeadingRepository;
    private RecentlyStageHeadingRepository recentlyStageHeadingRepository;
    private LiveData<StageHeadingDataModel> soonStageHeadingLiveData;
    private LiveData<StageHeadingDataModel> recentlyStageHeadingLiveData;
    private WhereWatchLinksRepository whereWatchLinksRepository;
    private LiveData<List<LinksDataModel>> whereWatchLinksLiveData;
    private AboutRecentlyStageLinksRepository aboutRecentlyStageLinksRepository;
    private LiveData<List<LinksDataModel>> aboutRecentlyStageLiveData;

    public HomeViewModel(){
        soonStageHeadingRepository = new SoonStageHeadingRepository();
        soonStageHeadingLiveData = soonStageHeadingRepository.getSoonStageHeading();

        recentlyStageHeadingRepository = new RecentlyStageHeadingRepository();
        recentlyStageHeadingLiveData = recentlyStageHeadingRepository.getRecentlyStageHeading();

        whereWatchLinksRepository = new WhereWatchLinksRepository();
        whereWatchLinksLiveData = whereWatchLinksRepository.getWhereWatchLinksLiveData();

        aboutRecentlyStageLinksRepository = new AboutRecentlyStageLinksRepository();
        aboutRecentlyStageLiveData = aboutRecentlyStageLinksRepository.getAboutStageLinksLiveData();
    }

    public LiveData<StageHeadingDataModel> getSoonStageHeadingLiveData(){
        return soonStageHeadingLiveData;
    }

    public LiveData<StageHeadingDataModel> getRecentlyStageHeadingLiveData(){
        return recentlyStageHeadingLiveData;
    }

    public LiveData<List<LinksDataModel>> getWhereWatchLinksLiveData(){
        return whereWatchLinksLiveData;
    }

    public LiveData<List<LinksDataModel>> getAboutRecentlyStageLiveData(){
        return aboutRecentlyStageLiveData;
    }
}