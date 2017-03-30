package nl.jft.match.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nl.jft.R;

/**
 * @author Oscar de Leeuw.
 */

public class PlaceholderFragment extends Fragment {

    public static PlaceholderFragment newInstance() {
        return new PlaceholderFragment();
    }

    public static CharSequence getTitle() {
        return "Placeholder";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_placeholder, container, false);
        return rootView;
    }
}
