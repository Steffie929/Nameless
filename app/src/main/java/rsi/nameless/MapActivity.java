package rsi.nameless;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;

public class MapActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private CanvasMap drawView;
    private GestureDetectorCompat gDetector;
    private SharedPreferences savedGame1, savedGame2, savedGame3;
    private SharedPreferences.Editor savedGame1Editor, savedGame2Editor, savedGame3Editor;
    private Map map;
    private MainModel m;
    private Character player;

    private final int SHOP_KEY = 4;
    private final int BATTLE_KEY = 7;
    private final int BACKPACK_KEY = 37;
    private final int CONVERSATION_KEY = 42;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Context context = this.getApplicationContext();
        savedGame1= context.getSharedPreferences("rsi.nameless.savedGame1", Context.MODE_PRIVATE);
        savedGame1Editor = savedGame1.edit();
        savedGame2= context.getSharedPreferences("rsi.nameless.savedGame2", Context.MODE_PRIVATE);
        savedGame2Editor = savedGame2.edit();
        savedGame3= context.getSharedPreferences("rsi.nameless.savedGame3", Context.MODE_PRIVATE);
        savedGame3Editor = savedGame3.edit();
        Intent intent = getIntent();

        if(!intent.getBooleanExtra("NEW_GAME", true)) {
                int nr = intent.getIntExtra("SELECTED_SLOT", 1);
                loadGame(nr);
            return;
        }

        String playerName = intent.getStringExtra("PLAYER_NAME");
        int hpMod = intent.getIntExtra("HP_MOD", 3);
        int strMod = intent.getIntExtra("STR_MOD", 3);
        int defMod = intent.getIntExtra("DEF_MOD", 3);
        int sklMod = intent.getIntExtra("SKL_MOD", 3);
        int spdMod = intent.getIntExtra("SPD_MOD", 3);
        this.m = new MainModel(playerName, hpMod, strMod, defMod, sklMod, spdMod);
        drawView = (CanvasMap) findViewById(R.id.view);
        this.gDetector = new GestureDetectorCompat(this,this);
        map = m.getMap(1);
        player = m.getPlayer();
        map.setPlayer(player);

        startLevel();
    }

    /**
     * Start the conversation associated with the current map
     */
    public void startLevel(){
        drawView.setMap(map);
        Conversation conv = map.getConversation();
        startConversation(conv);
    }

    /**
     * Start a conversation activity based on a Conversation object
     * @param conv the conversation that should be started
     */
    public void startConversation(Conversation conv){
        Intent intent = new Intent(this, npcActivity.class);
        intent.putExtra("CURRENT_CONVERSATION", conv);
        intent.putExtra("CURRENT_PLAYER", player);
        startActivityForResult(intent, CONVERSATION_KEY);
        drawView.invalidate();
    }

    /**
     * Start a battle activity
     * @param battle the battle that should be played out in a battle activity
     */
    public void startBattleActivity(Battle battle){
        Intent intent = new Intent(this, BattleActivity.class);
        intent.putExtra("CURRENT_BATTLE", battle);
        startActivityForResult(intent, BATTLE_KEY);
        drawView.invalidate();
    }

    /**
     * Start a shop activity
     * @param shop the shop that sells stuff in the shop activity
     */
    public void startShopActivity(Shop shop){
        Intent intent = new Intent(this, ShopActivity.class);
        intent.putExtra("CURRENT_SHOP", shop);
        intent.putExtra("CURRENT_PLAYER", player);
        startActivityForResult(intent, SHOP_KEY);
        drawView.invalidate();
    }

    /**
     * Get a result when returning from another activity that was started with startActivityForResult
     * RequestCodes: BattleActivity --> BATTLE_KEY = 7
     * @param requestCode the requestCode that was sent
     * @param resultCode to see if the result was ok, indicated by RESULT_OK
     * @param data the Intent that was sent back by the other Activity
     */
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode == BATTLE_KEY || requestCode == CONVERSATION_KEY) {
            if (resultCode == RESULT_OK) {
                this.player = (Character) data.getSerializableExtra("Character_Key");
                map.setPlayer(player);
                if (player.getCurrentHP() <= 0) {
                    final String enemyName = data.getStringExtra("Enemy_Name");
                    AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
                    helpBuilder.setTitle("You died");
                    helpBuilder.setMessage("");
                    helpBuilder.setPositiveButton("Return to Main Menu",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    int score = player.getScore();
                                    Intent returnIntent = new Intent();
                                    returnIntent.putExtra("PLAYER_SCORE",score);
                                    returnIntent.putExtra("PLAYER_CHARACTER", player);
                                    returnIntent.putExtra("ENEMY_NAME", enemyName);
                                    setResult(Activity.RESULT_OK,returnIntent);
                                    finish();
                                }
                            });
                    AlertDialog helpDialog = helpBuilder.create();
                    helpDialog.setCancelable(false);
                    helpDialog.show();
                }
                else if(map.getCurrentPoint() ==0 && map.getLevel() !=3){
                    map = m.getMap(map.getLevel()+1);
                    map.setCurrentPoint(8);
                    Item questItem = m.getItemLibrary().getQuestItem(0);
                    player.addItemToBackpack(questItem);
                    map.setPlayer(player);
                    startLevel();
                }
                else if(map.getCurrentPoint() ==0 && map.getLevel() ==3) {
                    int score = player.getScore();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("PLAYER_SCORE",score);
                    returnIntent.putExtra("PLAYER_CHARACTER", player);
                    returnIntent.putExtra("ENEMY_NAME", "");
                    setResult(Activity.RESULT_OK,returnIntent);

                    Intent intent = new Intent(this, EndActivity.class);
                    startActivity(intent);
                }
            }
        }
        if (requestCode == SHOP_KEY || requestCode == BACKPACK_KEY) {
            if (resultCode == RESULT_OK) {
                player = (Character) data.getSerializableExtra("Character_Key");
                map.setPlayer(player);
            }
        }
    }

    /**
     * Open the players backpack in a BackpackActivity
     */
    public void openBackpack(View v){
        Intent intent = new Intent(this, BackpackActivity.class);
        intent.putExtra("CURRENT_PLAYER", map.getPlayer());
        intent.putExtra("FROM_BATTLE_BOOLEAN", false);
        startActivityForResult(intent, BACKPACK_KEY);
    }

    /**
     * Open the characer stats screen
     */
    public void openStatScreen(View v){
        Intent intent = new Intent(this, CharacterScreen.class);
        intent.putExtra("CURRENT_PLAYER", map.getPlayer());
        startActivity(intent);
    }

    /**
     * Try to move based on where the player tapped the screen
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        float x,y;
        x = e.getX()-40;
        y = e.getY()-250;
        Log.d("DEBUG", "MapActivity, onSingleTap\n\t\tclicked on:\tgetX() " + x + " " + y);


        Move move = new Move(map, drawView, x, y);
        if(!move.tryMove()){
            return false;
        }

        move();
        return false;
    }

    /**
     * Move the player, start an activity that takes place on the new point
     * Change the possible activity that can take place in the new position of the player to empty
     * after the player has completed the activity, so the player cannot do the same thing twice
     */
    public void move(){
        drawView.invalidate();

        if(map.inBattle()){
            Battle currentBattle = (Battle) map.getEvent(map.getCurrentPoint());
            startBattleActivity(currentBattle);
        }
        else if(map.inShop()){
            Shop currentShop = (Shop) map.getEvent(map.getCurrentPoint());
            currentShop.setPlayer(map.getPlayer());
            startShopActivity(currentShop);
        }
        map.setEvent(map.getCurrentPoint(), Map.eventType.EMPTY);
    }

    /**
     * Prompt a pop-up window which lets the player choose the save slot
     * Save the game to the specified save slot
     */
    public void saveCurrentGame(View v){

        String[] choices = {"Save Slot 1", "Save Slot 2", "Save Slot 3"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Save Game")
                .setItems(choices, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Gson gson = new Gson();
                        String json = "";

                        switch(which){
                            case 0:
                                json = gson.toJson(player);
                                savedGame1Editor.putString("SAVED_PLAYER", json);
                                savedGame1Editor.commit();

                                json = gson.toJson(map);
                                savedGame1Editor.putString("SAVED_MAP", json);
                                savedGame1Editor.commit();

                                savedGame1Editor.putBoolean("USED", true);
                                savedGame1Editor.putString("PLAYER_NAME", player.getName());
                                savedGame1Editor.commit();
                                break;
                            case 1:
                                json = gson.toJson(player);
                                savedGame2Editor.putString("SAVED_PLAYER", json);
                                savedGame2Editor.commit();

                                json = gson.toJson(map);
                                savedGame2Editor.putString("SAVED_MAP", json);
                                savedGame2Editor.commit();

                                savedGame2Editor.putBoolean("USED", true);
                                savedGame2Editor.putString("PLAYER_NAME", player.getName());
                                savedGame2Editor.commit();
                                break;
                            case 2:
                                json = gson.toJson(player);
                                savedGame3Editor.putString("SAVED_PLAYER", json);
                                savedGame3Editor.commit();

                                json = gson.toJson(map);
                                savedGame3Editor.putString("SAVED_MAP", json);
                                savedGame3Editor.commit();

                                savedGame3Editor.putBoolean("USED", true);
                                savedGame3Editor.putString("PLAYER_NAME", player.getName());
                                savedGame3Editor.commit();
                                break;
                        }
                        Toast.makeText(getApplicationContext(), "Game Saved to save slot " + (which+1), Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog saveDialog = builder.create();
        saveDialog.show();
    }

    /**
     * Load a game from the specified save slot
     * @param nr the save slot to load the game from
     */
    public void loadGame(int nr){
        Log.d("SAVE", "in loadGame() nr: " + nr);
        Gson gson = new Gson();
        String json;

        switch(nr){
            case 1:
                json= savedGame1.getString("SAVED_PLAYER", "");
                player = gson.fromJson(json, Character.class);

                json = savedGame1.getString("SAVED_MAP", "");
                map = gson.fromJson(json, Map.class);
                break;
            case 2:
                json= savedGame2.getString("SAVED_PLAYER", "");
                player = gson.fromJson(json, Character.class);

                json = savedGame2.getString("SAVED_MAP", "");
                map = gson.fromJson(json, Map.class);
                break;
            case 3:
                json= savedGame3.getString("SAVED_PLAYER", "");
                player = gson.fromJson(json, Character.class);

                json = savedGame3.getString("SAVED_MAP", "");
                map = gson.fromJson(json, Map.class);
                break;
        }
        this.m = new MainModel(player.getName(), player.getHpGrowth(), player.getStrGrowth(), player.getDefGrowth(), player.getSklGrowth(), player.getSpdGrowth());
        drawView = (CanvasMap) findViewById(R.id.view);
        this.gDetector = new GestureDetectorCompat(this,this);
        map.setPlayer(player);
        drawView.setMap(map);


    }

    @Override
    public void onLongPress(MotionEvent e) {
        //ignore
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    @Override
    public boolean onDown(MotionEvent e) {
        //ignore
        return false;
    }
    @Override
    public void onShowPress(MotionEvent e) {
        //ignore
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //ignore
        return false;
    }

    /**
     * Player swiped to a given direction on the screen
     * Calculate which direction
     * If possible, move the player based on the calculated direction
     * Possible directions: Up and left / Up and right
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("DEBUG", "velX: " + velocityX + " velY: " + velocityY + "\ne1: " + e1.toString() + "\ne2: " + e2.toString());
        float orX,orY,newX,newY,deltaX,deltaY;
        orX = e1.getX();
        orY = e1.getY();
        newX = e2.getX();
        newY = e2.getY();
        Log.d("DEBUG", "orX: " + orX + " orY: " + orY + " newX: " + newX + "newY: " + newY);

        Move move = new Move(map, drawView);
        if(orY-newY > 200 && newX-orX >50){
            //swiped up and right
            Log.d("DEBUG", "first if");
            Log.d("DEBUG", "orX: " + orX + " orY: " + orY + " newX: " + newX + "newY: " + newY);
            move.swipedRight(map.getCurrentPoint());
            move();
        }
        else if(orY-newY >200 && orX-newX >50){
            //swiped up and left
            Log.d("DEBUG", "second if");
            Log.d("DEBUG", "orX: " + orX + " orY: " + orY + " newX: " + newX + "newY: " + newY);
            move.swipedLeft(map.getCurrentPoint());
            move();
        }

        return false;
    }

    /**
     * Start a tutorial activity
     */
    public void toTutorialscreen(View v) {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }
}
