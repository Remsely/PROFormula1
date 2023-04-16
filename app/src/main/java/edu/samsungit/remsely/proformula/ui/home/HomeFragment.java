package edu.samsungit.remsely.proformula.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import edu.samsungit.remsely.proformula.MainActivity;
import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.FragmentHomeBinding;
import edu.samsungit.remsely.proformula.ui.notifications_settings_dialog.NotificationsSettingsFragment;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private LinearLayout mOpenNotificationSettingsFragment;

    private LinearLayout mOpenGrandPrixResultsFragment;
    MainActivity mainActivity;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        mainActivity = new MainActivity();
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mOpenNotificationSettingsFragment = binding.notificationsButton;
        mOpenGrandPrixResultsFragment = binding.recentlyStageFrame;
        navigateToNotificationsSettings();
        navigateToGrandPrixResults();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void navigateToNotificationsSettings(){
        mOpenNotificationSettingsFragment.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_notificationsSettingsFragment);
        });
    }

    public void navigateToGrandPrixResults(){
        mOpenGrandPrixResultsFragment.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_grandPrixResultsFragment);
        });
    }
}