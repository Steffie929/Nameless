package rsi.nameless;

import java.util.ArrayList;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class EnemyLibrary {
    private ItemLibrary itemLibrary;
    private ArrayList<Character> enemies;

    public EnemyLibrary (ItemLibrary itemLibrary) {
        this.itemLibrary = itemLibrary;
        enemies = new ArrayList<>();
        fillEnemyList();
    }

    private void fillEnemyList() { //Name, str, def, skl, spd, maxHP, gold, maxXP, level, backpack, weapon
        ArrayList<Item> bunnyBackpack = new ArrayList<>();
        enemies.add(new Character ("Cute Bunny", 8, 8, 12, 12, 7, 2, 2, 1, bunnyBackpack, itemLibrary.getWeapon(3), itemLibrary.getArmour(0), R.drawable.bunny));

        ArrayList<Item> snailBackpack = new ArrayList<>();
        snailBackpack.add(itemLibrary.getPotion(2));
        enemies.add(new Character ("Snail", 8, 9, 9, 7, 9, 3, 4, 1, snailBackpack, itemLibrary.getWeapon(0), itemLibrary.getArmour(1), R.drawable.snail2));
    }

    public Character getEnemy (int index) {
        return enemies.get(index);
    }

    public int getNrEnemies(){
        return enemies.size();
    }
}
