package edu.samsungit.remsely.proformula.ui.race_against_time;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.samsungit.remsely.proformula.databinding.FragmentRaceAgainstTimeResultsBinding;
import edu.samsungit.remsely.proformula.ui.adapters.recycler_views.QualificationResultsRecyclerViewAdapter;

public class RaceAgainstTimeResultsFragment extends Fragment {
    private String seasonsKey;
    private String stageNumber;
    private String eventNumber;
    private FragmentRaceAgainstTimeResultsBinding binding;
    private RaceAgainstTimeResultsViewModel raceAgainstTimeResultsViewModel;
    private RecyclerView qualificationResultsRecyclerView;

    public static RaceAgainstTimeResultsFragment newInstance(String seasonsKey, String stageNumber, String eventNumber) {
        RaceAgainstTimeResultsFragment fragment = new RaceAgainstTimeResultsFragment();
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
        binding = FragmentRaceAgainstTimeResultsBinding.inflate(inflater, container, false);
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

            raceAgainstTimeResultsViewModel = new ViewModelProvider(this)
                    .get(RaceAgainstTimeResultsViewModel.class);

            qualificationResultsRecyclerView = binding.raceAgainstTimeRecyclerView;
            QualificationResultsRecyclerViewAdapter qualificationResultsAdapter =
                    new QualificationResultsRecyclerViewAdapter();
            qualificationResultsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            qualificationResultsRecyclerView.setAdapter(qualificationResultsAdapter);
            qualificationResultsRecyclerView.setItemAnimator(null);

            qualificationResultsLiveDataObservation();
        }
    }

    private void qualificationResultsLiveDataObservation(){
        raceAgainstTimeResultsViewModel.setQualificationResultsLiveData(seasonsKey, stageNumber, eventNumber);
        raceAgainstTimeResultsViewModel.getQualificationResultsLiveData().observe(this, results -> {
            QualificationResultsRecyclerViewAdapter adapter = (QualificationResultsRecyclerViewAdapter)
                    qualificationResultsRecyclerView.getAdapter();
            if (adapter != null){
                adapter.setQualificationResults(results);
            }
        });
    }
}