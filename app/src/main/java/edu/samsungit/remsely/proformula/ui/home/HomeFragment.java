package edu.samsungit.remsely.proformula.ui.home;

import static android.content.ContentValues.TAG;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private LinearLayout mOpenNotificationSettingsFragment;
    private LinearLayout mOpenGrandPrixResultsFragment;
    private ImageView soonStageFlag;
    private ImageView recentlyStageFlag;
    private TextView soonStageName;
    private TextView recentlyStageName;
    private TextView soonStageLocation;
    private TextView recentlyStageLocation;
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
        mOpenNotificationSettingsFragment = binding.notificationsButton; // Навигация на уведомления
        mOpenGrandPrixResultsFragment = binding.recentlyStageFrame; // Навигация на результаты недавнего Гран-при

        navigateToNotificationsSettings();
        navigateToGrandPrixResults();

        // Поля, отвечающие за заголовок Гран-при в "Скоро"
        soonStageFlag = binding.soonStageFlag;
        soonStageName = binding.soonStageName;
        soonStageLocation = binding.soonStagePlace;

        // Поля, отвечающие за заголовок Гран-при в "Недавно"
        recentlyStageFlag = binding.recentlyStageFlag;
        recentlyStageName = binding.recentlyStageName;
        recentlyStageLocation = binding.recentlyStagePlace;

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        soonStageHeadingLiveDataObservation();
        recentlyStageHeadingLiveDataObservation();
        whereWatchLinksLiveDataObservation();
        aboutRecentlyStageLinksLiveDataObservation();
        stageScheduleLiveDataObservation();
        recentlyRaceResultsLiveDataObservation();
    }

    private void soonStageHeadingLiveDataObservation(){
        homeViewModel.getSoonStageHeadingLiveData().observe(this, heading -> {
            if(heading != null){
                Glide.with(HomeFragment.this).load(heading.getFlag())
                        .transform(new RoundedCornersToImageViewTransformation(dpToPx(14))).into(soonStageFlag);
                Log.i(TAG, heading.getFlag());
                soonStageLocation.setText(heading.getLocation());
                soonStageName.setText(heading.getName());
            }
        });
    }

    private void recentlyStageHeadingLiveDataObservation(){
        homeViewModel.getRecentlyStageHeadingLiveData().observe(this, heading -> {
            if(heading != null){
                Glide.with(HomeFragment.this).load(heading.getFlag())
                        .transform(new RoundedCornersToImageViewTransformation(dpToPx(14))).into(recentlyStageFlag);
                Log.i(TAG, heading.getFlag());
                recentlyStageLocation.setText(heading.getLocation());
                recentlyStageName.setText(heading.getName());
            }
        });
    }

    private void whereWatchLinksLiveDataObservation(){
        homeViewModel.getWhereWatchLinksLiveData().observe(getViewLifecycleOwner(), linksList -> {
            LinksRecyclerViewAdapter adapter = new LinksRecyclerViewAdapter(linksList);
            RecyclerView recyclerView = binding.soonStageWhereWatchRecyclerView;
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }

    private void aboutRecentlyStageLinksLiveDataObservation(){
        homeViewModel.getAboutRecentlyStageLiveData().observe(getViewLifecycleOwner(), linksList -> {
            RecyclerView recyclerView = binding.aboutRecentlyStageLinksRecyclerView;
            recyclerView.setAdapter(new LinksRecyclerViewAdapter(linksList));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }

    private void stageScheduleLiveDataObservation(){
        homeViewModel.getSoonStageScheduleLiveData().observe(getViewLifecycleOwner(), scheduleList -> {
            RecyclerView recyclerView = binding.soonStageScheduleRecyclerView;
            recyclerView.setAdapter(new StageScheduleRecyclerViewAdapter(scheduleList));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }

    private void recentlyRaceResultsLiveDataObservation(){
        homeViewModel.getRecentlyStageResultsLivaData().observe(getViewLifecycleOwner(), raceResults -> {
            RecyclerView recyclerView = binding.recentlyStageRaceResultsRecyclerView;
            recyclerView.setAdapter(new RaceResultsRecyclerViewAdapter(raceResults));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
        mOpenNotificationSettingsFragment.setOnClickListener(v -> Navigation.findNavController(v)
                .navigate(R.id.action_navigation_home_to_notificationsSettingsFragment));
    }

    public void navigateToGrandPrixResults(){
        mOpenGrandPrixResultsFragment.setOnClickListener(v -> Navigation.findNavController(v)
                .navigate(R.id.action_navigation_home_to_grandPrixResultsFragment));
    }
}