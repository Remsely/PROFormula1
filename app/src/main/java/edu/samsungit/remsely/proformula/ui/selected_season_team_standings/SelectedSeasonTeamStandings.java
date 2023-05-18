package edu.samsungit.remsely.proformula.ui.selected_season_team_standings;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.samsungit.remsely.proformula.databinding.FragmentSelectedSeasonTeamStandingsBinding;
import edu.samsungit.remsely.proformula.ui.adapters.recycler_views.TeamStandingsRecyclerViewAdapter;

public class SelectedSeasonTeamStandings extends Fragment {
    private FragmentSelectedSeasonTeamStandingsBinding binding;
    private String seasonsKey;
    private RecyclerView standingsRecyclerView;
    private SelectedSeasonTeamStandingsViewModel teamStandingsViewModel;

    public static SelectedSeasonTeamStandings newInstance(String seasonsKey) {
        SelectedSeasonTeamStandings fragment = new SelectedSeasonTeamStandings();
        Bundle args = new Bundle();
        args.putString("seasonsKey", seasonsKey);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSelectedSeasonTeamStandingsBinding
                .inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        if(getArguments() != null)
            seasonsKey = getArguments().getString("seasonsKey");

        teamStandingsViewModel = new ViewModelProvider(this)
                .get(SelectedSeasonTeamStandingsViewModel.class);

        standingsRecyclerView = binding.selectedSeasonTeamStandingsRecyclerView;
        TeamStandingsRecyclerViewAdapter standingsAdapter = new TeamStandingsRecyclerViewAdapter();
        standingsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        standingsRecyclerView.setAdapter(standingsAdapter);
        standingsRecyclerView.setItemAnimator(null);

        teamStandingsLiveDataObservation();
    }

    private void teamStandingsLiveDataObservation(){
        teamStandingsViewModel.setTeamStandingLiveData(seasonsKey);
        teamStandingsViewModel.getTeamStandingLiveData().observe(getViewLifecycleOwner(), standings -> {
            TeamStandingsRecyclerViewAdapter adapter = (TeamStandingsRecyclerViewAdapter)
                    standingsRecyclerView.getAdapter();
            if (adapter != null){
                adapter.setStandings(standings);
            }
        });
    }
}