package edu.samsungit.remsely.proformula.ui.adapters.view_pagers;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import edu.samsungit.remsely.proformula.ui.grand_prix_results.GrandPrixResultsFragment;
import edu.samsungit.remsely.proformula.ui.race_against_time.RaceAgainstTimeResultsFragment;
import edu.samsungit.remsely.proformula.ui.race_with_points.RaceWithPointsFragment;

public class ViewPagerGrandPrixResultsAdapter extends FragmentStateAdapter {

    public ViewPagerGrandPrixResultsAdapter(@NonNull GrandPrixResultsFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new RaceAgainstTimeResultsFragment();
            case 1: return new RaceAgainstTimeResultsFragment();
            case 2: return new RaceAgainstTimeResultsFragment();
            case 3: return new RaceAgainstTimeResultsFragment();
            case 4: return new RaceWithPointsFragment();
            default: return new RaceAgainstTimeResultsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
