package edu.samsungit.remsely.proformula.ui.individual_standings;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.FragmentIndividualStandingsBinding;

public class IndividualStandingsFragment extends Fragment {

    private FragmentIndividualStandingsBinding binding;

    private IndividualStandingsViewModel mViewModel;

    public static IndividualStandingsFragment newInstance() {
        return new IndividualStandingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_individual_standings, container, false);
    }
}