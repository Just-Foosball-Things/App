package nl.jft.match.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nl.jft.CustomFragment;
import nl.jft.R;

/**
 * @author Oscar de Leeuw.
 */

public class MatchOverviewFragment extends CustomFragment {

    public static MatchOverviewFragment newInstance() {
        return new MatchOverviewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_match_overview, container, false);
        return rootView;
    }

    @Override
    public CharSequence getTitle() {
        return "Overview";
    }
}
