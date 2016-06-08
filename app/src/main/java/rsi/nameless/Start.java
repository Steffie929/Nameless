package rsi.nameless;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Start extends AppCompatActivity{
    private final int NEW_GAME_CODE = 1;
    private final int CREATION_KEY = 4;
    private ImageView endboss, jwlImg, charImg;
    private Animation jwlAnimation, charAnimation, bossAnimation;
    private SharedPreferences savedHighscores, savedGame1, savedGame2, savedGame3;
    private SharedPreferences.Editor savedHighscoresEditor, savedGame1Editor, savedGame2Editor, savedGame3Editor;
    private Button newGame,goToHighscores, loadGame, saveSlot1, saveSlot2, saveSlot3, clearSaves;
    private ImageButton tutorialButton;
    private boolean gameSaved1, gameSaved2, gameSaved3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Context context = this.getApplicationContext();
        savedHighscores= context.getSharedPreferences("rsi.nameless.highscores", Context.MODE_PRIVATE);
        savedGame1= context.getSharedPreferences("rsi.nameless.savedGame1", Context.MODE_PRIVATE);
        savedGame1Editor = savedGame1.edit();
        savedGame2= context.getSharedPreferences("rsi.nameless.savedGame2", Context.MODE_PRIVATE);
        savedGame2Editor = savedGame2.edit();
        savedGame3= context.getSharedPreferences("rsi.nameless.savedGame3", Context.MODE_PRIVATE);
        savedGame3Editor = savedGame3.edit();
        savedHighscoresEditor = savedHighscores.edit();

        newGame = (Button) findViewById(R.id.newgame_button);
        goToHighscores = (Button) findViewById(R.id.go_tohighscore);
        loadGame = (Button) findViewById(R.id.loadgame1_button);
        saveSlot1 = (Button) findViewById(R.id.saveslot1_button);
        saveSlot2 = (Button) findViewById(R.id.saveslot2_button);
        saveSlot3 = (Button) findViewById(R.id.saveslot3_button);
        clearSaves = (Button) findViewById(R.id.clearSaveslots);
        tutorialButton = (ImageButton) findViewById(R.id.imageButton);
        newGame.setVisibility(View.GONE);
        goToHighscores.setVisibility(View.GONE);
        loadGame.setVisibility(View.GONE);
        saveSlot1.setVisibility(View.GONE);
        saveSlot2.setVisibility(View.GONE);
        saveSlot3.setVisibility(View.GONE);
        clearSaves.setVisibility(View.GONE);
        tutorialButton.setVisibility(View.GONE);

        setSavedTexts();
        introAnimation();

    }

    /**
     * Perform the introductory animation
     * Make the invisible buttons visible after the animation ends
     */
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
                tutorialButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        charImg.startAnimation(charAnimation);
    }

    /**
     * Starts a CreationActivity, which allows you to start a new game
     */
    public void startNewGame(View v) {
        if(newGame.getVisibility() == View.GONE)
            return;
        Intent intent = new Intent(this, CreationActivity.class);
        startActivityForResult(intent, CREATION_KEY);
    }

    /**
     * Display the save slot buttons and the button that allows you to clear all save files
     */
    public void loadGame(View v){
        if(loadGame.getVisibility() == View.GONE)
            return;

        setSavedTexts();
        saveSlot1.setVisibility(View.VISIBLE);
        saveSlot2.setVisibility(View.VISIBLE);
        saveSlot3.setVisibility(View.VISIBLE);
        clearSaves.setVisibility(View.VISIBLE);
    }

    /**
     * Change the text on the save buttons to the most up to date version
     */
    private void setSavedTexts() {
        gameSaved1 = savedGame1.getBoolean("USED", false);
        gameSaved2 = savedGame2.getBoolean("USED", false);
        gameSaved3 = savedGame3.getBoolean("USED", false);
        if(gameSaved1) {
            String playername1 = savedGame1.getString("PLAYER_NAME", "Game saved");
            saveSlot1.setText("Slot 1: " + playername1);
        } else {
            saveSlot1.setText("Slot 1: Empty");
        }
        if(gameSaved2) {
            String playername2 = savedGame2.getString("PLAYER_NAME", "Game saved");
            saveSlot2.setText("Slot 2: " + playername2);
        } else {
            saveSlot2.setText("Slot 2: Empty");
        }
        if(gameSaved3) {
            String playername3 = savedGame3.getString("PLAYER_NAME", "Game saved");
            saveSlot3.setText("Slot 3: " + playername3);
        } else {
            saveSlot3.setText("Slot 3: Empty");
        }
    }

    /**
     * Delete all existing save data
     */
    public void clearSaveslots (View v) {
        savedGame1Editor.clear();
        savedGame1Editor.commit();
        savedGame2Editor.clear();
        savedGame2Editor.commit();
        savedGame3Editor.clear();
        savedGame3Editor.commit();
        setSavedTexts();
    }

    /**
     * Load the game that is saved in save slot 1
     * Starts a MapAcitivy, which is the activity in which the save data was made
     */
    public void clickedSaveSlot1(View v){
        if(!gameSaved1){
            Toast.makeText(getApplicationContext(), "There is no saved game in this slot ", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("NEW_GAME", false);
        intent.putExtra("SELECTED_SLOT", 1);
        startActivityForResult(intent, NEW_GAME_CODE);
    }

    /**
     * Load the game that is saved in save slot 2
     * Starts a MapAcitivy, which is the activity in which the save data was made
     */
    public void clickedSaveSlot2(View v){
        if(!gameSaved2){
            Toast.makeText(getApplicationContext(), "There is no saved game in this slot ", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("NEW_GAME", false);
        intent.putExtra("SELECTED_SLOT", 2);
        startActivityForResult(intent, NEW_GAME_CODE);
    }

    /**
     * Load the game that is saved in save slot 3
     * Starts a MapAcitivy, which is the activity in which the save data was made
     */
    public void clickedSaveSlot3(View v){
        if(!gameSaved3){
            Toast.makeText(getApplicationContext(), "There is no saved game in this slot ", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("NEW_GAME", false);
        intent.putExtra("SELECTED_SLOT", 3);
        startActivityForResult(intent, NEW_GAME_CODE);
    }

    /**
     * Open the High scores screen (Located in HighscoreActivity
     */
    public void goToHighscores(View v){
        if(goToHighscores.getVisibility() == View.GONE)
            return;
        Intent intent = new Intent(this, HighscoreActivity.class);
        startActivity(intent);
    }

    /**
     * Make the buttons that were hidden because of the opening animation visible
     * Hide the buttons that are related to loading save data
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        if(action == MotionEvent.ACTION_UP) {
            newGame.setVisibility(View.VISIBLE);
            goToHighscores.setVisibility(View.VISIBLE);
            loadGame.setVisibility(View.VISIBLE);
            tutorialButton.setVisibility(View.VISIBLE);
            introAnimation();

            if(saveSlot1.getVisibility()== View.VISIBLE) {
                saveSlot1.setVisibility(View.GONE);
                saveSlot2.setVisibility(View.GONE);
                saveSlot3.setVisibility(View.GONE);
                clearSaves.setVisibility(View.GONE);
            }

        }
        return false;
    }

    /**
     * If this method was called from a CreationActivty (CREATION_KEY), start a new game
     * If this method was called from a MapActivity (NEW_GAME_CODE), save the highscore data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == NEW_GAME_CODE && resultCode == RESULT_OK){
            int result = data.getIntExtra("PLAYER_SCORE",0);
            Character player = (Character) data.getSerializableExtra("PLAYER_CHARACTER");
            String enemyName = data.getStringExtra("ENEMY_NAME");
            if(enemyName == null)
                enemyName = "Nameless";
            Log.d("SAVE", "ended game, score: " + result);
            String text;
            text = player.getName() + "  " + result + " points\n";
            text+= "Level: " + player.getLevel() + "\t\t\t\tGold: " + player.getGold() + "\n";
            if (player.getCurrentHP() <= 0) {
                text += "Killed by " + enemyName;
            } else {
                text += "Game Completed!";
            }
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

    /**
     * Open the tutorial, located under TutorialActivity
     */
    public void openTutorial(View v) {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }


}
