package edu.samsungit.remsely.proformula.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.LinksDataModel;
import edu.samsungit.remsely.proformula.data.models.RaceResultsDataModel;
import edu.samsungit.remsely.proformula.data.models.StageHeadingDataModel;
import edu.samsungit.remsely.proformula.data.models.StageScheduleDataModel;
import edu.samsungit.remsely.proformula.data.repositories.AboutRecentlyStageLinksRepository;
import edu.samsungit.remsely.proformula.data.repositories.CurrentSeasonKeyRepository;
import edu.samsungit.remsely.proformula.data.repositories.RecentlyStageHeadingRepository;
import edu.samsungit.remsely.proformula.data.repositories.RecentlyStageNumberRepository;
import edu.samsungit.remsely.proformula.data.repositories.RecentlyStageRaceResultsRepository;
import edu.samsungit.remsely.proformula.data.repositories.SoonStageHeadingRepository;
import edu.samsungit.remsely.proformula.data.repositories.SoonStageScheduleRepository;
import edu.samsungit.remsely.proformula.data.repositories.WhereWatchLinksRepository;

public class HomeViewModel extends ViewModel {
    private final LiveData<StageHeadingDataModel> soonStageHeadingLiveData;
    private final LiveData<StageHeadingDataModel> recentlyStageHeadingLiveData;
    private final LiveData<List<LinksDataModel>> whereWatchLinksLiveData;
    private final LiveData<List<LinksDataModel>> aboutRecentlyStageLiveData;
    private final LiveData<List<StageScheduleDataModel>> soonStageScheduleLiveData;
    private final LiveData<List<RaceResultsDataModel>> recentlyStageResultsLivaData;
    private final LiveData<String> currentSeasonKeyLiveData;
    private final LiveData<String> recentlyStageNumberLiveData;

    public HomeViewModel(){
        SoonStageHeadingRepository soonStageHeadingRepository = new SoonStageHeadingRepository();
        soonStageHeadingLiveData = soonStageHeadingRepository.getSoonStageHeading();

        RecentlyStageHeadingRepository recentlyStageHeadingRepository = new RecentlyStageHeadingRepository();
        recentlyStageHeadingLiveData = recentlyStageHeadingRepository.getRecentlyStageHeading();

        WhereWatchLinksRepository whereWatchLinksRepository = new WhereWatchLinksRepository();
        whereWatchLinksLiveData = whereWatchLinksRepository.getWhereWatchLinksLiveData();

        AboutRecentlyStageLinksRepository aboutRecentlyStageLinksRepository = new AboutRecentlyStageLinksRepository();
        aboutRecentlyStageLiveData = aboutRecentlyStageLinksRepository.getAboutStageLinksLiveData();

        SoonStageScheduleRepository soonStageScheduleRepository = new SoonStageScheduleRepository();
        soonStageScheduleLiveData = soonStageScheduleRepository.getSoonStageScheduleLiveData();

        RecentlyStageRaceResultsRepository recentlyStageRaceResultsRepository = new RecentlyStageRaceResultsRepository();
        recentlyStageResultsLivaData = recentlyStageRaceResultsRepository.getRecentlyRaceResultsLiveData();

        CurrentSeasonKeyRepository currentSeasonKeyRepository = new CurrentSeasonKeyRepository();
        currentSeasonKeyLiveData = currentSeasonKeyRepository.getCurrentSeasonKeyLiveData();

        RecentlyStageNumberRepository recentlyStageNumberRepository = new RecentlyStageNumberRepository();
        recentlyStageNumberLiveData = recentlyStageNumberRepository.getCurrentRecentlyStageLiveData();
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

    public LiveData<String> getCurrentSeasonKeyLiveData() {
        return currentSeasonKeyLiveData;
    }

    public LiveData<String> getRecentlyStageNumberLiveData() {
        return recentlyStageNumberLiveData;
    }
}