package nl.jft.overview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import nl.jft.CustomFragment;
import nl.jft.overview.fragment.MatchesFragment;
import nl.jft.overview.fragment.ProfileFragment;
import nl.jft.overview.fragment.RankingFragment;

/**
 * @author Oscar de Leeuw.
 */

public class OverviewPagerAdapter extends FragmentPagerAdapter {

    Map<Integer, CustomFragment> fragments;

    public OverviewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments = new HashMap<>();

        fragments.put(0, new RankingFragment());
        fragments.put(1, new MatchesFragment());
        fragments.put(2, new ProfileFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
