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

public class SelectedSeasonStages extends Fragment {

    private SelectedSeasonStagesViewModel mViewModel;

    public static SelectedSeasonStages newInstance() {
        return new SelectedSeasonStages();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selected_season_stages, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SelectedSeasonStagesViewModel.class);
        // TODO: Use the ViewModel
    }

}