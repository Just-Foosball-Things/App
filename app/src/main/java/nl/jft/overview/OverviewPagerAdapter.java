package nl.jft.overview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import nl.jft.overview.fragment.MatchesFragment;
import nl.jft.overview.fragment.ProfileFragment;
import nl.jft.overview.fragment.RankingFragment;

/**
 * @author Oscar de Leeuw.
 */

public class OverviewPagerAdapter extends FragmentPagerAdapter {

    public OverviewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return RankingFragment.newInstance();
            case 1:
                return MatchesFragment.newInstance();
            case 2:
                return ProfileFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Ranking";
            case 1:
                return "Matches";
            case 2:
                return "Profile";
        }
        return null;
    }
}
