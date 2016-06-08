package rsi.nameless;

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

    /**
     * Here all enemies are created and added to enemies or bosses
     */
    private void fillEnemyList() { //Name, str, def, skl, spd, maxHP, gold, maxXP, level, backpack, weapon
        //================LEVEL1 Forest/grass =================================================================
        ArrayList<Item> bunnyBackpack = new ArrayList<>();
        bunnyBackpack.add(itemLibrary.getPotion(4));
        bunnyBackpack.add(itemLibrary.getPotion(0));
        bunnyBackpack.add(itemLibrary.getPotion(3));
        enemies.add(new Character ("Bunny", 7, 8, 10, 15, 10, 2, 2, 1, bunnyBackpack, itemLibrary.getWeapon(3), itemLibrary.getArmour(0), R.drawable.bunny));


        ArrayList<Item> snailBackpack = new ArrayList<>();
        snailBackpack.add(itemLibrary.getPotion(2));
        snailBackpack.add(itemLibrary.getPotion(0));
        enemies.add(new Character ("Snail", 9, 11, 9, 6, 9, 3, 4, 1, snailBackpack, itemLibrary.getWeapon(0), itemLibrary.getArmour(1), R.drawable.snail));

        ArrayList<Item> batBackpack = new ArrayList<>();
        batBackpack.add(itemLibrary.getPotion(3));
        batBackpack.add(itemLibrary.getPotion(1));
        batBackpack.add(itemLibrary.getPotion(0));
        enemies.add(new Character("Bat", 7, 8, 12, 10, 9, 3, 3, 1, batBackpack, itemLibrary.getWeapon(4), itemLibrary.getArmour(0), R.drawable.bat));



        //================LEVEL2 Desert =================================================================
        //Name, str, def, skl, spd, maxHP, gold, maxXP, level, backpack, weapon
        ArrayList<Item> duneCrawlerBackpack = new ArrayList<>();
        duneCrawlerBackpack.add(itemLibrary.getPotion(5));
        duneCrawlerBackpack.add(itemLibrary.getPotion(6));
        duneCrawlerBackpack.add(itemLibrary.getPotion(9));
        enemies.add(new Character("Dune Crawler", 12, 10, 10, 10, 13, 8, 7, 2, duneCrawlerBackpack, itemLibrary.getWeapon(4), itemLibrary.getArmour(0), R.drawable.dune_crawler));

        ArrayList<Item> scorpionBackpack = new ArrayList<>();
        scorpionBackpack.add(itemLibrary.getPotion(5));
        scorpionBackpack.add(itemLibrary.getPotion(7));
        scorpionBackpack.add(itemLibrary.getPotion(9));
        enemies.add(new Character("Scorpion", 12, 8, 10, 10, 15, 6, 7, 2, scorpionBackpack, itemLibrary.getWeapon(4), itemLibrary.getArmour(0), R.drawable.scorpion));

        ArrayList<Item> angryTreeBackpack = new ArrayList<>();
        angryTreeBackpack.add(itemLibrary.getPotion(5));
        angryTreeBackpack.add(itemLibrary.getPotion(6));
        angryTreeBackpack.add(itemLibrary.getPotion(8));
        enemies.add(new Character("Angry Tree", 12, 14, 10, 6, 18, 7, 7, 2, angryTreeBackpack, itemLibrary.getWeapon(0), itemLibrary.getArmour(0), R.drawable.tree1));

        //====================lEVEL3=================================================================================================================================
        //Name, str, def, skl, spd, maxHP, gold, maxXP, level, backpack, weapon
        ArrayList<Item>dragonBackpack = new ArrayList<>();
        dragonBackpack.add(itemLibrary.getWeapon(8));
        dragonBackpack.add(itemLibrary.getPotion(6));
        dragonBackpack.add(itemLibrary.getPotion(7));
        enemies.add(new Character("Dragon", 14, 16, 12, 10, 25, 12, 12, 3, dragonBackpack, itemLibrary.getWeapon(8), itemLibrary.getArmour(0), R.drawable.dragon));

        ArrayList<Item>axeBoarBackpack = new ArrayList<>();
        axeBoarBackpack.add(itemLibrary.getWeapon(7));
        axeBoarBackpack.add(itemLibrary.getPotion(6));
        axeBoarBackpack.add(itemLibrary.getPotion(8));
        enemies.add(new Character("Axe Boar", 18, 8, 10, 12, 22, 11, 11, 3, axeBoarBackpack, itemLibrary.getWeapon(9), itemLibrary.getArmour(0), R.drawable.monster1));

        ArrayList<Item>skelebroBackpack = new ArrayList<>();
        skelebroBackpack.add(itemLibrary.getWeapon(5));
        skelebroBackpack.add(itemLibrary.getPotion(7));
        skelebroBackpack.add(itemLibrary.getPotion(9));
        enemies.add(new Character("Skelebro", 12, 14, 16, 16, 18, 7, 7, 3, skelebroBackpack, itemLibrary.getWeapon(5), itemLibrary.getArmour(0), R.drawable.monster2));

    }

    private void fillBossList() {
        //Name, str, def, skl, spd, maxHP, gold, maxXP, level, backpack, weapon
        ArrayList<Item> wolfBackpack = new ArrayList<>();
        wolfBackpack.add(itemLibrary.getPotion(5));
        wolfBackpack.add(itemLibrary.getPotion(6));
        wolfBackpack.add(itemLibrary.getPotion(0));
        wolfBackpack.add(itemLibrary.getWeapon(2));
        bosses.add(new Character ("Wolf", 12, 12, 12, 12, 15, 10, 10, 1, wolfBackpack, itemLibrary.getWeapon(4), itemLibrary.getArmour(0), R.drawable.wolf));//0

        ArrayList<Item> desertBossBackpack = new ArrayList<>();
        desertBossBackpack.add(itemLibrary.getWeapon(5));
        desertBossBackpack.add(itemLibrary.getWeapon(6));
        desertBossBackpack.add(itemLibrary.getPotion(5));
        desertBossBackpack.add(itemLibrary.getPotion(6));
        bosses.add(new Character ("Nagaruda", 20, 14, 14, 14, 20, 15, 20, 2, desertBossBackpack, itemLibrary.getWeapon(0), itemLibrary.getArmour(0), R.drawable.desert_boss));//1

        ArrayList<Item> endBossBackpack = new ArrayList<>();
        bosses.add(new Character ("Nameless", 30, 30, 30, 30, 45, 50, 50, 10, endBossBackpack, itemLibrary.getWeapon(4), itemLibrary.getArmour(0), R.drawable.stranger_battle));//2
    }

    public Character getBoss (int index) {
        return bosses.get(index);
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
