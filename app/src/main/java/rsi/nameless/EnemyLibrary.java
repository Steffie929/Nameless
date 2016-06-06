package rsi.nameless;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class EnemyLibrary {
    private ItemLibrary itemLibrary;
    private ArrayList<Character> enemies;
    private ArrayList<Character> bosses;

    public EnemyLibrary (ItemLibrary itemLibrary) {
        this.itemLibrary = itemLibrary;
        enemies = new ArrayList<>();
        bosses = new ArrayList<>();
        fillEnemyList();
        fillBossList();
    }

    private void fillEnemyList() { //Name, str, def, skl, spd, maxHP, gold, maxXP, level, backpack, weapon
        //================LEVEL1 Forest/grass =================================================================
        ArrayList<Item> bunnyBackpack = new ArrayList<>();
        bunnyBackpack.add(itemLibrary.getPotion(0));
        bunnyBackpack.add(itemLibrary.getPotion(3));
        enemies.add(new Character ("Cute Bunny", 8, 8, 12, 12, 7, 2, 2, 1, bunnyBackpack, itemLibrary.getWeapon(3), itemLibrary.getArmour(0), R.drawable.bunny));


        ArrayList<Item> snailBackpack = new ArrayList<>();
        snailBackpack.add(itemLibrary.getPotion(0));
        snailBackpack.add(itemLibrary.getPotion(2));
        enemies.add(new Character ("Snail", 8, 9, 9, 7, 9, 3, 4, 1, snailBackpack, itemLibrary.getWeapon(0), itemLibrary.getArmour(1), R.drawable.snail));

        ArrayList<Item> batBackpack = new ArrayList<>();
        batBackpack.add(itemLibrary.getPotion(0));
        batBackpack.add(itemLibrary.getPotion(3));
        batBackpack.add(itemLibrary.getPotion(4));
        enemies.add(new Character("Bat", 5, 5, 10, 10, 5, 3, 3, 1, batBackpack, itemLibrary.getWeapon(4), itemLibrary.getArmour(0), R.drawable.bat)       );



        //================LEVEL2 Desert =================================================================
        //Name, str, def, skl, spd, maxHP, gold, maxXP, level, backpack, weapon
        ArrayList<Item> duneCrawlerBackpack = new ArrayList<>();
        duneCrawlerBackpack.add(itemLibrary.getPotion(5));
        duneCrawlerBackpack.add(itemLibrary.getPotion(6));
        duneCrawlerBackpack.add(itemLibrary.getPotion(9));
        enemies.add(new Character("Dune Crawler", 12, 8, 10, 10, 12, 8, 7, 2, duneCrawlerBackpack, itemLibrary.getWeapon(4), itemLibrary.getArmour(0), R.drawable.dune_crawler));

        ArrayList<Item> scorpionBackpack = new ArrayList<>();
        scorpionBackpack.add(itemLibrary.getPotion(5));
        scorpionBackpack.add(itemLibrary.getPotion(7));
        scorpionBackpack.add(itemLibrary.getPotion(9));
        enemies.add(new Character("Scorpion", 12, 8, 10, 10, 12, 6, 7, 2, scorpionBackpack, itemLibrary.getWeapon(4), itemLibrary.getArmour(0), R.drawable.scorpion));

        ArrayList<Item> angryTreeBackpack = new ArrayList<>();
        angryTreeBackpack.add(itemLibrary.getPotion(5));
        angryTreeBackpack.add(itemLibrary.getPotion(6));
        angryTreeBackpack.add(itemLibrary.getPotion(8));
        enemies.add(new Character("Angry Tree", 12, 14, 8, 6, 18, 7, 7, 2, angryTreeBackpack, itemLibrary.getWeapon(0), itemLibrary.getArmour(0), R.drawable.tree1));

    }

    private void fillBossList() {
        //Name, str, def, skl, spd, maxHP, gold, maxXP, level, backpack, weapon
        ArrayList<Item> wolfBackpack = new ArrayList<>();
        wolfBackpack.add(itemLibrary.getPotion(0));
        wolfBackpack.add(itemLibrary.getPotion(5));
        wolfBackpack.add(itemLibrary.getPotion(6));
        wolfBackpack.add(itemLibrary.getWeapon(2));
        bosses.add(new Character ("Wolf", 11, 10, 10, 10, 15, 10, 10, 1, wolfBackpack, itemLibrary.getWeapon(4), itemLibrary.getArmour(0), R.drawable.wolf));//0

        ArrayList<Item> desertBossBackpack = new ArrayList<>();
        desertBossBackpack.add(itemLibrary.getWeapon(5));
        desertBossBackpack.add(itemLibrary.getWeapon(6));
        desertBossBackpack.add(itemLibrary.getPotion(5));
        desertBossBackpack.add(itemLibrary.getPotion(6));
        bosses.add(new Character ("Nagaruda", 14, 10, 14, 10, 20, 15, 20, 2, desertBossBackpack, itemLibrary.getWeapon(0), itemLibrary.getArmour(0), R.drawable.desert_boss));//1

        ArrayList<Item> endBossBackpack = new ArrayList<>();
        bosses.add(new Character ("Stranger", 30, 30, 30, 30, 45, 50, 50, 10, endBossBackpack, itemLibrary.getWeapon(4), itemLibrary.getArmour(0), R.drawable.stranger_battle));//2
    }

    public Character getEnemy (int index) {
        return enemies.get(index);
    }

    public Character getBoss (int index) {
        return bosses.get(index);
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

    public ArrayList<Character> getBossesWithLevel (int level) {
        ArrayList<Character> result = new ArrayList<>();
        int size = bosses.size();
        for (int i = 0; i < size; i++) {
            Character boss = bosses.get(i);
            if (boss.getLevel() == level) {
                result.add(boss);
            }
        }
        return result;
    }
}
