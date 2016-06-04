package rsi.nameless;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import java.io.Serializable;

public class MapActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private CanvasMap drawView;
    private GestureDetectorCompat gDetector;
    private Map map;

    private final int SHOP_KEY = 4;
    private final int BATTLE_KEY = 7;
    private final int BACKPACK_KEY = 37;
    private final int CONVERSATION_KEY = 42;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        String playerName = getIntent().getStringExtra("PLAYER_NAME");
        MainModel m = new MainModel(playerName);
        drawView = (CanvasMap) findViewById(R.id.view);
        map = m.getCurrentMap();
        drawView.setMap(map);
        Conversation conv = map.getConversation();
        startConversation(conv);


        this.gDetector = new GestureDetectorCompat(this,this);
    }

    public void startConversation(Conversation conv){
        Intent intent = new Intent(this, npcActivity.class);
        intent.putExtra("CURRENT_CONVERSATION", conv);
        intent.putExtra("CURRENT_PLAYER", map.getPlayer());
        startActivityForResult(intent, CONVERSATION_KEY);
        drawView.invalidate();
    }

    public void startBattleActivity(Battle battle){
        Intent intent = new Intent(this, BattleActivity.class);
        intent.putExtra("CURRENT_BATTLE", battle);
        startActivityForResult(intent, BATTLE_KEY);
        drawView.invalidate();
    }

    public void startShopActivity(Shop shop){
        Intent intent = new Intent(this, ShopActivity.class);
        intent.putExtra("CURRENT_SHOP", shop);
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
                Character player = (Character) data.getSerializableExtra("Character_Key");
                map.setPlayer(player);
                if (player.getCurrentHP() <= 0) {
                    AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
                    helpBuilder.setTitle("You died");
                    helpBuilder.setMessage("");
                    helpBuilder.setPositiveButton("Return to Main Menu",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    AlertDialog helpDialog = helpBuilder.create();
                    helpDialog.setCancelable(false);
                    helpDialog.show();
                }
            }
        }
        if (requestCode == SHOP_KEY || requestCode == BACKPACK_KEY) {
            if (resultCode == RESULT_OK) {
                Character player = (Character) data.getSerializableExtra("Character_Key");
                map.setPlayer(player);
            }
        }
    }

    public boolean openBackpack(float x, float y){
        Log.d("DEBUG", "BACKPACK?       width: " + drawView.getWidth() + "\theight " + drawView.getHeight());
        if(Math.abs(x-drawView.getWidth()) < 200 && Math.abs(y-drawView.getHeight()) < 400){
            return true;
        }
        else
            return false;
    }

    public boolean openStatScreen(float x, float y){
        Log.d("DEBUG", "StatScreen?       width: " + drawView.getWidth() + "\theight " + drawView.getHeight());
        if(x < 200 && Math.abs(y-drawView.getHeight()) < 400){
            return true;
        }
        else
            return false;

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        float x,y;
        x = e.getX()-40;
        y = e.getY()-250;
        Log.d("DEBUG", "MapActivity, onSingleTap\n\t\tclicked on:\tgetX() " + x + " " + y);

        if(openBackpack(x,y)){
            Intent intent = new Intent(this, BackpackActivity.class);
            intent.putExtra("CURRENT_PLAYER", map.getPlayer());
            startActivityForResult(intent, BACKPACK_KEY);
            return false;
        }

        if(openStatScreen(x,y)){
            Intent intent = new Intent(this, CharacterScreen.class);
            intent.putExtra("CURRENT_PLAYER", map.getPlayer());
            startActivity(intent);
            return false;
        }


        Move move = new Move(map, drawView, x, y);
        if(!move.tryMove()){
            return false;
        }

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
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        int x,y;
        x= Math.round(e.getX());
        y = Math.round(e.getY());
        String toastText = "Long tap:\t (" + x + ", " + y + ")";
        Toast.makeText(getApplicationContext(), toastText,
                Toast.LENGTH_SHORT).show();
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
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //ignore
        //TODO Add fling/swipe possibility
        return false;
    }
}
