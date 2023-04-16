package edu.samsungit.remsely.proformula.ui.race_with_points;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.samsungit.remsely.proformula.R;

public class RaceWithPointsFragment extends Fragment {

    private RaceWithPointsViewModel mViewModel;

    public static RaceWithPointsFragment newInstance() {
        return new RaceWithPointsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_race_with_points, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RaceWithPointsViewModel.class);
        // TODO: Use the ViewModel
    }

}