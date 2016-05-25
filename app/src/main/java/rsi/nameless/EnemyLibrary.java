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

    /**
     * Get all enemies with a given level
     * @param level the level of the enemies you want
     * @return an ArrayList with all enemies of the specified level
     */
    public ArrayList<Character> getEnemiesWithLevel (int level) {
        ArrayList<Character> result = new ArrayList<>();
        int size = enemies.size();
        for (int i = 0; i < size; i++) {
            Character enemy = enemies.get(i);
            if (enemy.getLevel() == level) {
                result.add(enemy);
            }
        }
        return result;
    }
}
