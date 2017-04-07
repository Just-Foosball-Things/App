package nl.jft.match.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.Locale;

import nl.jft.CustomFragment;
import nl.jft.R;
import nl.jft.logic.match.Goal;
import nl.jft.logic.match.Match;
import nl.jft.widget.timeline.GoalClickedListener;
import nl.jft.widget.timeline.GoalTimelineView;

/**
 * @author Oscar de Leeuw.
 */

public class MatchOverviewFragment extends CustomFragment {

    public static final String EXTRA_MATCH_ARGUMENT = "match";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_match_overview, container, false);

        // TODO: Actually get match from intent here
        Match match = getMatch();

        GoalTimelineView timeline = (GoalTimelineView) rootView.findViewById(R.id.match_timeline_goals);
        timeline.setMatch(match);
        timeline.setOnGoalClickedListener(new GoalClickedListener() {
            @Override
            public void onGoalClicked(Match match, Goal goal) {
                String participant = goal.getParticipant().getName();
                String time = new SimpleDateFormat("HH:mm:ss:SSS", Locale.UK).format(goal.getTime());
                String text = String.format("Participant: %s\nTime: %s", participant, time);

                Snackbar snackbar = Snackbar.make(rootView, text, Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        return rootView;
    }

    private Match getMatch() {
        Intent intent = getActivity().getIntent();
        Match match = (Match) intent.getSerializableExtra(EXTRA_MATCH_ARGUMENT);

        return match;
    }

    @Override
    public CharSequence getTitle() {
        return "Overview";
    }
}
