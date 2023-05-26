package edu.samsungit.remsely.proformula.ui.fragments.season_stages;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.FragmentSeasonStagesBinding;
import edu.samsungit.remsely.proformula.ui.adapters.recycler_views.SeasonStagesRecyclerViewAdapter;

public class SeasonStagesFragment extends Fragment {
    private FragmentSeasonStagesBinding binding;
    private SeasonStagesViewModel seasonStagesViewModel;
    private RecyclerView stagesRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSeasonStagesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        seasonStagesViewModel = new ViewModelProvider(this).get(SeasonStagesViewModel.class);
        stagesRecyclerView = binding.seasonsStagesRecyclerView;
        SeasonStagesRecyclerViewAdapter stagesAdapter = new SeasonStagesRecyclerViewAdapter();
        stagesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        stagesRecyclerView.setAdapter(stagesAdapter);
        stagesRecyclerView.setItemAnimator(null);

        seasonStagesLiveDataObservation();

        navigationToSelectedStageResults(stagesAdapter);
    }

    private void seasonStagesLiveDataObservation(){
        seasonStagesViewModel.getCurrentSeasonKeyLiveData().observe(getViewLifecycleOwner(), s -> {
            seasonStagesViewModel.setSeasonStagesLiveData(s);
            seasonStagesViewModel.getSeasonStagesLiveData().observe(getViewLifecycleOwner(), stagesList -> {
                SeasonStagesRecyclerViewAdapter adapter =
                        (SeasonStagesRecyclerViewAdapter) stagesRecyclerView.getAdapter();
                if(adapter != null){
                    adapter.setStagesList(stagesList);
                }
            });
        });
    }

    private void navigationToSelectedStageResults(SeasonStagesRecyclerViewAdapter adapter){
        seasonStagesViewModel.getCurrentSeasonKeyLiveData().observe(getViewLifecycleOwner(), s ->
                adapter.setOnItemClickListener(stagesItem -> {

            if (stagesItem.getReadiness()) {
                Bundle args = new Bundle();
                args.putString("seasonsKey", s);
                args.putString("stageNumber", String.valueOf(stagesItem.getStageNumber()));
                args.putString("stageName", stagesItem.getStageHeadingDataModel().getName());
                args.putString("stageLocation", stagesItem.getStageHeadingDataModel().getLocation());
                args.putString("stageFlag", stagesItem.getStageHeadingDataModel().getFlag());
                Navigation.findNavController(stagesRecyclerView)
                        .navigate(R.id.action_navigation_championship_to_grandPrixResultsFragment, args);
            }
            else{
                Toast.makeText(getContext(),
                        "Результаты этого Гран-при пока недоступны!",
                        Toast.LENGTH_SHORT).show();
            }
        }));
    }
}