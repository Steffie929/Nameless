package rsi.nameless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Start extends AppCompatActivity {
    private final int NEW_GAME_CODE = 1;
    private Highscores highscores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        highscores = new Highscores();
    }


    public void startNewGame(View v){
        Intent intent = new Intent(this, MapActivity.class);
        EditText editText = (EditText) findViewById(R.id.choosePlayerName);
        String message = editText.getText().toString();
        intent.putExtra("PLAYER_NAME", message);
        startActivityForResult(intent, NEW_GAME_CODE);
    }

    public void goToHighscores(View v){
        Intent intent = new Intent(this, HighscoreActivity.class);
        intent.putExtra("OLD_HIGHSCORES", highscores);
        startActivity(intent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == NEW_GAME_CODE && resultCode == RESULT_OK){
            int result = data.getIntExtra("PLAYER_SCORE",0);
            Character player = (Character)data.getSerializableExtra("PLAYER_CHARACTER");
            highscores.addScore(result, player);
            Log.d("DEBUG", "ended game, score: " + result);
        }
    }



}
