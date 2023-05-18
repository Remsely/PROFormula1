package edu.samsungit.remsely.proformula.ui.selected_season_individual_standings;

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

import edu.samsungit.remsely.proformula.databinding.FragmentSelectedSeasonIndividualStandingsBinding;
import edu.samsungit.remsely.proformula.ui.adapters.recycler_views.IndividualStandingsRecyclerViewAdapter;

public class SelectedSeasonIndividualStandingsFragment extends Fragment {
    private FragmentSelectedSeasonIndividualStandingsBinding binding;
    private String seasonsKey;
    private RecyclerView standingsRecyclerView;
    private SelectedSeasonIndividualStandingsViewModel individualStandingsViewModel;

    public static SelectedSeasonIndividualStandingsFragment newInstance(String seasonsKey) {
        SelectedSeasonIndividualStandingsFragment fragment = new SelectedSeasonIndividualStandingsFragment();
        Bundle args = new Bundle();
        args.putString("seasonsKey", seasonsKey);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSelectedSeasonIndividualStandingsBinding
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

        individualStandingsViewModel = new ViewModelProvider(this)
                .get(SelectedSeasonIndividualStandingsViewModel.class);

        standingsRecyclerView = binding.selectedSeasonIndividualStandingsRecyclerView;
        IndividualStandingsRecyclerViewAdapter standingsAdapter = new IndividualStandingsRecyclerViewAdapter();
        standingsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        standingsRecyclerView.setAdapter(standingsAdapter);
        standingsRecyclerView.setItemAnimator(null);

        individualStandingsLiveDataObservation();
    }

    private void individualStandingsLiveDataObservation(){
        individualStandingsViewModel.setIndividualStandingsLiveData(seasonsKey);
        individualStandingsViewModel.getIndividualStandingsLiveData().observe(getViewLifecycleOwner(), standings -> {
            IndividualStandingsRecyclerViewAdapter adapter = (IndividualStandingsRecyclerViewAdapter)
                    standingsRecyclerView.getAdapter();
            if(adapter != null){
                adapter.setStandings(standings);
            }
        });
    }
}