package edu.samsungit.remsely.proformula.ui.selected_season_individual_standings;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.samsungit.remsely.proformula.R;

public class SelectedSeasonIndividualStandings extends Fragment {

    private SelectedSeasonIndividualStandingsViewModel mViewModel;

    public static SelectedSeasonIndividualStandings newInstance() {
        return new SelectedSeasonIndividualStandings();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selected_season_individual_standings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SelectedSeasonIndividualStandingsViewModel.class);
        // TODO: Use the ViewModel
    }

}