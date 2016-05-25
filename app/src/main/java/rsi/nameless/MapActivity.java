package rsi.nameless;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private CanvasMap drawView;
    private GestureDetectorCompat gDetector;
    private Map map;

    private final int BATTLE_KEY = 7;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        String playerName = getIntent().getStringExtra("PLAYER_NAME");
        MainModel m = new MainModel(playerName);
        drawView = (CanvasMap) findViewById(R.id.view);
        map = m.getCurrentMap();
        drawView.setMap(map);
        this.gDetector = new GestureDetectorCompat(this,this);
    }

    public void startBattleActivity(Battle battle){
        Intent intent = new Intent(this, BattleActivity.class);
        intent.putExtra("CURRENT_BATTLE", battle);
        startActivityForResult(intent, BATTLE_KEY);
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
        if (requestCode == BATTLE_KEY) {
            if (resultCode == RESULT_OK) {
                Character player = (Character) data.getSerializableExtra("Character_Key");
                map.setPlayer(player);
            }
        }
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        float x,y;
        x = e.getX()-40;
        y = e.getY()-250;
        //Log.d("DEBUG", "MapActivity, onSingleTap\n\t\tclicked on:\tgetX() " + x + " " + y);

        Move move = new Move(map, drawView, x, y);
        drawView.invalidate();
        if(map.inBattle()){
            Battle currentBattle = (Battle) map.getEvent(map.getCurrentPoint());
            startBattleActivity(currentBattle);
        }
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
        return false;
    }
}
