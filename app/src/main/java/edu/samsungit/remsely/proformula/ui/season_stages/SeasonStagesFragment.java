package edu.samsungit.remsely.proformula.ui.season_stages;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.FragmentSeasonStagesBinding;

public class SeasonStagesFragment extends Fragment {

    private FragmentSeasonStagesBinding binding;

    private SeasonStagesViewModel mViewModel;

    public static SeasonStagesFragment newInstance() {
        return new SeasonStagesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_season_stages, container, false);
    }
}