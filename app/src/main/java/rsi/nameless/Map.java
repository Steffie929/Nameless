package rsi.nameless;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Stéphanie on 18-5-2016.
 */
public class Map {
    public enum eventType {BATTLE,SHOP}
    private Event[] events; //Array of events at point 1,..,9
    private Character player;
    private int currentPoint; //Current location of player
    private int previousPoint; // Previous location of player
    private int level; //Level of the map
    private EnemyLibrary enemies;
    private ItemLibrary items;
    private Character[] mapEnemies;
    private boolean battle; // is the player currently in a battle event
    private boolean shop; // is the player currently in a shop event

    private float[] pathX; //X-coordinates of points 1,..,9 on the map
    private float[] pathY; // Y-coordinates of points 1,..,9 on the map


    
    public Map(int level, Character player, EnemyLibrary enemies,ItemLibrary items){
        this.level = level;
        this.enemies = enemies;
        this.items = items;
        this.events = new Event[9];
        battle = false;
        shop = false;
        this.player = player;
        generateEvents();
        currentPoint = 9;
        previousPoint = 9;
        //
    }


    private void generateEvents(){
        Random rand = new Random();
        int rdNR;
        Log.d("TAG", "generate events started");
        rdNR = rand.nextInt(3);
        events[rdNR+3] = new Shop(level, items);

        for(int i=1; i<8; i++){
            rdNR = rand.nextInt(enemies.getNrEnemies());
            if(events[i] == null){
                Event e = new Battle(player, enemies.getEnemy(rdNR));
                events[i] = e;


            }
            //events[i] = new Battle(player, enemies.getEnemy(rdNR));
        }

        events[0] = new EmptyEvent();
        events[8] = new EmptyEvent();
        Log.d("TAG", "generate events finished");
    }


    public void setCurrentPoint(int point){
        previousPoint = currentPoint;
        currentPoint = point;
        if(events[currentPoint-1] instanceof Battle) {
            shop = false;
            battle = true;
        }
        else if(events[currentPoint-1] instanceof Shop){
            battle = false;
            shop = true;
        }
        else{
            shop = false;
            battle = false;
        }
    }

    public void goBack(){
        currentPoint = previousPoint;
        previousPoint = 9;
    }


    public boolean inBattle(){
        return battle;
    }

    public boolean inShop(){
        return shop;
    }


    public int getCurrentPoint(){
        return currentPoint;
    }

    public Event getEvent(int index){
        return events[index];
    }
    
}
