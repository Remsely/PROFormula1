package edu.samsungit.remsely.proformula.ui.selected_season;

import static edu.samsungit.remsely.proformula.util.DpToPx.dpToPx;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import edu.samsungit.remsely.proformula.databinding.FragmentSelectedSeasonBinding;
import edu.samsungit.remsely.proformula.ui.adapters.view_pagers.SelectedSeasonViewPagerAdapter;

public class SelectedSeasonFragment extends Fragment {
    private FragmentSelectedSeasonBinding binding;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private FrameLayout frameLayout;
    private LinearLayout backButton;
    private String seasonKey;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSelectedSeasonBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @SuppressLint("SetTextI18n")
    private void init(){
        if (getArguments() != null) {
            seasonKey = getArguments().getString("seasonsKey");
            binding.selectedSeasonScreenName.setText("СЕЗОН " + seasonKey);
        }

        tabLayout = binding.selectedSeasonTabLayout;

        viewPager2 = binding.selectedSeasonViewPager;
        SelectedSeasonViewPagerAdapter viewPagerAdapter = new SelectedSeasonViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
        viewPagerAdapter.setSeasonsKey(seasonKey);
        viewPager2.setPageTransformer(((page, position) -> page.setTranslationX(position * dpToPx(10))));

        frameLayout = binding.selectedSeasonFrameLayout;

        tabLayoutOnSelectedListenerAddition();
        ViewPagerOnPageChangeCallbackRegistration();

        backButton = binding.selectedSeasonBackButton;
        navigationToSeasonsScreen();
    }

    private void tabLayoutOnSelectedListenerAddition(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.GONE);
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager2.setVisibility(View.VISIBLE);
                viewPager2.setVisibility(View.VISIBLE);
            }
        });
    }

    private void ViewPagerOnPageChangeCallbackRegistration(){
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                    case 1:
                    case 2:
                        Objects.requireNonNull(tabLayout.getTabAt(position)).select();
                }
                super.onPageSelected(position);
            }
        });
    }

    private void navigationToSeasonsScreen(){
        backButton.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());
    }
}