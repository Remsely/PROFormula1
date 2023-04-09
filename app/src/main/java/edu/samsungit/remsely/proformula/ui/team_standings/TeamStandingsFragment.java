package edu.samsungit.remsely.proformula.ui.team_standings;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.FragmentTeamStandingsBinding;

public class TeamStandingsFragment extends Fragment {

    private FragmentTeamStandingsBinding binding;

    private TeamStandingsViewModel mViewModel;

    public static TeamStandingsFragment newInstance() {
        return new TeamStandingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_team_standings, container, false);
    }
}