package edu.samsungit.remsely.proformula.ui.selected_season_team_standings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.TeamStandingsPositionDataModel;
import edu.samsungit.remsely.proformula.data.repositories.SelectedSeasonTeamStandingsRepository;

public class SelectedSeasonTeamStandingsViewModel extends ViewModel {
    private MutableLiveData<List<TeamStandingsPositionDataModel>> teamStandingLiveData;
    private final SelectedSeasonTeamStandingsRepository selectedSeasonTeamStandingsRepository;

    public SelectedSeasonTeamStandingsViewModel(){
        selectedSeasonTeamStandingsRepository = new SelectedSeasonTeamStandingsRepository();
    }

    public void setTeamStandingLiveData(String seasonsKey){
        teamStandingLiveData = selectedSeasonTeamStandingsRepository
                .getTeamStandingsLiveData(seasonsKey);
    }

    public MutableLiveData<List<TeamStandingsPositionDataModel>> getTeamStandingLiveData() {
        return teamStandingLiveData;
    }
}