package nl.jft;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public final class MatchPlayActivity extends AppCompatActivity {

    private Button btnPlayerOne;
    private Button btnPlayerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_play);

        assignViews();
    }

    private void assignViews() {
        btnPlayerOne = (Button) findViewById(R.id.match_play_button_player_one);
        btnPlayerTwo = (Button) findViewById(R.id.match_play_button_player_two);
    }

}
