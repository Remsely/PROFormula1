package edu.samsungit.remsely.proformula.ui.championship;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import edu.samsungit.remsely.proformula.ui.individual_standings.IndividualStandingsFragment;
import edu.samsungit.remsely.proformula.ui.season_stages.SeasonStagesFragment;
import edu.samsungit.remsely.proformula.ui.team_standings.TeamStandingsFragment;

public class ViewPagerChampionshipAdapter extends FragmentStateAdapter {
    public ViewPagerChampionshipAdapter(@NonNull ChampionshipFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new IndividualStandingsFragment();
            case 1: return new TeamStandingsFragment();
            case 2: return new SeasonStagesFragment();
            default: return new IndividualStandingsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
