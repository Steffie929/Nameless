package rsi.nameless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Start extends AppCompatActivity {
    private final int NEW_GAME_CODE = 1;
    private final int CREATION_KEY = 4;
    private Highscores highscores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        highscores = new Highscores();
    }


    public void startNewGame(View v) {
        Intent intent = new Intent(this, CreationActivity.class);
        startActivityForResult(intent, CREATION_KEY);
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
            Character player = (Character) data.getSerializableExtra("PLAYER_CHARACTER");
            String enemyName = data.getStringExtra("ENEMY_NAME");
            highscores.addScore(result, player, enemyName);
            Log.d("DEBUG", "ended game, score: " + result);
        }
        else if (requestCode == CREATION_KEY && resultCode == RESULT_OK) {
            Intent intent = new Intent(this, MapActivity.class);
            int hpMod = data.getIntExtra("HP_MOD", 3);
            int strMod = data.getIntExtra("STR_MOD", 3);
            int defMod = data.getIntExtra("DEF_MOD", 3);
            int sklMod = data.getIntExtra("SKL_MOD", 3);
            int spdMod = data.getIntExtra("SPD_MOD", 3);
            String playerName = data.getStringExtra("PLAYER_NAME");
            intent.putExtra("HP_MOD", hpMod);
            intent.putExtra("STR_MOD", strMod);
            intent.putExtra("DEF_MOD", defMod);
            intent.putExtra("SKL_MOD", sklMod);
            intent.putExtra("SPD_MOD", spdMod);
            intent.putExtra("PLAYER_NAME", playerName);
            startActivityForResult(intent, NEW_GAME_CODE);
        }
    }



}
