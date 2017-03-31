package nl.jft.overview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import nl.jft.CustomFragment;
import nl.jft.R;
import nl.jft.common.rating.glicko.GlickoRating;
import nl.jft.logic.match.Goal;
import nl.jft.logic.match.Match;
import nl.jft.logic.match.MatchType;
import nl.jft.logic.participant.Participant;
import nl.jft.logic.participant.Title;
import nl.jft.logic.participant.impl.User;
import nl.jft.widget.AnimatedExpandableListView;
import nl.jft.widget.match.MatchesOverviewListAdapter;

/**
 * @author Oscar de Leeuw.
 */

public class MatchesFragment extends CustomFragment {

    private static Random random = new Random();
    private List<Match> matches;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_matches, container, false);
        AnimatedExpandableListView listView = (AnimatedExpandableListView) rootView.findViewById(R.id.overview_matches_listview);

        matches = getMatches();
        final MatchesOverviewListAdapter adapter = new MatchesOverviewListAdapter(inflater.getContext());
        adapter.addMatches(matches);

        listView.setAdapter(adapter);
        MatchesFragmentClickListener listener = new MatchesFragmentClickListener(matches);

        listView.setOnGroupClickListener(listener);
        listView.setOnChildClickListener(listener);

        return rootView;
    }

    @Override
    public CharSequence getTitle() {
        return "Matches";
    }

    private List<Match> getMatches() {
        List<Match> matches = new ArrayList<>();

        final Participant lesley = new User("Lesley", new GlickoRating(1500, 350, 0.06), new Title("Weltmeister"));
        final Participant oscar = new User("Oscar", new GlickoRating(1500, 350, 0.06), new Title("Weltmeister"));

        for (int i = 0; i < 10; i++) {
            matches.add(createRandomMatch(lesley, oscar));
            matches.add(createRandomMatch(oscar, lesley));
        }

        return matches;
    }

    private Match createRandomMatch(Participant firstParticipant, Participant secondParticipant) {
        Match match = new Match(firstParticipant, secondParticipant, MatchType.FRIENDLY);

        int goalsFirstParticipant = 0;
        int goalsSecondParticipant = 0;

        while (goalsFirstParticipant < 10 && goalsSecondParticipant < 10) {
            boolean first = random.nextBoolean();

            if (first) {
                goalsFirstParticipant += 1;
            } else {
                goalsSecondParticipant += 1;
            }

            Participant participant = first ? firstParticipant : secondParticipant;
            Date date = new Date();

            match.addGoal(new Goal(participant, date));
        }

        return match;
    }
}
