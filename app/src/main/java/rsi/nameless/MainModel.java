package rsi.nameless;

import android.util.Log;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class MainModel {
    private Character player;
    private int currentLevel;
    private Map currentMap;
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
        currentMap = new Map(currentLevel, player, enemies, items, convLib);
        playerScore = 0;

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

    public int getPlayerScore(){
        return playerScore;
    }

}
