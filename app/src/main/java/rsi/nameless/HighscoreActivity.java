package rsi.nameless;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HighscoreActivity extends AppCompatActivity {
    private TextView[] tvArray;
    private String[] strArray;
    private int[] scores;
    private SharedPreferences savedHighscores;
    private SharedPreferences.Editor savedHighscoresEditor;

    /**
     * This method creates and fills tvArray with the TextViews on the screen and calls 2 other methods
     * to fill them.     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

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

        Context context = this.getApplicationContext();
        savedHighscores= context.getSharedPreferences("rsi.nameless.highscores", Context.MODE_PRIVATE);
        savedHighscoresEditor = savedHighscores.edit();

        getInfoFromPrefs();
        setTVs();
    }

    /**
     * This method gets information from SharedPreferences and stores it in a string array and an int array.
     */
    public void getInfoFromPrefs(){
        strArray = new String[15];
        scores = new int[15];

        for(int i=0;i<15;i++){
            scores[i] = 0;
            strArray[i] ="";
        }

        for(int i=1; i<=15; i++) {
            String resultKey = "Result" + i;
            String textKey = "Score" + i;
            int result;
            String text;
            result = savedHighscores.getInt(resultKey, -20);
            text = savedHighscores.getString(textKey, "");

            if (result == -20) {
                break;
            }

           insertInArrays(result, text, i - 1);
        }
    }

    /**
     * This method uses the stringarray to change the text of the right textviews in the tVarray.
     */
    public void setTVs(){
        for(int i=0; i<15;i++){
            tvArray[i].setText( (i+1) +". " + strArray[i]);
        }
    }

    public void back(View v){
        finish();
    }

    public int getScoreIndex(int result){
        int i=0;

        for(i =0; i< scores.length; i++){
            if(result> scores[i]){
                break;
            }
        }
        return i+1;
    }

    /**
     * This method inserts a String and score into the array at position 'index'
     * and moves the other values to make it fit.
     * @param result
     * @param text
     * @param index
     */
    public void insertInArrays(int result, String text, int index){
        for(int i= 14;i> index; i--){
            if(strArray[i] == i + "")
                scores[i] = scores[i-1];
            strArray[i] = strArray[i-1];
        }
        scores[index] = result;
        strArray[index] = text;
    }


    /**
     * Clears the sharedPreferences and thereby clears the highscores and updates the view.
     * @param v
     */
    public void resetHighscores(View v){
        savedHighscoresEditor.clear();
        savedHighscoresEditor.commit();
        getInfoFromPrefs();
        setTVs();
    }
}
