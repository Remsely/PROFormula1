package edu.samsungit.remsely.proformula.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.LinksDataModel;
import edu.samsungit.remsely.proformula.data.models.RaceResultsDataModel;
import edu.samsungit.remsely.proformula.data.models.StageHeadingDataModel;
import edu.samsungit.remsely.proformula.data.models.StageScheduleDataModel;
import edu.samsungit.remsely.proformula.data.repositories.AboutRecentlyStageLinksRepository;
import edu.samsungit.remsely.proformula.data.repositories.RecentlyStageHeadingRepository;
import edu.samsungit.remsely.proformula.data.repositories.RecentlyStageRaceResultsRepository;
import edu.samsungit.remsely.proformula.data.repositories.SoonStageHeadingRepository;
import edu.samsungit.remsely.proformula.data.repositories.SoonStageScheduleRepository;
import edu.samsungit.remsely.proformula.data.repositories.WhereWatchLinksRepository;

public class HomeViewModel extends ViewModel {
    private SoonStageHeadingRepository soonStageHeadingRepository;
    private RecentlyStageHeadingRepository recentlyStageHeadingRepository;
    private LiveData<StageHeadingDataModel> soonStageHeadingLiveData;
    private LiveData<StageHeadingDataModel> recentlyStageHeadingLiveData;
    private WhereWatchLinksRepository whereWatchLinksRepository;
    private LiveData<List<LinksDataModel>> whereWatchLinksLiveData;
    private AboutRecentlyStageLinksRepository aboutRecentlyStageLinksRepository;
    private LiveData<List<LinksDataModel>> aboutRecentlyStageLiveData;
    private SoonStageScheduleRepository soonStageScheduleRepository;
    private LiveData<List<StageScheduleDataModel>> soonStageScheduleLiveData;
    private RecentlyStageRaceResultsRepository recentlyStageRaceResultsRepository;
    private LiveData<List<RaceResultsDataModel>> recentlyStageResultsLivaData;

    public HomeViewModel(){
        soonStageHeadingRepository = new SoonStageHeadingRepository();
        soonStageHeadingLiveData = soonStageHeadingRepository.getSoonStageHeading();

        recentlyStageHeadingRepository = new RecentlyStageHeadingRepository();
        recentlyStageHeadingLiveData = recentlyStageHeadingRepository.getRecentlyStageHeading();

        whereWatchLinksRepository = new WhereWatchLinksRepository();
        whereWatchLinksLiveData = whereWatchLinksRepository.getWhereWatchLinksLiveData();

        aboutRecentlyStageLinksRepository = new AboutRecentlyStageLinksRepository();
        aboutRecentlyStageLiveData = aboutRecentlyStageLinksRepository.getAboutStageLinksLiveData();

        soonStageScheduleRepository = new SoonStageScheduleRepository();
        soonStageScheduleLiveData = soonStageScheduleRepository.getSoonStageScheduleLiveData();

        recentlyStageRaceResultsRepository = new RecentlyStageRaceResultsRepository();
        recentlyStageResultsLivaData = recentlyStageRaceResultsRepository.getRecentlyRaceResultsLiveData();
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

    public LiveData<List<StageScheduleDataModel>> getSoonStageScheduleLiveData(){
        return soonStageScheduleLiveData;
    }

    public LiveData<List<RaceResultsDataModel>> getRecentlyStageResultsLivaData(){
        return recentlyStageResultsLivaData;
    }
}