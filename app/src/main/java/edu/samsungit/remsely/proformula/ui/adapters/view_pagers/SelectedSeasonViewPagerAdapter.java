package edu.samsungit.remsely.proformula.ui.adapters.view_pagers;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import edu.samsungit.remsely.proformula.ui.selected_season.SelectedSeasonFragment;
import edu.samsungit.remsely.proformula.ui.selected_season_individual_standings.SelectedSeasonIndividualStandingsFragment;
import edu.samsungit.remsely.proformula.ui.selected_season_stages.SelectedSeasonStagesFragment;
import edu.samsungit.remsely.proformula.ui.selected_season_team_standings.SelectedSeasonTeamStandingsFragment;

public class SelectedSeasonViewPagerAdapter extends FragmentStateAdapter {
    private String seasonsKey;

    public SelectedSeasonViewPagerAdapter(@NonNull SelectedSeasonFragment fragmentActivity) {
        super(fragmentActivity);
    }

    public void setSeasonsKey(String seasonsKey){
        this.seasonsKey = seasonsKey;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return SelectedSeasonTeamStandingsFragment.newInstance(seasonsKey);
            case 2:
                return SelectedSeasonStagesFragment.newInstance(seasonsKey);
            default:
                return SelectedSeasonIndividualStandingsFragment.newInstance(seasonsKey);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
