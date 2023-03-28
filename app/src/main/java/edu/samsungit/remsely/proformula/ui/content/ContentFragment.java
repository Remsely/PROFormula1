package edu.samsungit.remsely.proformula.ui.content;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.FragmentChampionshipBinding;
import edu.samsungit.remsely.proformula.databinding.FragmentContentBinding;
import edu.samsungit.remsely.proformula.ui.campionship.ChampionshipViewModel;

public class ContentFragment extends Fragment {

    private FragmentContentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ContentViewModel dashboardViewModel =
                new ViewModelProvider(this).get(ContentViewModel.class);

        binding = FragmentContentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textContent;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}