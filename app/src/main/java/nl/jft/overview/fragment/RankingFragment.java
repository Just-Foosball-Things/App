package nl.jft.overview.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nl.jft.CustomFragment;
import nl.jft.R;

/**
 * @author Oscar de Leeuw.
 */

public class RankingFragment extends CustomFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ranking, container, false);
        /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt("Test")));*/
        return rootView;
    }

    @Override
    public CharSequence getTitle() {
        return "Ranking";
    }
}
