package rsi.nameless;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Used to display conversations
 */
public class npcActivity extends AppCompatActivity {
    private ImageView iV;
    private Button o1,o2,o3;
    private TextView tV;
    private Conversation conversation;
    private Link currentLink;
    private Battle possibleBattle;
    private GestureDetectorCompat gDetector;
    private Character player;
    private boolean end;

    private final int BATTLE_KEY = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npc);
        conversation = (Conversation) getIntent().getSerializableExtra("CURRENT_CONVERSATION");
        player = (Character) getIntent().getSerializableExtra("CURRENT_PLAYER");
        end = false;

        iV = (ImageView) findViewById(R.id.imageViewNPC);
        o1 = (Button) findViewById(R.id.option1);
        o2 = (Button) findViewById(R.id.option2);
        o3 = (Button) findViewById(R.id.option3);
        tV = (TextView) findViewById(R.id.textNPC);
        currentLink = conversation.getLink();

        Drawable d = ResourcesCompat.getDrawable(getResources(),conversation.getImgID() , null);
        iV.setImageDrawable(d);
        update();
    }

    /**
     * Update what dialogue and what options are shown
     */
    public void update() {
        o1.setText(currentLink.getOption(0));
        o2.setText(currentLink.getOption(1));
        o3.setText(currentLink.getOption(2));
        tV.setText(currentLink.getText());
    }

    /**
     * User selected option one of the choices
     * Change the course of the conversation to reflect this choice
     * All methods "option[number]" do similar things
     */
    public void option1(View v) {
        if(o1.getVisibility() ==View.GONE)
            return;

        currentLink = currentLink.getNext(0);
        if(currentLink.isLast()) {
            tV.setText(currentLink.getText());
            o1.setVisibility(View.GONE);
            o2.setVisibility(View.GONE);
            o3.setVisibility(View.GONE);
            end=true;
        }
        else
            update();
    }

    public void option2(View v) {
        if(o2.getVisibility() ==View.GONE)
            return;

        currentLink = currentLink.getNext(1);
        if(currentLink.isLast()) {
            tV.setText(currentLink.getText());
            o1.setVisibility(View.GONE);
            o2.setVisibility(View.GONE);
            o3.setVisibility(View.GONE);
            end=true;
        }
        else
            update();
    }

    public void option3(View v) {
        if(o3.getVisibility() ==View.GONE)
            return;

        currentLink = currentLink.getNext(2);
        if(currentLink.isLast()) {
            tV.setText(currentLink.getText());
            o1.setVisibility(View.GONE);
            o2.setVisibility(View.GONE);
            o3.setVisibility(View.GONE);
            end=true;
        }
        else
            update();
    }

    /**
     * Ends the conversation
     * Depending on the flow of the conversation, a battle could start,
     * the player could get a reward, or nothing could happen
     */
    public void endActivity() {
        if(currentLink.isBattle()) {
            possibleBattle = new Battle(player, conversation.getEnemy());
            Intent intent = new Intent(this, BattleActivity.class);
            intent.putExtra("CURRENT_BATTLE", possibleBattle);      // finish() not needed, happens in method onActivityResult(int, int, Intent)
            startActivityForResult(intent, BATTLE_KEY);             // Do not put a finish() here anyway! This causes problems!
        }
        else if(currentLink.isReward()) {
            player.changeGold(player.getLevel()*5);
            Intent returnIntent = new Intent();
            setResult(RESULT_CANCELED, returnIntent);
            finish();
        }
        else {
            Intent returnIntent = new Intent();
            setResult(RESULT_CANCELED, returnIntent);
            finish();
        }
    }

    /**
     * After a conversation has run out of dialogue, touching the screen anywhere will end the conversation
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);
        if(action == MotionEvent.ACTION_UP && end) {
            endActivity();
        }

        return false;
    }

    /**
     * Conversations can start a battle, this handles what happens after a battle
     * Just passes all data from the battle onto the activity which called this one
     * Ends the conversation
     */
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode == BATTLE_KEY) {
            if (resultCode == RESULT_OK) {
                Character player = (Character) data.getSerializableExtra("Character_Key");
                Intent returnIntent = new Intent();
                returnIntent.putExtra("Character_Key", player);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        }
    }

    /**
     * To make the normal back button on the screen do nothing
     * Prevents the user from (accidentally) escaping the conversation, which is not intended
     */
    @Override
    public void onBackPressed() {
    }

}
