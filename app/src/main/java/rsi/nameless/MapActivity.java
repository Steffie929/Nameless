package rsi.nameless;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    private CanvasMap drawView;
    private GestureDetectorCompat gDetector;
    private Map map;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        String playerName = (String) getIntent().getStringExtra("PLAYER_NAME");
        MainModel m = new MainModel(playerName);
        drawView = (CanvasMap) findViewById(R.id.view);
        map = m.getCurrentMap();
        drawView.setMap(map);
        this.gDetector = new GestureDetectorCompat(this,this);
    }

    public void startBattleActivity(Battle battle){
        Intent intent = new Intent(this, BattleActivity.class);
        intent.putExtra("CURRENT_BATTLE", battle);
        startActivity(intent);
        drawView.invalidate();
    }

    public void startShopActivity(Shop shop){
        /*Intent intent = new Intent(this, ShopActivity.class);
        intent.putExtra("CURRENT_SHOP", shop);
        startActivity(intent);
        drawView.invalidate();*/
    }


    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        float x,y;
        x= e.getX()-40;
        y = e.getY()-250;
        Log.d("DEBUG", "MapActivity, onSingleTap\n\t\tclicked on:\tgetX() " + x + " " + y);


        Move move = new Move(map, drawView, x, y);
        drawView.invalidate();
        if(map.getEvent(map.getCurrentPoint()-1) instanceof Battle){
            Battle currentBattle = (Battle) map.getEvent(map.getCurrentPoint()-1);
            startBattleActivity(currentBattle);
        }
        else if(map.getEvent(map.getCurrentPoint()-1) instanceof Shop){
            Shop currentShop = (Shop) map.getEvent(map.getCurrentPoint()-1);
            startShopActivity(currentShop);
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
