package nl.jft.match;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import nl.jft.Constants;
import nl.jft.R;
import nl.jft.logic.match.Goal;
import nl.jft.logic.match.Match;
import nl.jft.logic.participant.Participant;
import nl.jft.util.MatchUtil;

public final class MatchPlayActivity extends AppCompatActivity implements View.OnClickListener {

    private Match match;

    private TextView txtLabelPlayerOne;
    private TextView txtGoalsPlayerOne;
    private Button btnPlayerOne;

    private TextView txtLabelPlayerTwo;
    private TextView txtGoalsPlayerTwo;
    private Button btnPlayerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_play);

        assignViews();
        assignListeners();
        assignMatch();
    }

    private void assignViews() {
        txtLabelPlayerOne = (TextView) findViewById(R.id.match_play_label_player_one);
        txtGoalsPlayerOne = (TextView) findViewById(R.id.match_play_score_player_one);
        btnPlayerOne = (Button) findViewById(R.id.match_play_button_player_one);

        txtLabelPlayerTwo = (TextView) findViewById(R.id.match_play_label_player_two);
        txtGoalsPlayerTwo = (TextView) findViewById(R.id.match_play_score_player_two);
        btnPlayerTwo = (Button) findViewById(R.id.match_play_button_player_two);
    }

    private void assignListeners() {
        btnPlayerOne.setOnClickListener(this);
        btnPlayerTwo.setOnClickListener(this);
    }

    private void assignMatch() {
        Intent intent = getIntent();
        Match match = (Match) intent.getSerializableExtra("match");

        new GetMatchTask().execute(match.getId());
    }

    private void updateMatchInformation(Match match) {
        this.match = match;

        Participant firstParticipant = match.getFirstParticipant();
        txtLabelPlayerOne.setText(firstParticipant.getName() + ": ");
        txtGoalsPlayerOne.setText(Integer.toString(MatchUtil.getAmountOfGoals(match, firstParticipant)));
        btnPlayerOne.setText(firstParticipant.getName());

        Participant secondParticipant = match.getSecondParticipant();
        txtLabelPlayerTwo.setText(secondParticipant.getName() + ": ");
        txtGoalsPlayerTwo.setText(Integer.toString(MatchUtil.getAmountOfGoals(match, secondParticipant)));
        btnPlayerTwo.setText(secondParticipant.getName());

    }

    @Override
    public void onClick(View view) {
        Participant participant = view == btnPlayerOne ? match.getFirstParticipant() : match.getSecondParticipant();
        participantScored(participant);
    }

    private void participantScored(Participant participant) {
        Goal goal = new Goal(participant, new Date());
        match.addGoal(goal);

        new ScoreGoalTask().execute(match.getId(), participant.getId());
    }

    private class ScoreGoalTask extends AsyncTask<Integer, Void, Match> {

        @Override
        protected Match doInBackground(Integer... params) {
            try {
                String url = String.format("%s/score?match_id=%d&player_id=%d", Constants.REST_HOST, params[0], params[1]);
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
