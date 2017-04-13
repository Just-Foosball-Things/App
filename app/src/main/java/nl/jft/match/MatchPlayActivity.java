package nl.jft.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Date;

import nl.jft.R;
import nl.jft.logic.match.Goal;
import nl.jft.logic.match.Match;
import nl.jft.logic.participant.Participant;

public final class MatchPlayActivity extends AppCompatActivity implements View.OnClickListener {

    private Match match;

    private Button btnPlayerOne;
    private Button btnPlayerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_play);

        assignMatch();
        assignViews();
        assignListeners();
    }

    private void assignMatch() {
        Intent intent = getIntent();
        match = (Match) intent.getSerializableExtra("match");
    }

    private void assignViews() {
        btnPlayerOne = (Button) findViewById(R.id.match_play_button_player_one);
        btnPlayerTwo = (Button) findViewById(R.id.match_play_button_player_two);
    }

    private void assignListeners() {
        btnPlayerOne.setOnClickListener(this);
        btnPlayerTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Participant participant = view == btnPlayerOne ? match.getFirstParticipant() : match.getSecondParticipant();
        participantScored(participant);
    }

    private void participantScored(Participant participant) {
        Goal goal = new Goal(participant, new Date());
        match.addGoal(goal);

        System.out.println("Goal added for " + participant.getName());
    }
}
