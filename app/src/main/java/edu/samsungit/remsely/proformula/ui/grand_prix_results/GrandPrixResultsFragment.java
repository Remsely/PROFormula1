package edu.samsungit.remsely.proformula.ui.grand_prix_results;

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

import edu.samsungit.remsely.proformula.databinding.FragmentGrandPrixResultsBinding;
import edu.samsungit.remsely.proformula.ui.adapters.ViewPagerGrandPrixResultsAdapter;

public class GrandPrixResultsFragment extends Fragment {
    private FragmentGrandPrixResultsBinding binding;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewPagerGrandPrixResultsAdapter viewPagerGrandPrixResultsAdapter;
    FrameLayout frameLayout;
    private GrandPrixResultsViewModel mViewModel;
    LinearLayout mGoToBackFragment;

    public static GrandPrixResultsFragment newInstance() {
        return new GrandPrixResultsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentGrandPrixResultsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = binding.grandPrixResultsTabLayout;
        viewPager2 = binding.grandPrixResultsViewPager;
        viewPagerGrandPrixResultsAdapter = new ViewPagerGrandPrixResultsAdapter(this);
        viewPager2.setAdapter(viewPagerGrandPrixResultsAdapter);
        mGoToBackFragment = binding.grandPrixResultsBackButton;
        frameLayout = binding.grandPrixResultsFrameLayout;
        navigateToBack();
        onTabSelectedListenerMethod();
        registerOnPageChangeCallbackMethod();
    }

    public void navigateToBack(){
        mGoToBackFragment.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });
    }

    void onTabSelectedListenerMethod(){
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
    }

    void registerOnPageChangeCallbackMethod(){
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        tabLayout.getTabAt(position).select();
                }
                super.onPageSelected(position);
            }
        });
    }
}