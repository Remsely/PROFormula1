package edu.samsungit.remsely.proformula.ui.selected_season;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import edu.samsungit.remsely.proformula.ui.selected_season_individual_standings.SelectedSeasonIndividualStandings;
import edu.samsungit.remsely.proformula.ui.selected_season_stages.SelectedSeasonStages;
import edu.samsungit.remsely.proformula.ui.selected_season_team_standings.SelectedSeasonTeamStandings;

public class SelectedSeasonViewPagerAdapter extends FragmentStateAdapter {
    public SelectedSeasonViewPagerAdapter(@NonNull SelectedSeasonFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new SelectedSeasonIndividualStandings();
            case 1: return new SelectedSeasonTeamStandings();
            case 2: return new SelectedSeasonStages();
            default: return new SelectedSeasonIndividualStandings();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
