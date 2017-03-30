package nl.jft.match;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import nl.jft.match.fragment.MatchOverviewFragment;
import nl.jft.match.fragment.PlaceholderFragment;

/**
 * @author Oscar de Leeuw.
 */

public class MatchPagerAdapter extends FragmentPagerAdapter {

    MatchOverviewFragment fragment;

    public MatchPagerAdapter(FragmentManager fm) {
        super(fm);
        fragment = new MatchOverviewFragment();
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return PlaceholderFragment.newInstance();
            case 1:
                return fragment;
            case 2:
                return PlaceholderFragment.newInstance();
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
                return "Placeholder";
            case 1:
                return "Overview";
            case 2:
                return "Placeholder";
        }
        return null;
    }
}
