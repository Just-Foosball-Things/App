package nl.jft.match;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import nl.jft.CustomFragment;
import nl.jft.match.fragment.MatchOverviewFragment;
import nl.jft.match.fragment.PlaceholderFragment;

/**
 * @author Oscar de Leeuw.
 */

public class MatchPagerAdapter extends FragmentPagerAdapter {

    Map<Integer, CustomFragment> fragments;

    public MatchPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments = new HashMap<>();

        fragments.put(0, new PlaceholderFragment());
        fragments.put(1, new MatchOverviewFragment());
        fragments.put(2, new PlaceholderFragment());
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
