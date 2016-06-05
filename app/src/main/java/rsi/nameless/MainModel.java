package rsi.nameless;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class MainModel {
    private Character player;
    private int currentLevel;
    private Map currentMap;
    private ArrayList<Map> maps;
    private EnemyLibrary enemies;
    private ItemLibrary items;
    private ConversationLibrary convLib;
    private int playerScore;

    public MainModel(String playerName){
        this.items = new ItemLibrary();
        this.enemies = new EnemyLibrary(items);
        this.player = new Character(playerName, items);
        convLib = new ConversationLibrary(enemies);
        currentLevel = 1;
        playerScore = 0;
        maps = new ArrayList<>();

        maps.add(new Map(1, player, enemies, items, convLib));
        maps.add(new Map(2, player, enemies, items, convLib));
        //maps.add(new Map(3, player, enemies, items, convLib));
        currentMap = maps.get(0);

    }


    public void updateScore(){
        int mapLevelBonus = currentLevel*100;
        int playerLevelBonus = player.getLevel()*20;
        int goldBonus = player.getGold()*2;
        int xpBonus = player.getCurrentXP()*10;
        int itemBonus = player.getBackpackValue();

        playerScore = mapLevelBonus + playerLevelBonus + goldBonus + xpBonus + itemBonus;
        Log.d("DEBUG", "current score: " + playerScore);

    }

    public Map getCurrentMap(){
        return currentMap;
    }

    public void levelTwo(){
        currentMap = maps.get(1);
    }

    public int getPlayerScore(){
        return playerScore;
    }

    public ItemLibrary getItemLibrary(){
        return items;
    }

}
