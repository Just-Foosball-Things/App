package nl.jft.match.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Locale;

import nl.jft.Constants;
import nl.jft.CustomFragment;
import nl.jft.R;
import nl.jft.logic.match.Goal;
import nl.jft.logic.match.Match;
import nl.jft.logic.participant.Participant;
import nl.jft.logic.participant.impl.User;
import nl.jft.match.MatchPlayActivity;
import nl.jft.util.LabelUtil;
import nl.jft.widget.badge.Badge;
import nl.jft.widget.timeline.GoalClickedListener;
import nl.jft.widget.timeline.GoalTimelineView;

/**
 * @author Oscar de Leeuw.
 */

public class MatchOverviewFragment extends CustomFragment {

    public static final String EXTRA_MATCH_ARGUMENT = "match";

    private View rootView;
    private Match match;

    private GoalTimelineView timelineGoal;
    private Button btnPlay;

    private Badge badgeFirstParticipant;
    private TextView txtGoalsFirstParticipant;
    private TextView txtRatingFirstParticipant;
    private TextView txtRatingDifferenceFirstParticipant;

    private Badge badgeSecondParticipant;
    private TextView txtGoalsSecondParticipant;
    private TextView txtRatingSecondParticipant;
    private TextView txtRatingDifferenceSecondParticipant;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_match_overview, container, false);

        assignViews();
        assignMatch();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (match == null) {
            return;
        }

        new GetMatchTask().execute(match.getId());
    }

    private void assignMatch() {
        Intent intent = getActivity().getIntent();
        Match match = (Match) intent.getSerializableExtra(EXTRA_MATCH_ARGUMENT);

        new GetMatchTask().execute(match.getId());
    }

    private void assignViews() {
        timelineGoal = (GoalTimelineView) rootView.findViewById(R.id.match_timeline_goals);
        btnPlay = (Button) rootView.findViewById(R.id.match_overview_btn_play);

        badgeFirstParticipant = (Badge) rootView.findViewById(R.id.match_badge_profile_first);
        txtGoalsFirstParticipant = (TextView) rootView.findViewById(R.id.match_text_goals_first);
        txtRatingFirstParticipant = (TextView) rootView.findViewById(R.id.match_text_rating_first);
        txtRatingDifferenceFirstParticipant = (TextView) rootView.findViewById(R.id.match_text_rating_difference_first);

        badgeSecondParticipant = (Badge) rootView.findViewById(R.id.match_badge_profile_second);
        txtGoalsSecondParticipant = (TextView) rootView.findViewById(R.id.match_text_goals_second);
        txtRatingSecondParticipant = (TextView) rootView.findViewById(R.id.match_text_rating_second);
        txtRatingDifferenceSecondParticipant = (TextView) rootView.findViewById(R.id.match_text_rating_difference_second);
    }

    private void updateMatchInformation(Match match) {
        this.match = match;

        initializeLabels(match);
        initializeBadges(match);
        initializeTimelineGoal(match);
        initializeButtonPlay(match);
    }

    private void initializeLabels(Match match) {
        Participant firstParticipant = match.getFirstParticipant();
        txtGoalsFirstParticipant.setText(LabelUtil.getGoalsSpannable(match, firstParticipant));
        txtRatingFirstParticipant.setText(LabelUtil.getRatingSpannable(match, firstParticipant));
        txtRatingDifferenceFirstParticipant.setText(LabelUtil.getRatingDifferenceSpannable(match, firstParticipant));

        Participant secondParticipant = match.getSecondParticipant();
        txtGoalsSecondParticipant.setText(LabelUtil.getGoalsSpannable(match, secondParticipant));
        txtRatingSecondParticipant.setText(LabelUtil.getRatingSpannable(match, secondParticipant));
        txtRatingDifferenceSecondParticipant.setText(LabelUtil.getRatingDifferenceSpannable(match, secondParticipant));
    }

    private void initializeBadges(Match match) {
        initializeBadge(badgeFirstParticipant, (User) match.getFirstParticipant());
        initializeBadge(badgeSecondParticipant, (User) match.getSecondParticipant());
    }

    private void initializeBadge(Badge badge, User participant) {
        badge.setName(participant.getName());
        badge.setTitle(participant.getActiveTitle().getName());
    }

    private void initializeTimelineGoal(Match match) {
        timelineGoal.setMatch(match);
        timelineGoal.setOnGoalClickedListener(new GoalClickedListener() {
            @Override
            public void onGoalClicked(Match match, Goal goal) {
                String participant = goal.getParticipant().getName();
                String time = new SimpleDateFormat("HH:mm:ss:SSS", Locale.UK).format(goal.getTime());
                String text = String.format("Participant: %s\nTime: %s", participant, time);

                Snackbar snackbar = Snackbar.make(rootView, text, Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });
    }

    private void initializeButtonPlay(final Match match) {
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


    private class GetMatchTask extends AsyncTask<Integer, Void, Match> {

        @Override
        protected Match doInBackground(Integer... params) {
            try {
                String url = String.format("%s/match?match_id=%d", Constants.REST_HOST, params[0]);
                RestTemplate template = new RestTemplate();
                template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                Match match = template.getForObject(url, Match.class);
                return match;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Match match) {
            updateMatchInformation(match);
        }

    }
}
