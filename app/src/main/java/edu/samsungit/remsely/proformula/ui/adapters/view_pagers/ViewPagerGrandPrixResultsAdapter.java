package edu.samsungit.remsely.proformula.ui.adapters.view_pagers;

import static java.lang.Math.abs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Collections;
import java.util.List;

import edu.samsungit.remsely.proformula.data.models.GrandPrixResultsTabLayoutHeadingDataModel;
import edu.samsungit.remsely.proformula.ui.about_stage.AboutStageFragment;
import edu.samsungit.remsely.proformula.ui.grand_prix_results.GrandPrixResultsFragment;
import edu.samsungit.remsely.proformula.ui.race_against_time.RaceAgainstTimeResultsFragment;
import edu.samsungit.remsely.proformula.ui.race_with_points.RaceWithPointsFragment;

public class ViewPagerGrandPrixResultsAdapter extends FragmentStateAdapter {
    private List<GrandPrixResultsTabLayoutHeadingDataModel> headingsList = Collections.emptyList();
    private String seasonsKey;
    private String stageNumber;

    public ViewPagerGrandPrixResultsAdapter(@NonNull GrandPrixResultsFragment fragmentActivity) {
        super(fragmentActivity);
    }

    public void setHeadingsList(List<GrandPrixResultsTabLayoutHeadingDataModel> headingsList) {
        this.headingsList = headingsList;
        notifyItemChanged(0, headingsList.size());
    }

    public void setSeasonsKey(String seasonsKey) {
        this.seasonsKey = seasonsKey;
    }

    public void setStageNumber(String stageNumber) {
        this.stageNumber = stageNumber;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 5) {
            return AboutStageFragment.newInstance(seasonsKey, stageNumber);
        }
        int index = abs(position - 4);
        GrandPrixResultsTabLayoutHeadingDataModel heading = headingsList.get(index);

        if (heading.getIsRace()) {
            return RaceWithPointsFragment.newInstance(seasonsKey, stageNumber, String.valueOf(index + 1));
        }
        return RaceAgainstTimeResultsFragment.newInstance(seasonsKey, stageNumber, String.valueOf(index + 1));
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
