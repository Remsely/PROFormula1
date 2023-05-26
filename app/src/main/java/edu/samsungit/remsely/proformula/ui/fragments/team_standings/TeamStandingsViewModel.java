package edu.samsungit.remsely.proformula.ui.fragments.team_standings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.TeamStandingsPositionDataModel;
import edu.samsungit.remsely.proformula.data.repositories.TeamStandingsRepository;

public class TeamStandingsViewModel extends ViewModel {
    private final MutableLiveData<List<TeamStandingsPositionDataModel>> teamStandingsLiveData;

    public TeamStandingsViewModel(){
        TeamStandingsRepository teamStandingsRepository = new TeamStandingsRepository();
        teamStandingsLiveData = teamStandingsRepository.getTeamStandingsLiveData();
    }

    public MutableLiveData<List<TeamStandingsPositionDataModel>> getTeamStandingsLiveData(){
        return teamStandingsLiveData;
    }
}