package nl.jft.match.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import nl.jft.CustomFragment;
import nl.jft.R;
import nl.jft.logic.match.Goal;
import nl.jft.logic.match.Match;
import nl.jft.logic.participant.impl.User;
import nl.jft.match.MatchPlayActivity;
import nl.jft.util.MatchUtil;
import nl.jft.widget.badge.Badge;
import nl.jft.widget.timeline.GoalClickedListener;
import nl.jft.widget.timeline.GoalTimelineView;

/**
 * @author Oscar de Leeuw.
 */

public class MatchOverviewFragment extends CustomFragment {

    public static final String EXTRA_MATCH_ARGUMENT = "match";

    private Match match;

    private GoalTimelineView timelineGoal;
    private Button btnPlay;

    private Badge badgeFirstParticipant;
    private TextView txtGoalsFirstParticipant;

    private Badge badgeSecondParticipant;
    private TextView txtGoalsSecondParticipant;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_match_overview, container, false);

        assignMatch();
        assignViews(root);

        initializeLabels();
        initializeBadges();

        initializeTimelineGoal(root);
        initializeButtonPlay();

        return root;
    }

    private void assignMatch() {
        Intent intent = getActivity().getIntent();
        match = (Match) intent.getSerializableExtra(EXTRA_MATCH_ARGUMENT);
    }

    private void assignViews(View root) {
        timelineGoal = (GoalTimelineView) root.findViewById(R.id.match_timeline_goals);
        btnPlay = (Button) root.findViewById(R.id.match_overview_btn_play);

        badgeFirstParticipant = (Badge) root.findViewById(R.id.match_badge_profile_first);
        txtGoalsFirstParticipant = (TextView) root.findViewById(R.id.match_text_goals_first);

        badgeSecondParticipant = (Badge) root.findViewById(R.id.match_badge_profile_second);
        txtGoalsSecondParticipant = (TextView) root.findViewById(R.id.match_text_goals_second);
    }

    private void initializeLabels() {
        int firstGoals = MatchUtil.getAmountOfGoals(match, match.getFirstParticipant());
        txtGoalsFirstParticipant.setText(Integer.toString(firstGoals));

        int secondGoals = MatchUtil.getAmountOfGoals(match, match.getSecondParticipant());
        txtGoalsSecondParticipant.setText(Integer.toString(secondGoals));
    }

    private void initializeBadges() {
        initializeBadge(badgeFirstParticipant, (User) match.getFirstParticipant());
        initializeBadge(badgeSecondParticipant, (User) match.getSecondParticipant());
    }

    private void initializeBadge(Badge badge, User participant) {
        badge.setName(participant.getName());
        badge.setTitle(participant.getActiveTitle().getName());
    }

    private void initializeTimelineGoal(final View root) {
        timelineGoal.setMatch(match);
        timelineGoal.setOnGoalClickedListener(new GoalClickedListener() {
            @Override
            public void onGoalClicked(Match match, Goal goal) {
                String participant = goal.getParticipant().getName();
                String time = new SimpleDateFormat("HH:mm:ss:SSS", Locale.UK).format(goal.getTime());
                String text = String.format("Participant: %s\nTime: %s", participant, time);

                Snackbar snackbar = Snackbar.make(root, text, Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });
    }

    private void initializeButtonPlay() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();

                Intent intent = new Intent(context, MatchPlayActivity.class);
                intent.putExtra("match", match);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public CharSequence getTitle() {
        return "Overview";
    }
}
