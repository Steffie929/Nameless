package rsi.nameless;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class MainModel {
    private Character player;
    private int currentLevel;
    private Map currentMap;
    private EnemyLibrary enemies;
    private ItemLibrary items;

    public MainModel(String playerName){
        this.items = new ItemLibrary();
        this.enemies = new EnemyLibrary(items);
        this.player = new Character(playerName);
        currentLevel = 1;
        currentMap = new Map(currentLevel, player, enemies, items);
    }

    public Map getCurrentMap(){
        return currentMap;
    }

}
