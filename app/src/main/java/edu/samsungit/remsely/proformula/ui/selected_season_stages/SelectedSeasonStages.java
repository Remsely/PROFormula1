package edu.samsungit.remsely.proformula.ui.selected_season_stages;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.FragmentSelectedSeasonStagesBinding;
import edu.samsungit.remsely.proformula.ui.selected_season_individual_standings.SelectedSeasonIndividualStandings;

public class SelectedSeasonStages extends Fragment {
    private FragmentSelectedSeasonStagesBinding binding;
    private String seasonsKey;

    public static SelectedSeasonStages newInstance(String seasonsKey) {
        SelectedSeasonStages fragment = new SelectedSeasonStages();
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
    }
}