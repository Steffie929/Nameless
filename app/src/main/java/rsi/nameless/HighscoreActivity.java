package rsi.nameless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HighscoreActivity extends AppCompatActivity {
    private Highscores highscores;
    private TextView[] tvArray;
    private String[] strArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        highscores = (Highscores) getIntent().getSerializableExtra("OLD_HIGHSCORES");

        tvArray = new TextView[15];
        tvArray[0] = (TextView) findViewById(R.id.score_nr1);
        tvArray[1] = (TextView) findViewById(R.id.score_nr2);
        tvArray[2] = (TextView) findViewById(R.id.score_nr3);
        tvArray[3] = (TextView) findViewById(R.id.score_nr4);
        tvArray[4] = (TextView) findViewById(R.id.score_nr5);
        tvArray[5] = (TextView) findViewById(R.id.score_nr6);
        tvArray[6] = (TextView) findViewById(R.id.score_nr7);
        tvArray[7] = (TextView) findViewById(R.id.score_nr8);
        tvArray[8] = (TextView) findViewById(R.id.score_nr9);
        tvArray[9] = (TextView) findViewById(R.id.score_nr10);
        tvArray[10] = (TextView) findViewById(R.id.score_nr11);
        tvArray[11] = (TextView) findViewById(R.id.score_nr12);
        tvArray[12] = (TextView) findViewById(R.id.score_nr13);
        tvArray[13] = (TextView) findViewById(R.id.score_nr14);
        tvArray[14] = (TextView) findViewById(R.id.score_nr15);

        strArray = highscores.getHighscores();
        for(int i=0; i<15;i++){
            tvArray[i].setText(strArray[i]);
        }

    }

    public void resetHighscores(View v){
        highscores.resetHighscores();
    }
}
