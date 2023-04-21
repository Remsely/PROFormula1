package edu.samsungit.remsely.proformula.ui.home;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;

import edu.samsungit.remsely.proformula.MainActivity;
import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.FragmentHomeBinding;
import edu.samsungit.remsely.proformula.ui.notifications_settings_dialog.NotificationsSettingsFragment;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private LinearLayout mOpenNotificationSettingsFragment;
    private LinearLayout mOpenGrandPrixResultsFragment;
    private ImageView soonStageFlag;
    private TextView soonStageName;
    private TextView soonStageLocation;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    public void init(){
        mOpenNotificationSettingsFragment = binding.notificationsButton;
        mOpenGrandPrixResultsFragment = binding.recentlyStageFrame;
        navigateToNotificationsSettings();
        navigateToGrandPrixResults();
        soonStageFlag = binding.soonStageFlag;
        soonStageName = binding.soonStageName;
        soonStageLocation = binding.soonStagePlace;
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        headingLiveDataObservation();
    }

    private void headingLiveDataObservation(){
        homeViewModel.getHeadingLiveData().observe(this, heading -> {
            if(heading != null){
                Glide.with(HomeFragment.this).load(heading.getFlag()).transform(new RoundedCornersToImageViewTransformation(dpToPx(14))).into(soonStageFlag);
                Log.i(TAG, heading.getFlag());
                soonStageLocation.setText(heading.getLocation());
                soonStageName.setText(heading.getName());
            }
        });
    }

    private int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
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