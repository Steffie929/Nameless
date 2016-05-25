package rsi.nameless;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BattleActivity extends AppCompatActivity {
    private Battle battle;
    private Character player, enemy;
    private TextView HP_player, HP_enemy,playerName, enemyName, info, messages;
    private boolean playerTurn; // is true when the user can click a button to set their next action.

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
        dummy = "\t" + player.getCurrentHP() + " / " + player.getMaxHP();
        HP_player.setText(dummy);
        dummy = "\t" + enemy.getCurrentHP() + " / " + enemy.getMaxHP();
        HP_enemy.setText(dummy);

        startRound();
    }

    public void startRound(){
        battle.chooseEnemyAction();
        info.setText("Choose your action");
        playerTurn = true;
    }

    public void getRewards(){
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Rewards");
        helpBuilder.setMessage("You won:\n\t- lots of items\n\t- even more xp");
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

    }

    public void afterInput(){
        info.setText("");
        Log.d("BATTLE", "user-input received");
        battle.performActions();
        messages.setText(battle.getBattleInfo());
        HP_player.setText("\t" + player.getCurrentHP() + " / " + player.getMaxHP());
        HP_enemy.setText("\t" + enemy.getCurrentHP() + " / " + enemy.getMaxHP());

        if(player.getCurrentHP() <= 0){
            finish();
            return;
        }
        else if(enemy.getCurrentHP() <=0){
            getRewards();
            //finish();
            return;
        }
        else if(battle.isRanAway()){
            finish();
            return;
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
            battle.setPlayerAction(BattleAction.USE_ITEM, 1);
            playerTurn = false;
            afterInput();
            startRound();
        }
    }

    public void cowardButton(View v){
        if(playerTurn){
            battle.setPlayerAction(BattleAction.RUN, 0);
            playerTurn = false;
            afterInput();
            startRound();
        }
    }

}
