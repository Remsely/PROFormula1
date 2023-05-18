package edu.samsungit.remsely.proformula.ui.team_standings;

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

import edu.samsungit.remsely.proformula.databinding.FragmentTeamStandingsBinding;
import edu.samsungit.remsely.proformula.ui.adapters.recycler_views.TeamStandingsRecyclerViewAdapter;

public class TeamStandingsFragment extends Fragment {
    private FragmentTeamStandingsBinding binding;
    private TeamStandingsViewModel teamStandingsViewModel;
    private RecyclerView standingsRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTeamStandingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        teamStandingsViewModel = new ViewModelProvider(this).get(TeamStandingsViewModel.class);

        standingsRecyclerView = binding.currentSeasonTeamStandingsRecyclerView;
        TeamStandingsRecyclerViewAdapter standingsAdapter = new TeamStandingsRecyclerViewAdapter();
        standingsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        standingsRecyclerView.setAdapter(standingsAdapter);
        standingsRecyclerView.setItemAnimator(null);

        teamStandingsLiveDataObservation();
    }

    private void teamStandingsLiveDataObservation(){
        teamStandingsViewModel.getTeamStandingsLiveData().observe(getViewLifecycleOwner(), standings -> {
            TeamStandingsRecyclerViewAdapter adapter = (TeamStandingsRecyclerViewAdapter) standingsRecyclerView.getAdapter();
            if (adapter != null){
                adapter.setStandings(standings);
            }
        });
    }
}