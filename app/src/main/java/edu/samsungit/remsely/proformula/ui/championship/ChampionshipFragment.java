package edu.samsungit.remsely.proformula.ui.championship;

import static edu.samsungit.remsely.proformula.util.DpToPx.dpToPx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import edu.samsungit.remsely.proformula.databinding.FragmentChampionshipBinding;
import edu.samsungit.remsely.proformula.ui.adapters.view_pagers.ViewPagerChampionshipAdapter;

public class ChampionshipFragment extends Fragment {
    private FragmentChampionshipBinding binding;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private FrameLayout frameLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChampionshipBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        tabLayout = binding.championshipTabLayout;

        viewPager2 = binding.championshipViewPager;
        ViewPagerChampionshipAdapter viewPagerChampionshipAdapter = new ViewPagerChampionshipAdapter(this);
        viewPager2.setAdapter(viewPagerChampionshipAdapter);
        viewPager2.setPageTransformer((page, position) -> page.setTranslationX(position * dpToPx(10)));

        frameLayout = binding.championshipFrameLayout;
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.GONE);
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager2.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.GONE);
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}