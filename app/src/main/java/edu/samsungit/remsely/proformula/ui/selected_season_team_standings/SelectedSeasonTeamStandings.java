package edu.samsungit.remsely.proformula.ui.selected_season_team_standings;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.samsungit.remsely.proformula.R;

public class SelectedSeasonTeamStandings extends Fragment {

    private SelectedSeasonTeamStandingsViewModel mViewModel;

    public static SelectedSeasonTeamStandings newInstance() {
        return new SelectedSeasonTeamStandings();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selected_season_team_standings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SelectedSeasonTeamStandingsViewModel.class);
        // TODO: Use the ViewModel
    }

}