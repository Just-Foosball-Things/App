package nl.jft.match.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Locale;

import nl.jft.Constants;
import nl.jft.CustomFragment;
import nl.jft.R;
import nl.jft.logic.match.Goal;
import nl.jft.logic.match.Match;
import nl.jft.logic.participant.LocationJft;
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
    private Participant participant;

    private GoalTimelineView timelineGoal;
    private Button btnPlay;
    private Button btnLocation;

    private Badge badgeFirstParticipant;
    private TextView txtGoalsFirstParticipant;
    private TextView txtRatingFirstParticipant;
    private TextView txtRatingDifferenceFirstParticipant;

    private Badge badgeSecondParticipant;
    private TextView txtGoalsSecondParticipant;
    private TextView txtRatingSecondParticipant;
    private TextView txtRatingDifferenceSecondParticipant;

    private LocationManager locationManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_match_overview, container, false);

        assignViews();
        assignMatch();

        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

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
        btnLocation = (Button) rootView.findViewById(R.id.match_overview_btn_temp);

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
        this.participant = match.getFirstParticipant();

        initializeLabels(match);
        initializeBadges(match);
        initializeTimelineGoal(match);
        initializeButtonPlay(match);
        initializeButtonTemp();
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
                new GetParticipantTask().execute(match.getSecondParticipant().getId());
                /*Context context = getContext();

                Intent intent = new Intent(context, MatchPlayActivity.class);
                intent.putExtra("match", match);
                context.startActivity(intent);*/
            }
        });
    }

    private void initializeButtonTemp() {
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    saveCurrentLocation(match.getSecondParticipant(), location);
                } catch (SecurityException e) {
                    Log.e("Alles is fatoe", e.getMessage(), e);
                    Toast toast = Toast.makeText(getContext(), "Failed to get location", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private void startMatch(Participant other) {
        float[] result = new float[3];

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 50);
        }

        try {
            LocationJft otherLocation = other.getLocation();
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            saveCurrentLocation(participant, location);
            Location.distanceBetween(location.getLatitude(), location.getLongitude(), otherLocation.latitude, otherLocation.longitude, result);
        } catch (SecurityException e) {
            Log.e("Alles is fatoe", e.getMessage(), e);
        }

        if (result[0] < 50) {
            Context context = getContext();
            Intent intent = new Intent(context, MatchPlayActivity.class);
            intent.putExtra("match", match);
            context.startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getContext(), "You are too far away! " + result[0] + " meters!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void saveCurrentLocation(Participant participant, Location location) {
        new SaveLocationTask().execute((double) participant.getId(), location.getLongitude(), location.getLatitude());
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

    private class GetParticipantTask extends AsyncTask<Integer, Void, Participant> {

        @Override
        protected Participant doInBackground(Integer... params) {
            try {
                String url = String.format("%s/player?player_id=%d", Constants.REST_HOST, params[0]);
                RestTemplate template = new RestTemplate();
                template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                Participant participant = template.getForObject(url, Participant.class);
                return participant;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Participant participant) {
            startMatch(participant);
        }
    }

    private class SaveLocationTask extends AsyncTask<Double, Void, Void> {
        @Override
        protected Void doInBackground(Double... params) {
            try {
                int player_id = (int) Math.floor(params[0]);

                String url = String.format("%s/setlocation?player_id=%d&longitude=%f&latitude=%f", Constants.REST_HOST, player_id, params[1], params[2]);
                RestTemplate template = new RestTemplate();
                template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                Participant participant = template.getForObject(url, Participant.class);

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }
    }
}
