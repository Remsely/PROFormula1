package edu.samsungit.remsely.proformula.ui.grand_prix_results;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.google.android.material.tabs.TabLayout;

import edu.samsungit.remsely.proformula.databinding.FragmentGrandPrixResultsBinding;
import edu.samsungit.remsely.proformula.ui.adapters.view_pagers.ViewPagerGrandPrixResultsAdapter;
import edu.samsungit.remsely.proformula.util.RoundedCornersToImageViewTransformation;

public class GrandPrixResultsFragment extends Fragment {
    private FragmentGrandPrixResultsBinding binding;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ViewPagerGrandPrixResultsAdapter viewPagerGrandPrixResultsAdapter;
    private FrameLayout frameLayout;
    private LinearLayout mGoToBackFragment;
    private String seasonKey;
    private String stageNumber;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentGrandPrixResultsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @SuppressLint("SetTextI18n")
    private void init(){
        if (getArguments() != null){
            seasonKey = getArguments().getString("seasonsKey");
            binding.grandPrixResultsScreenName.setText("СЕЗОН " + seasonKey);

            stageNumber = getArguments().getString("stageNumber");

            binding.grandPrixResultsStageName.setText(getArguments().getString("stageName"));
            binding.grandPrixResultsStagePlace.setText(getArguments().getString("stageLocation"));

            Glide.with(binding.grandPrixResultsStageFlag).load(getArguments().getString("stageFlag"))
                    .transform(new CenterCrop(), new RoundedCornersToImageViewTransformation(dpToPx(14)))
                    .into(binding.grandPrixResultsStageFlag);
        }

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