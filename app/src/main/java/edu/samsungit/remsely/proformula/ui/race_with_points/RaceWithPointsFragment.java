package edu.samsungit.remsely.proformula.ui.race_with_points;

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

import edu.samsungit.remsely.proformula.databinding.FragmentRaceWithPointsBinding;
import edu.samsungit.remsely.proformula.ui.adapters.recycler_views.RaceResultsRecyclerViewAdapter;

public class RaceWithPointsFragment extends Fragment {
    private String seasonsKey;
    private String stageNumber;
    private String eventNumber;
    private FragmentRaceWithPointsBinding binding;
    private RaceWithPointsViewModel raceWithPointsViewModel;
    private RecyclerView raceResultsRecyclerView;

    public static RaceWithPointsFragment newInstance(String seasonsKey, String stageNumber, String eventNumber) {
        RaceWithPointsFragment fragment = new RaceWithPointsFragment();
        Bundle args = new Bundle();
        args.putString("seasonsKey", seasonsKey);
        args.putString("stageNumber", stageNumber);
        args.putString("eventNumber", eventNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRaceWithPointsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        if (getArguments() != null){
            seasonsKey = getArguments().getString("seasonsKey");
            stageNumber = getArguments().getString("stageNumber");
            eventNumber = getArguments().getString("eventNumber");
        }

        raceWithPointsViewModel = new ViewModelProvider(this).get(RaceWithPointsViewModel.class);

        raceResultsRecyclerView = binding.raceWithPointRecyclerView;
        RaceResultsRecyclerViewAdapter raceResultsAdapter = new RaceResultsRecyclerViewAdapter();
        raceResultsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        raceResultsRecyclerView.setAdapter(raceResultsAdapter);
        raceResultsRecyclerView.setItemAnimator(null);

        raceResultsLiveDataObservation();
    }

    private void raceResultsLiveDataObservation(){
        raceWithPointsViewModel.setRaceResultsLiveData(seasonsKey, stageNumber, eventNumber);
        raceWithPointsViewModel.getRaceResultsLiveData().observe(this, results ->{
            RaceResultsRecyclerViewAdapter adapter = (RaceResultsRecyclerViewAdapter)
                    raceResultsRecyclerView.getAdapter();
            if (adapter != null){
                adapter.setRaceResults(results);
            }
        });
    }
}