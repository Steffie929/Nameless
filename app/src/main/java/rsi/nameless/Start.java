package rsi.nameless;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Start extends AppCompatActivity{
    private final int NEW_GAME_CODE = 1;
    private final int CREATION_KEY = 4;
    private ImageView endboss, jwlImg, charImg;
    private Animation jwlAnimation, charAnimation, bossAnimation;
    private SharedPreferences savedHighscores, savedGame1;
    private SharedPreferences.Editor savedHighscoresEditor, savedGame1Editor;
    private Button newGame,goToHighscores, loadGame;
    private boolean gameSaved1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Context context = this.getApplicationContext();
        savedHighscores= context.getSharedPreferences("rsi.nameless.highscores", Context.MODE_PRIVATE);
        savedGame1= context.getSharedPreferences("rsi.nameless.savedGame1", Context.MODE_PRIVATE);
        savedGame1Editor = savedGame1.edit();
        savedHighscoresEditor = savedHighscores.edit();

        gameSaved1 = savedGame1.getBoolean("USED", false);


        newGame = (Button) findViewById(R.id.newgame_button);
        goToHighscores = (Button) findViewById(R.id.go_tohighscore);
        loadGame = (Button) findViewById(R.id.loadgame1_button);
        newGame.setVisibility(View.GONE);
        goToHighscores.setVisibility(View.GONE);
        loadGame.setVisibility(View.GONE);
        introAnimation();

    }

    public void introAnimation(){
        endboss = (ImageView) findViewById(R.id.skl_animation);
        bossAnimation = AnimationUtils.loadAnimation(this, R.anim.skl_anim);
        bossAnimation.setFillAfter(true);
        endboss.setVisibility(View.GONE);


        jwlImg = (ImageView) findViewById(R.id.jwl_animation);
        jwlAnimation = AnimationUtils.loadAnimation(this, R.anim.jwl_anim);
        jwlAnimation.setFillAfter(true);
        jwlImg.startAnimation(jwlAnimation);

        charImg = (ImageView) findViewById(R.id.char_imageView);
        charAnimation = AnimationUtils.loadAnimation(this, R.anim.char_anim);
        charAnimation.setFillAfter(true);
        charAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                endboss.setVisibility(View.VISIBLE);
                endboss.startAnimation(bossAnimation);
                newGame.setVisibility(View.VISIBLE);
                loadGame.setVisibility(View.VISIBLE);
                goToHighscores.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        charImg.startAnimation(charAnimation);
    }


    public void startNewGame(View v) {
        if(newGame.getVisibility() == View.GONE)
            return;
        Intent intent = new Intent(this, CreationActivity.class);
        startActivityForResult(intent, CREATION_KEY);
    }

    public void loadGame(View v){
        if(loadGame.getVisibility() == View.GONE)
            return;

        if(!gameSaved1){

            AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
            helpBuilder.setTitle("Failed to load game");
            helpBuilder.setMessage("No saved game was found");
            helpBuilder.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            AlertDialog helpDialog = helpBuilder.create();
            helpDialog.setCancelable(false);
            helpDialog.show();
            return;
        }

        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("NEW_GAME", false);
        startActivityForResult(intent, NEW_GAME_CODE);
    }

    public void goToHighscores(View v){
        if(goToHighscores.getVisibility() == View.GONE)
            return;
        Intent intent = new Intent(this, HighscoreActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        if(action == MotionEvent.ACTION_UP) {
            newGame.setVisibility(View.VISIBLE);
            goToHighscores.setVisibility(View.VISIBLE);
            loadGame.setVisibility(View.VISIBLE);
            introAnimation();
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == NEW_GAME_CODE && resultCode == RESULT_OK){
            int result = data.getIntExtra("PLAYER_SCORE",0);
            Character player = (Character) data.getSerializableExtra("PLAYER_CHARACTER");
            String enemyName = data.getStringExtra("ENEMY_NAME");
            if(enemyName == null)
                enemyName = "Stranger";
            Log.d("SAVE", "ended game, score: " + result);
            String text;
            text = player.getName() + "  " + result + " points\n";
            text+= "Level: " + player.getLevel() + "\t\t\t\tGold: " + player.getGold() + "\n";
            text += "Killed by " + enemyName;
            int index = getScoreIndex(result);

            String key1 = "Score"+index;
            String key2 = "Result"+index;
            Log.d("TAGGED", "stringkey: " + key1 + "   intKey: " + key2);
            savedHighscoresEditor.putString(key1, text);
            savedHighscoresEditor.putInt(key2, result);
            savedHighscoresEditor.commit();

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

    public int getScoreIndex(int result){
        int i=0;

        for(i=0; i<15;i++){
            int score =savedHighscores.getInt("Result"+(i+1), -20);
            Log.d("TAGGED", "score: " + score);
            if(result>score)
                break;
        }
        return i+1;
    }

    public void openTutorial(View v) {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }


}
