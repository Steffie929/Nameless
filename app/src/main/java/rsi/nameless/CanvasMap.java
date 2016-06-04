package rsi.nameless;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Stéphanie on 17-5-2016.
 */
public class CanvasMap extends View  {
    private float[] Xcoord; //All possible X-coordinates of the canvas
    private float[] Ycoord; //All possible Y-coordinates of the canvas
    private float[] pathX; //An array with the X-coordinates of point 1,..,9 of the map
    private float[] pathY; //An array with the Y-coordinates of point 1,..,9 of the map
    private int mapHeight;
    private int mapWidth;
    private Paint paint;
    private Canvas canvas;
    private Map map; //A class that keeps track of the current state of the map. (location player, location and type of events etc.)
    private Drawable playerIcon;


    public CanvasMap(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        start();
    }
    public CanvasMap(Context context, AttributeSet attrs) {
        super(context, attrs);
        start();
    }
    public CanvasMap(Context context){
        super(context);
        start();
    }

    /**
     * This method is called when an instance of CanvasMap is created.
     * It creates a canvas that fills the screen and sets the stage to START.
     */
    public void start() {
        paint = new Paint();
        mapHeight=getResources().getDisplayMetrics().heightPixels;
        mapWidth=getResources().getDisplayMetrics().widthPixels;
        Log.d("DEBUG", "CanvasMap, start()\n\t\tcanvas height and width: " + mapHeight + "  " + mapWidth);
        Bitmap bitmap = Bitmap.createBitmap(mapHeight, mapWidth, Bitmap.Config.RGB_565);
        canvas = new Canvas(bitmap);
    }
    
    public void setMap(Map map){
        this.map =map;
        playerIcon = ResourcesCompat.getDrawable(getResources(), this.map.getPlayer().getImgID(), null);
    }


    @Override
    public void onDraw(Canvas canvas) {
        this.canvas = canvas;
        mapHeight = this.getHeight();
        mapWidth = this.getWidth();
        int[] orderX = {2,1,3,0,2,4,1,3,2};
        int[] orderY = {0,1,1,2,2,2,3,3,4};
        Xcoord = new float[5];
        Ycoord = new float[5];
        pathX = new float[9];
        pathY = new float[9];

        // 5 different Y-coordinates:
        int padding = 100;
        Ycoord[0] = padding;
        Ycoord[1] = padding + (mapHeight-2*padding)/4;
        Ycoord[2] = mapHeight/2;
        Ycoord[3] = padding + (mapHeight-2*padding)*3/4;
        Ycoord[4] = mapHeight-padding;

        // 5 different x-coordinates:
        padding = 75;
        Xcoord[0] = padding;
        Xcoord[1] = padding + (mapWidth-2*padding)/4;
        Xcoord[2] = mapWidth/2;
        Xcoord[3]= padding + (mapWidth-2*padding)*3/4;
        Xcoord[4] = mapWidth-padding;
        
        for(int i=0; i< 9;i++){
            pathX[i] = Xcoord[orderX[i]];
            pathY[i] = Ycoord[orderY[i]];
        }

        Log.d("DEBUG", "CanvasMap, ondraw, \n\t\t "+ "canvasWidth: " + mapWidth + "  canvasHeight: " + mapHeight);

        drawBackground();
        drawRoads();
        drawEventIcons();
        drawPlayerIcon(map.getCurrentPoint());
        
    }

    public void drawEventIcons(){
        Log.d("DRAW", "started drawEventIcons");
        Drawable d;

        for(int i=0; i<8;i++){
            Event event = map.getEvent(i);
            if(event instanceof Battle && i != 0){
                d = ResourcesCompat.getDrawable(getResources(), R.drawable.icon_battle, null);
                drawIcon(d, i);
            }
            if(event instanceof Battle && i == 0){
                d = ResourcesCompat.getDrawable(getResources(), R.drawable.icon_battle, null);
                drawBigIcon(d, i);
            }
            else if(event instanceof Shop){
                d = ResourcesCompat.getDrawable(getResources(), R.drawable.shop, null);
                drawBigIcon(d, i);
            }
        }
    }


    /**
     * Draws an icon 'd' with a height of 150 and width of 150 at location 'p' on the map.
     * used for battle icons
     * @param d The icon to draw
     * @param p The location on the map
     */
    public void drawIcon(Drawable d, int p){
        int x,y;
        x= Math.round(pathX[p]);
        y = Math.round(pathY[p]);
        d.setBounds(x-75, y-75, x+75, y+75);
        d.draw(canvas);
    }

    /**
     * Draws an icon 'd' with a height of 250 and width of 200 at location 'p' on the map.
     * Used for the shop icon
     * @param d The icon to draw
     * @param p The location on the map
     */
    public void drawBigIcon(Drawable d, int p){
        int x,y;
        x= Math.round(pathX[p]);
        y = Math.round(pathY[p]);
        d.setBounds(x-125, y-100, x+125, y+100);
        d.draw(canvas);
    }

    /**
     * Draws an icon 'd' with a height of 150 and width of 100 at location 'p' on the map.
     * Used for the player icon
     * @param d The icon to draw
     * @param p The location on the map
     */
    public void drawPlayerIcon(int p){
        int x,y;
        x= Math.round(pathX[p]);
        y = Math.round(pathY[p]);
        //d.setBounds(left, top, right, bottom);
        playerIcon.setBounds(x-50, y-75, x+50, y+75);//100 breed 150 hoog
        playerIcon.draw(canvas);
    }

    public void drawRoads(){
        Path path = new Path();
        int[] orderPath = {3,0,5,4,2,1,4,3,8,5,4,6,7,4};
        path.moveTo(pathX[3], pathY[3]);
        for(int i=1; i <14;i++){
            path.lineTo(pathX[orderPath[i]], pathY[orderPath[i]]);
        }
        path.close();

        Bitmap bitmap2 = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.roadpattern);
        BitmapShader patternShader = new BitmapShader(bitmap2,
                Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        paint.setColor(Color.parseColor("#663c00"));
        paint.setShader(patternShader);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        paint.setShader(null);
    }

    public void drawBackground(){
        Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.achtergrond1, null);
        d.setBounds(0, 0, mapWidth, mapHeight);
        d.draw(canvas);

        d = ResourcesCompat.getDrawable(getResources(), R.drawable.backpack, null);
        d.setBounds(mapWidth-150, mapHeight-150,mapWidth,mapHeight);
        d.draw(canvas);

    }

    public float[] getPathX(){
        return pathX;
    }

    public float[] getPathY(){
        return pathY;
    }

    public Map getMap(){
        return map;
    }
    
}
