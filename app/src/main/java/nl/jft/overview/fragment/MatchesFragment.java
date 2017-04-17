package nl.jft.overview.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import nl.jft.Constants;
import nl.jft.CustomFragment;
import nl.jft.R;
import nl.jft.logic.match.Match;
import nl.jft.widget.AnimatedExpandableListView;
import nl.jft.widget.match.MatchesOverviewListAdapter;

/**
 * @author Oscar de Leeuw.
 */

public class MatchesFragment extends CustomFragment {

    private AnimatedExpandableListView list;
    private MatchesOverviewListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_matches, container, false);

        adapter = new MatchesOverviewListAdapter(inflater.getContext());
        list = (AnimatedExpandableListView) rootView.findViewById(R.id.overview_matches_listview);
        list.setAdapter(adapter);

        System.out.println("setup adapter");

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        new RequestMatchesTask().execute();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public CharSequence getTitle() {
        return "Matches";
    }

    private class RequestMatchesTask extends AsyncTask<Void, Void, List<Match>> {

        @Override
        protected List<Match> doInBackground(Void... params) {
            try {
                final String url = String.format("%s/matches", Constants.REST_HOST);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                List<Match> matches = Arrays.asList(restTemplate.getForObject(url, Match[].class));

                System.out.println(matches);

                return matches;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Match> matches) {
            adapter.addMatches(matches);

            MatchesFragmentClickListener listener = new MatchesFragmentClickListener(matches);
            list.setOnGroupClickListener(listener);
        }
    }

}
