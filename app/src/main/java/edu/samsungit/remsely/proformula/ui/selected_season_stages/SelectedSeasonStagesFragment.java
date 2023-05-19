package edu.samsungit.remsely.proformula.ui.selected_season_stages;

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
import edu.samsungit.remsely.proformula.databinding.FragmentSelectedSeasonStagesBinding;
import edu.samsungit.remsely.proformula.ui.adapters.recycler_views.SeasonStagesRecyclerViewAdapter;

public class SelectedSeasonStagesFragment extends Fragment {
    private FragmentSelectedSeasonStagesBinding binding;
    private String seasonsKey;
    private SelectedSeasonStagesViewModel selectedSeasonStagesViewModel;
    private RecyclerView stagesRecyclerView;

    public static SelectedSeasonStagesFragment newInstance(String seasonsKey) {
        SelectedSeasonStagesFragment fragment = new SelectedSeasonStagesFragment();
        Bundle args = new Bundle();
        args.putString("seasonsKey", seasonsKey);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSelectedSeasonStagesBinding.inflate(inflater, container, false);
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

        selectedSeasonStagesViewModel = new ViewModelProvider(this)
                .get(SelectedSeasonStagesViewModel.class);

        stagesRecyclerView = binding.selectedSeasonStagesRecyclerView;
        SeasonStagesRecyclerViewAdapter stagesAdapter = new SeasonStagesRecyclerViewAdapter();
        stagesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        stagesRecyclerView.setAdapter(stagesAdapter);
        stagesRecyclerView.setItemAnimator(null);

        seasonStagesLiveDataObservation();

        navigationToSelectedStageResults(stagesAdapter);
    }

    private void seasonStagesLiveDataObservation(){
        selectedSeasonStagesViewModel.setSeasonStagesLiveData(seasonsKey);
        selectedSeasonStagesViewModel.getSeasonStagesLiveData().observe(getViewLifecycleOwner(), stagesList -> {
            SeasonStagesRecyclerViewAdapter adapter =
                    (SeasonStagesRecyclerViewAdapter) stagesRecyclerView.getAdapter();
            if(adapter != null){
                adapter.setStagesList(stagesList);
            }
        });
    }

    private void navigationToSelectedStageResults(SeasonStagesRecyclerViewAdapter adapter){
        adapter.setOnItemClickListener(stagesItem -> {
            if(stagesItem.getReadiness()){
                Bundle args = new Bundle();
                args.putString("seasonsKey", seasonsKey);
                args.putString("stageNumber", String.valueOf(stagesItem.getStageNumber()));
                args.putString("stageName", stagesItem.getStageHeadingDataModel().getName());
                args.putString("stageLocation", stagesItem.getStageHeadingDataModel().getLocation());
                args.putString("stageFlag", stagesItem.getStageHeadingDataModel().getFlag());
                Navigation.findNavController(stagesRecyclerView)
                        .navigate(R.id.action_selectedSeasonFragment_to_grandPrixResultsFragment, args);
            }
            else {
                Toast.makeText(getContext(),
                        "Результаты этого Гран-при пока недоступны!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}