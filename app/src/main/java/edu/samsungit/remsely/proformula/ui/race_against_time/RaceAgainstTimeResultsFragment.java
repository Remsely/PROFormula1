package edu.samsungit.remsely.proformula.ui.race_against_time;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.samsungit.remsely.proformula.R;

public class RaceAgainstTimeResultsFragment extends Fragment {

    private RaceAgainstTimeResultsViewModel mViewModel;

    public static RaceAgainstTimeResultsFragment newInstance() {
        return new RaceAgainstTimeResultsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_race_against_time_results, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RaceAgainstTimeResultsViewModel.class);
        // TODO: Use the ViewModel
    }

}