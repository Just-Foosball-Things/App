package nl.jft.overview.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.Date;
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
import nl.jft.match.MatchActivity;
import nl.jft.widget.AnimatedExpandableListView;
import nl.jft.widget.match.MatchesOverviewListAdapter;

/**
 * @author Oscar de Leeuw.
 */

public class MatchesFragment extends CustomFragment {

    private static Random random = new Random();

    private static Match createRandomMatch(Participant firstParticipant, Participant secondParticipant) {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_matches, container, false);
        AnimatedExpandableListView listView = (AnimatedExpandableListView) rootView.findViewById(R.id.overview_matches_listview);

        final MatchesOverviewListAdapter adapter = new MatchesOverviewListAdapter(inflater.getContext());

        final Participant lesley = new User("Lesley", new GlickoRating(1500, 350, 0.06), new Title("Weltmeister"));
        final Participant oscar = new User("Oscar", new GlickoRating(1500, 350, 0.06), new Title("Weltmeister"));

        Match match1 = createRandomMatch(lesley, oscar);
        Match match2 = createRandomMatch(oscar, lesley);

        adapter.addMatch(match1);
        adapter.addMatch(match2);

        listView.setAdapter(adapter);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Intent intent = new Intent(v.getContext(), MatchActivity.class);
                startActivity(intent);
                return true;
            }
        });

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(v.getContext(), MatchActivity.class);
                startActivity(intent);
                return true;
            }
        });

        return rootView;
    }

    @Override
    public CharSequence getTitle() {
        return "Matches";
    }
}
