package rsi.nameless;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class MainModel {
    private Character player;
    private int currentLevel;
    private ArrayList<Map> maps;
    private EnemyLibrary enemies;
    private ItemLibrary items;
    private ConversationLibrary convLib;

    public MainModel(String playerName, int hpMod, int strMod, int defMod, int sklMod, int spdMod){
        this.items = new ItemLibrary();
        this.enemies = new EnemyLibrary(items);
        this.player = new Character(playerName, hpMod, strMod, defMod, sklMod, spdMod, items);
        convLib = new ConversationLibrary(enemies);
        currentLevel = 1;
        maps = new ArrayList<>();

        maps.add(new Map(1, enemies, items, convLib));
        maps.add(new Map(2, enemies, items, convLib));
        //maps.add(new Map(3, player, enemies, items, convLib));

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
