package rsi.nameless;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Stéphanie on 24-5-2016.
 */
public class ShopCanvas extends View {
    private int mapHeight;
    private int mapWidth;
    private Paint paint;
    private Canvas canvas;
    public ShopCanvas(Context context) {
        super(context);
        start();
    }

    public ShopCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        start();
    }

    public ShopCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        start();
    }

    public void start(){
        paint = new Paint();
        mapHeight=getResources().getDisplayMetrics().heightPixels;
        mapWidth=getResources().getDisplayMetrics().widthPixels;
        Log.d("DEBUG", "SimpleCanvas, start()\n\t\tcanvas height and width: " + mapHeight + "  " + mapWidth);
        Bitmap bitmap = Bitmap.createBitmap(mapHeight, mapWidth, Bitmap.Config.RGB_565);
        canvas = new Canvas(bitmap);
        drawBackground();
    }

    public void drawBackground(){
        Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.wall, null);
        d.setBounds(0, 0, mapWidth, mapHeight);
        d.draw(canvas);
    }
}


