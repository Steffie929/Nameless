package rsi.nameless;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BattleActivity extends AppCompatActivity {
    private Battle battle;
    private Character player, enemy;
    private TextView HP_player, HP_enemy, playerName, enemyName, info, messages;
    private boolean playerTurn; // is true when the user can click a button to set their next action.
    private final int BACKPACK_KEY = 37;


    /**
     * When this activity is created first information is gathered from the intent. Then the View is updated with
     * the relevant information about the player and the enemy. At the end startRound() is called to start the fight.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        battle = (Battle) getIntent().getSerializableExtra("CURRENT_BATTLE");
        this.player = battle.getPlayer();
        this.enemy = battle.getEnemy();
        playerTurn = false;


        HP_player = (TextView) findViewById(R.id.hpbar_player);
        HP_enemy = (TextView) findViewById(R.id.hpbar_enemy);
        playerName = (TextView) findViewById(R.id.name_player);
        enemyName = (TextView) findViewById(R.id.name_enemy);
        info = (TextView) findViewById(R.id.tV_info);
        messages = (TextView) findViewById(R.id.battle_messages);
        String dummy;

        ImageView img = (ImageView) findViewById(R.id.imageEnemy) ;
        img.setImageResource(enemy.getImgID());

        playerName.setText(player.getName());
        enemyName.setText(enemy.getName());
        dummy = player.getCurrentHP() + " / " + player.getMaxHP();
        HP_player.setText(dummy);
        dummy = "\t" + enemy.getCurrentHP() + " / " + enemy.getMaxHP();
        HP_enemy.setText(dummy);

        startRound();
    }

    /**
     * This function is called every round of the battle. It first checks if the player is still alive and if the enemy is still alive.
     *  Then it gets the enemyAction and allows the player to chooce theirs.
     */
    public void startRound(){
        if(player.getCurrentHP() <= 0){
            battleOver();
            return;
        }
        else if(enemy.getCurrentHP() <=0){
            getRewards();
            return;
        }
        else if(battle.isRanAway()){
            battleOver();
            return;
        }
        battle.chooseEnemyAction();
        info.setText("Choose your action");
        playerTurn = true;
    }

    /**
     * This function is called at the end of a battle. It makes sure all temporary boosts from potions are canceled,
     * and it sets the returnIntent before ending the activity.
     */
    private void battleOver() {
        player.revertBoosts();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("Character_Key", player);
        returnIntent.putExtra("Enemy_Name", enemy.getName());
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    /**
     * This method calls a method to give the player the rewards for winning this battle,
     *  it calls a method to cancel all temporary boosts. And it shows the player a screen with
     *  an overview of the rewards gained. When the player clicks 'ok' the activity is ended.
     */
    private void getRewards() {
        battle.getRewards();
        player.revertBoosts();
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Rewards");
        helpBuilder.setMessage(battle.getRewardString());
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("Character_Key", player);
                        setResult(RESULT_OK, returnIntent);
                        finish();
                    }
                });
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.setCancelable(false);
        helpDialog.show();

    }

    /**
     * This method is called after the player and the enemy picked their actions. It executes those actions
     * and updates the view.
     */
    public void afterInput(){
        info.setText("");
        battle.performActions();
        String battleInfo = battle.getBattleInfo();
        if (battleInfo != null && battleInfo.length() > 0 && battleInfo.charAt(battleInfo.length()-1)=='\n') {
            battleInfo = battleInfo.substring(0, battleInfo.length()-1);
        }
        messages.setText(battleInfo);
        HP_player.setText(player.getCurrentHP() + " / " + player.getMaxHP());
        HP_enemy.setText("\t" + enemy.getCurrentHP() + " / " + enemy.getMaxHP());

        if(player.getCurrentHP() <= 0){
            info.setText("You lost the fight!");
        }
        else if(enemy.getCurrentHP() <=0){
            info.setText("You won the fight!");
        }
    }

    public void attackButton(View v){
        if(playerTurn){
            battle.setPlayerAction(BattleAction.ATTACK, 0);
            playerTurn = false;
            afterInput();
            startRound();
        }
    }

    public void defendButton(View v){
        if(playerTurn){
            battle.setPlayerAction(BattleAction.DEFEND, 0);
            playerTurn = false;
            afterInput();
            startRound();
        }
    }

    public void useItemButton(View v){
        if(playerTurn){
            Intent intent = new Intent(this, BackpackActivity.class);
            intent.putExtra("CURRENT_PLAYER", player);
            intent.putExtra("FROM_BATTLE_BOOLEAN", true);
            startActivityForResult(intent, BACKPACK_KEY);
        }
    }

    /**
     * Button used to run away
     */
    public void cowardButton(View v){
        if(playerTurn){
            battle.setPlayerAction(BattleAction.RUN, 0);
            playerTurn = false;
            afterInput();
            startRound();
        }
    }

    @Override
    public void onBackPressed() {
    }

    /**
     * This method updates the player information after the use item option has been selected en the
     * backpack activity has been called.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode == BACKPACK_KEY) {
            if (resultCode == RESULT_OK) {
                int index = (int) data.getSerializableExtra("Item_Index");
                if (index >= 0) {
                    battle.setPlayerAction(BattleAction.USE_ITEM, index);
                    playerTurn = false;
                    afterInput();
                    startRound();
                }
            }
        }
    }

}
