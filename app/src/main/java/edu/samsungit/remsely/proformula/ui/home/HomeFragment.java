package edu.samsungit.remsely.proformula.ui.home;

import android.os.Bundle;
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
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.FragmentHomeBinding;
import edu.samsungit.remsely.proformula.ui.adapters.recycler_views.LinksRecyclerViewAdapter;
import edu.samsungit.remsely.proformula.ui.adapters.recycler_views.RaceResultsRecyclerViewAdapter;
import edu.samsungit.remsely.proformula.ui.adapters.recycler_views.StageScheduleRecyclerViewAdapter;
import edu.samsungit.remsely.proformula.util.DpToPx;
import edu.samsungit.remsely.proformula.util.RoundedCornersToImageViewTransformation;

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
    private RecyclerView stageScheduleRecyclerView;
    private RecyclerView aboutRecentlyStageRecyclerView;
    private RecyclerView recentlyRaceResultsRecyclerView;
    private RecyclerView whereWatchRecyclerView;

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

    private void init(){
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

        stageScheduleRecyclerView = binding.soonStageScheduleRecyclerView;
        StageScheduleRecyclerViewAdapter scheduleAdapter = new StageScheduleRecyclerViewAdapter();
        stageScheduleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        stageScheduleRecyclerView.setAdapter(scheduleAdapter);
        stageScheduleRecyclerView.setItemAnimator(null);

        whereWatchRecyclerView = binding.soonStageWhereWatchRecyclerView;
        LinksRecyclerViewAdapter whereWatchLinksAdapter = new LinksRecyclerViewAdapter();
        whereWatchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        whereWatchRecyclerView.setAdapter(whereWatchLinksAdapter);
        whereWatchRecyclerView.setItemAnimator(null);

        recentlyRaceResultsRecyclerView = binding.recentlyStageRaceResultsRecyclerView;
        RaceResultsRecyclerViewAdapter recentlyRaceResultsRecyclerViewAdapter = new RaceResultsRecyclerViewAdapter();
        recentlyRaceResultsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recentlyRaceResultsRecyclerView.setAdapter(recentlyRaceResultsRecyclerViewAdapter);
        recentlyRaceResultsRecyclerView.setItemAnimator(null);

        aboutRecentlyStageRecyclerView = binding.aboutRecentlyStageLinksRecyclerView;
        LinksRecyclerViewAdapter aboutRecentlyStageAdapter = new LinksRecyclerViewAdapter();
        aboutRecentlyStageRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        aboutRecentlyStageRecyclerView.setAdapter(aboutRecentlyStageAdapter);
        aboutRecentlyStageRecyclerView.setItemAnimator(null);

        soonStageHeadingLiveDataObservation();
        stageScheduleLiveDataObservation();
        whereWatchLinksLiveDataObservation();
        recentlyStageHeadingLiveDataObservation();
        recentlyRaceResultsLiveDataObservation();
        aboutRecentlyStageLinksLiveDataObservation();
    }

    private void soonStageHeadingLiveDataObservation(){
        homeViewModel.getSoonStageHeadingLiveData().observe(this, heading -> {
            if(heading != null){
                Glide.with(HomeFragment.this).load(heading.getFlag())
                        .transform(new CenterCrop(), new RoundedCornersToImageViewTransformation(DpToPx.dpToPx(14)))
                        .into(soonStageFlag);
                soonStageLocation.setText(heading.getLocation());
                soonStageName.setText(heading.getName());
            }
        });
    }

    private void recentlyStageHeadingLiveDataObservation(){
        homeViewModel.getRecentlyStageHeadingLiveData().observe(this, heading -> {
            if(heading != null){
                Glide.with(HomeFragment.this).load(heading.getFlag())
                        .transform(new CenterCrop(), new RoundedCornersToImageViewTransformation(DpToPx.dpToPx(14)))
                        .into(recentlyStageFlag);
                recentlyStageLocation.setText(heading.getLocation());
                recentlyStageName.setText(heading.getName());
            }
        });
    }

    private void whereWatchLinksLiveDataObservation(){
        homeViewModel.getWhereWatchLinksLiveData().observe(getViewLifecycleOwner(), linksList -> {
            LinksRecyclerViewAdapter adapter = (LinksRecyclerViewAdapter) whereWatchRecyclerView.getAdapter();
            if (adapter != null) {
                adapter.setLinksList(linksList);
            }
        });
    }

    private void aboutRecentlyStageLinksLiveDataObservation(){
        homeViewModel.getAboutRecentlyStageLiveData().observe(getViewLifecycleOwner(), linksList -> {
            LinksRecyclerViewAdapter adapter = (LinksRecyclerViewAdapter) aboutRecentlyStageRecyclerView.getAdapter();
            if (adapter != null) {
                adapter.setLinksList(linksList);
            }
        });
    }

    private void stageScheduleLiveDataObservation(){
        homeViewModel.getSoonStageScheduleLiveData().observe(getViewLifecycleOwner(), scheduleList -> {
            StageScheduleRecyclerViewAdapter adapter = (StageScheduleRecyclerViewAdapter) stageScheduleRecyclerView.getAdapter();
            if (adapter != null) {
                adapter.setEvents(scheduleList);
            }
        });
    }

    private void recentlyRaceResultsLiveDataObservation(){
        homeViewModel.getRecentlyStageResultsLivaData().observe(getViewLifecycleOwner(), raceResults -> {
            RaceResultsRecyclerViewAdapter adapter = (RaceResultsRecyclerViewAdapter) recentlyRaceResultsRecyclerView.getAdapter();
            if (adapter != null) {
                adapter.setRaceResults(raceResults);
            }
        });
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