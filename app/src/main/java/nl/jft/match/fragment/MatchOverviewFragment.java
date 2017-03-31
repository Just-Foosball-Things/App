package nl.jft.match.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
import nl.jft.widget.timeline.GoalClickedListener;
import nl.jft.widget.timeline.GoalTimelineView;

/**
 * @author Oscar de Leeuw.
 */

public class MatchOverviewFragment extends CustomFragment {

    private static final Random random = new Random();

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
        Participant lesley = new User("Lesley", new GlickoRating(1500, 350, 0.06), new Title("Weltmeister"));
        Participant oscar = new User("Oscar", new GlickoRating(1500, 350, 0.06), new Title("Weltmeister"));

        return createRandomMatch(lesley, oscar);
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

    @Override
    public CharSequence getTitle() {
        return "Overview";
    }
}
