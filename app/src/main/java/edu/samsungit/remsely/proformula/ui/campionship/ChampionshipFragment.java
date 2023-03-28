package edu.samsungit.remsely.proformula.ui.campionship;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.samsungit.remsely.proformula.databinding.FragmentChampionshipBinding;

public class ChampionshipFragment extends Fragment {

    private FragmentChampionshipBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ChampionshipViewModel dashboardViewModel =
                new ViewModelProvider(this).get(ChampionshipViewModel.class);

        binding = FragmentChampionshipBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textChampionship;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}