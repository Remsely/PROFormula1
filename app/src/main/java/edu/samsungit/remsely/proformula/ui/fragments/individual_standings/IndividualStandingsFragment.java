package edu.samsungit.remsely.proformula.ui.fragments.individual_standings;

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

import edu.samsungit.remsely.proformula.databinding.FragmentIndividualStandingsBinding;
import edu.samsungit.remsely.proformula.ui.adapters.recycler_views.IndividualStandingsRecyclerViewAdapter;

public class IndividualStandingsFragment extends Fragment {
    private FragmentIndividualStandingsBinding binding;
    private IndividualStandingsViewModel individualStandingsViewModel;
    private RecyclerView standingsRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentIndividualStandingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        individualStandingsViewModel = new ViewModelProvider(this).get(IndividualStandingsViewModel.class);

        standingsRecyclerView = binding.currentSeasonIndividualStandingsRecyclerView;
        IndividualStandingsRecyclerViewAdapter standingsAdapter = new IndividualStandingsRecyclerViewAdapter();
        standingsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        standingsRecyclerView.setAdapter(standingsAdapter);
        standingsRecyclerView.setItemAnimator(null);

        individualStandingsLiveDataObservation();
    }

    private void individualStandingsLiveDataObservation(){
        individualStandingsViewModel.getIndividualStandingsLiveData().observe(getViewLifecycleOwner(), standings -> {
            IndividualStandingsRecyclerViewAdapter adapter = (IndividualStandingsRecyclerViewAdapter)
                    standingsRecyclerView.getAdapter();
            if(adapter != null){
                adapter.setStandings(standings);
            }
        });
    }
}