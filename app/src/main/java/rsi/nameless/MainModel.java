package rsi.nameless;

import java.util.ArrayList;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class MainModel {
    private Character player;
    private ArrayList<Map> maps;
    private EnemyLibrary enemies;
    private ItemLibrary items;
    private ConversationLibrary convLib;

    /**
     * Constructor of the main model of our game. Here all items, weapons and maps are made and a player is created.
     * @param playerName
     * @param hpMod
     * @param strMod
     * @param defMod
     * @param sklMod
     * @param spdMod
     */
    public MainModel(String playerName, int hpMod, int strMod, int defMod, int sklMod, int spdMod){
        this.items = new ItemLibrary();
        this.enemies = new EnemyLibrary(items);
        this.player = new Character(playerName, hpMod, strMod, defMod, sklMod, spdMod, items);
        convLib = new ConversationLibrary(enemies);
        maps = new ArrayList<>();

        maps.add(new Map(1, enemies, items, convLib));
        maps.add(new Map(2, enemies, items, convLib));
        maps.add(new Map(3, enemies, items, convLib));

    }

    public Map getMap(int lvl){
        return maps.get(lvl-1);
    }

    public ItemLibrary getItemLibrary(){
        return items;
    }

    public Character getPlayer(){
        return player;
    }

}
