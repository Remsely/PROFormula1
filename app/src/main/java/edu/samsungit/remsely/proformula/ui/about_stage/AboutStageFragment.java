package edu.samsungit.remsely.proformula.ui.about_stage;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.FragmentAboutStageBinding;
import edu.samsungit.remsely.proformula.databinding.FragmentRaceWithPointsBinding;
import edu.samsungit.remsely.proformula.ui.race_with_points.RaceWithPointsFragment;

public class AboutStageFragment extends Fragment {
    private String seasonsKey;
    private String stageNumber;
    private FragmentAboutStageBinding binding;

    public static AboutStageFragment newInstance(String seasonsKey, String stageNumber) {
        AboutStageFragment fragment = new AboutStageFragment();
        Bundle args = new Bundle();
        args.putString("seasonsKey", seasonsKey);
        args.putString("stageNumber", stageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAboutStageBinding.inflate(inflater, container, false);
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
        }
    }
}