package nl.jft;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Oscar de Leeuw.
 */

public class RankingFragment extends Fragment {

    public static RankingFragment newInstance() {
        /*RankingFragment fragment = new RankingFragment();

        Bundle args = new Bundle();
        args.putInt("Test", sectionNumber);
        fragment.setArguments(args);*/

        return new RankingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ranking, container, false);
        /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt("Test")));*/
        return rootView;
    }
}
