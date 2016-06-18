package rsi.nameless;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class Character implements Serializable {
    private final String name;
    private int imgID;
    private int strength;
    private int strGrowth;
    private int defense;
    private int defGrowth;
    private int skill;
    private int sklGrowth;
    private int speed;
    private int spdGrowth;
    private int maxHP;
    private int hpGrowth;
    private int currentHP;
    private int gold;
    private int level;
    private int currentXP;
    private int maxXP;
    private int boosts[]; //0 str, 1 def, 2 skl, 3 spd, 4 maxHP
    private boolean defaultGrowth;
    private int score;

    private Armour armour;
    private Weapon weapon;
    private ArrayList<Item> backpack;
    private final int maxBackpackSize = 30;

    /**
     * Default constructor
     */
    public Character (String playerName, ItemLibrary items) {
        name = playerName;
        imgID = R.drawable.ci_1_nosw_nosh;
        strength = 10;
        defense = 10;
        skill = 10;
        speed = 10;
        maxHP = 15;
        currentHP = maxHP;
        gold = 10;
        level = 1;
        backpack = new ArrayList<>();
        currentXP = 0;
        maxXP = 10;
        weapon = items.getWeapon(1);
        boosts = new int[5];
        armour = new Armour ("No armour","No bonusses, no penalties.", 0, 0, 0, 0, 0, 1, 0, R.drawable.empty);
        defaultGrowth = true;
        score = 10;
    }

    /**
     * Constructor used for creation of the player
     */
    public Character (String playerName, int hpMod, int strMod, int defMod, int sklMod, int spdMod, ItemLibrary items) {
        name = playerName;
        imgID = R.drawable.ci_1_nosw_nosh;
        strength = 7 + strMod;
        defense = 7 + defMod;
        skill = 7 + sklMod;
        speed = 7 + spdMod;
        maxHP = 12 + hpMod;
        strGrowth = strMod;
        defGrowth = defMod;
        sklGrowth = sklMod;
        spdGrowth = spdMod;
        hpGrowth = hpMod;
        defaultGrowth = false;
        currentHP = maxHP;
        gold = 10;
        level = 1;
        backpack = new ArrayList<>();
        currentXP = 0;
        maxXP = 10;
        weapon = items.getWeapon(1);
        boosts = new int[5];
        armour = new Armour ("No armour","No bonusses, no penalties.", 0, 0, 0, 0, 0, 1, 0, R.drawable.empty);
        score = 10;
    }

    /**
     * Constructor used for enemies
     */
    public Character (String name, int str, int def, int skl, int spd, int maxHP,
                      int gold, int maxExp, int level, ArrayList<Item> backpack, Weapon weapon, Armour armour, int imgID) {
        this.imgID = imgID;
        this.name = name;
        strength = str;
        defense = def;
        skill = skl;
        speed = spd;
        this.maxHP = maxHP;
        currentHP = this.maxHP;
        this.gold = gold;
        this.maxXP = maxExp;
        this.level = level;
        this.backpack = backpack;
        this.weapon = new Weapon ("No weapon","No bonusses, no penalties.", 0, 0, 0, 0, 0, 1, 0, R.drawable.empty);
        setWeapon(weapon);
        this.armour = new Armour ("No armour","No bonusses, no penalties.", 0, 0, 0, 0, 0, 1, 0, R.drawable.empty);
        setArmour(armour);
        currentXP = 0;
    }



    /**
     * Level up!
     * Change attributes according to current level/growth stats
     */
    public void levelUp () {
        if (currentXP > maxXP) {
            if (defaultGrowth) {
                strength += level + 2;
                defense += level + 2;
                skill += level + 2;
                speed += level + 2;
                changeMaxHP(level + 2);
            } else {
                strength += strGrowth;
                defense += defGrowth;
                skill += sklGrowth;
                speed += spdGrowth;
                changeMaxHP(hpGrowth);
            }
            level++;
            currentXP -= maxXP;
            maxXP = level*10;
            if (currentXP > maxXP) {
                levelUp();
            }
            changeCharIcon();
        }
        score+=50;
    }

    public void giveBonusXP(int delta){
        currentXP += delta;
        levelUp();
        score +=(delta*2);
    }

    /**
     * @return true if the backpack contain a potion
     */
    public boolean checkBackPackForPotion () {
        if (backpack.isEmpty()) {
            return false;
        }
        int size = backpack.size();
        for (int i = 0; i < size; i++) {
            if (backpack.get(i).getType() == ItemType.POTION) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the backpack
     */
    public ArrayList<Item> getBackpack() {
        return backpack;
    }

    /**
     * Check if the backpack is full (Backpack contains 30 items
     * @return true if the backpack is full
     */
    public boolean backpackIsFull() {
        return backpack.size() >= 30;
    }

    /**
     * Get item from backpack at index
     * @param index The index of the item in the backpack
     * @return The item
     */
    public Item getItemFromBackPack(int index) {
        if (backpack.isEmpty() || backpack.size() <= index) {
            return null;
        }
        return backpack.get(index);
    }

    /**
     * Add item to the backpack
     * @param item The item to add
     * @return the new index of the item in the backpack, -1 if adding failed
     */
    public int addItemToBackpack (Item item) {
        if (backpack.size() < 30) {
            backpack.add(item);
            score+= item.getPrice();
            return backpack.indexOf(item);
        }
        return -1;
    }

    public int getImgID(){
        return imgID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the strength
     */
    public int getStrength() {
        return strength;
    }

    public void changeStrength(int boost) {
        strength += boost;
    }

    /**
     * @return the defense
     */
    public int getDefense() {
        return defense;
    }

    public void changeDefense(int boost) {
        defense += boost;
    }

    /**
     * @return the skill
     */
    public int getSkill() {
        return skill;
    }

    public void changeSkill(int boost) {
        skill += boost;
    }

    public void removeItemFromBackpack(int position){
        backpack.remove(position);
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    public void changeSpeed(int boost) {
        speed += boost;
    }

    /**
     * @return the HP
     */
    public int getMaxHP() {
        return maxHP;
    }

    public void changeMaxHP(int boost) {
        maxHP += boost;
        if (boost > 0) {
            currentHP += boost;
        }
        if (currentHP > maxHP) {
            currentHP = maxHP;
        }
    }

    /**
     * @return the amount of gold the player has
     */
    public int getGold() {
        return gold;
    }

    /**
     * Change the gold of the character according to a difference
     * @param difference
     */
    public void changeGold(int difference) {
        gold += difference;
        if (gold < 0) {
            gold = 0;
        }
    }

    /**
     * @return the weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    public int getCurrentXP(){
        return currentXP;
    }

    /**
     * Change your current weapon to a weapon in your backpack
     * @param index The index of the weapon in your backpack
     */
    public void setWeapon(int index) {
        Weapon old = this.weapon;
        Item item = backpack.get(index);
        if (item.getType() == ItemType.WEAPON) {
            Weapon wpn = (Weapon) item;
            setWeapon(wpn);
            backpack.remove(wpn);
            if (!old.getName().equals("No weapon"))
                backpack.add(old);
        }
    }

    /**
     * This function gives the player the statboosts gained from using a certain weapon.
     * @param weapon the weapon to set
     */
    private void setWeapon(Weapon weapon) {
        if (level < weapon.getLevel()) {
            return;
        }
        int hpDelta = weapon.getHPBonus() - this.weapon.getHPBonus();
        strength -= this.weapon.getStrengthBonus();
        defense -= this.weapon.getDefenseBonus();
        skill -= this.weapon.getSkillBonus();
        speed -= this.weapon.getSpeedBonus();
        maxHP -= this.weapon.getHPBonus();
        this.weapon = weapon;
        strength += this.weapon.getStrengthBonus();
        defense += this.weapon.getDefenseBonus();
        skill += this.weapon.getSkillBonus();
        speed += this.weapon.getSpeedBonus();
        maxHP += this.weapon.getHPBonus();
        changeCurrentHP(hpDelta);
    }

    public Armour getArmour() {
        return armour;
    }

    /**
     *  This function equips new armour and adds the old armour to the backpack.
     * @param index
     */
    public void setArmour(int index) {
        Armour old = this.armour;
        Item item = backpack.get(index);
        if (item.getType() == ItemType.ARMOUR) {
            Armour arm = (Armour) item;
            setArmour(arm);
            backpack.remove(arm);
            if (!old.getName().equals("No armour"))
                backpack.add(old);
        }
    }

    /**
     * This function gives the player the statboosts gained from using a certain armour.
     * @param armour
     */
    private void setArmour(Armour armour) {
        if (level < armour.getLevel()) {
            return;
        }
        int hpDelta = armour.getHPBonus() - this.armour.getHPBonus();
        strength -= this.armour.getStrengthBonus();
        defense -= this.armour.getDefenseBonus();
        skill -= this.armour.getSkillBonus();
        speed -= this.armour.getSpeedBonus();
        maxHP -= this.armour.getHPBonus();
        this.armour = armour;
        strength += this.armour.getStrengthBonus();
        defense += this.armour.getDefenseBonus();
        skill += this.armour.getSkillBonus();
        speed += this.armour.getSpeedBonus();
        maxHP += this.armour.getHPBonus();
        changeCurrentHP(hpDelta);
    }

    /**
     * Returns the total value of all items in your backpack.
     * @return
     */
    public int getBackpackValue(){
        int result = 0;
        for(int i=0; i<backpack.size(); i++){
            result += backpack.get(i).getPrice();
        }

        return result;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the currentHP
     */
    public int getCurrentHP() {
        return currentHP;
    }

    /**
     * @param currentHP the currentHP to set
     */
    public void setCurrentHP(int currentHP) {
        if (currentHP < 0) {
            this.currentHP = 0;
        }
        else if (currentHP > maxHP) {
            this.currentHP = maxHP;
        }
        else {
            this.currentHP = currentHP;
        }
    }

    /**
     * Change the current HP with a difference
     * @param delta the change in currentHP
     */
    public void changeCurrentHP(int delta) {
        if (currentHP + delta > maxHP) {
            currentHP = maxHP;
        }
        else if (currentHP + delta < 0) {
            currentHP = 0;
        }
        else {
            currentHP += delta;
        }
    }


    /**
     * Changes which icon is used for the player depending on weapon,armour and level of the player.
     */
    public void changeCharIcon (){
        if(weapon == null || armour == null)
            return;

        String w = weapon.getDescription() + " " + weapon.getName();
        String a = armour.getDescription() + " " + armour.getName();
        w = w.toLowerCase();
        a = a.toLowerCase();

        if (w.contains("sword") && !a.contains("shield") && level <2)
            this.imgID = R.drawable.ci_1_sw_nosh;
        else if (!w.contains("sword") && (!w.contains("axe")) && a.contains("shield")&& level <2)
            this.imgID = R.drawable.ci_1_nosw_sh;
        else if (w.contains("sword") && a.contains("shield")&& level <2)
            this.imgID = R.drawable.ci_1_sw_sh;
        else if (w.contains("axe") && !a.contains("shield") && level <2)
            this.imgID = R.drawable.ci_1_ax_nosh;
        else if (w.contains("axe") && a.contains("shield")&& level <2)
            this.imgID = R.drawable.ci_1_ax_sh;
        else if (!w.contains("sword")&& (!w.contains("axe")) && !a.contains("shield")&& level <2)
            this.imgID = R.drawable.ci_1_nosw_nosh;
        
        else if (w.contains("sword") && !a.contains("shield") && level <4)
            this.imgID = R.drawable.ci_2_sw_nosh;
        else if(w.contains("sword") && a.contains("shield") && level <4)
            this.imgID = R.drawable.ci_2_sw_sh;
        else if (!w.contains("sword")&& (!w.contains("axe")) && a.contains("shield")&& level <4)
            this.imgID = R.drawable.ci_2_nosw_sh;
        else if (w.contains("axe") && !a.contains("shield") && level <4)
            this.imgID = R.drawable.ci_2_ax_nosh;
        else if (w.contains("axe") && a.contains("shield")&& level <4)
            this.imgID = R.drawable.ci_2_ax_sh;
        else if (!w.contains("sword")&& (!w.contains("axe")) && !a.contains("shield")&& level <4)
            this.imgID = R.drawable.ci_2_nosw_nosh;
        
        else if (w.contains("sword") && !a.contains("shield"))
            this.imgID = R.drawable.ci_3_sw_nosh_nohlm;
        else if(w.contains("sword") && !a.contains("shield"))
            this.imgID = R.drawable.ci_3_sw_sh_nohlm;
        else if (!w.contains("sword")&& (!w.contains("axe")) && a.contains("shield"))
            this.imgID = R.drawable.ci_3_nosw_sh_nohlm;
        else if (w.contains("axe") && !a.contains("shield"))
            this.imgID = R.drawable.ci_3_ax_nosh_nohlm;
        else if (!w.contains("sword")&& (!w.contains("axe")) && !a.contains("shield"))
            this.imgID = R.drawable.ci_3_nosw_nosh_nohlm;
    }



    public int getMaxXP(){
        return maxXP;
    }

    public int[] getBoosts() {
        return boosts;
    }

    /**
     * reverts all temporary boosts
     */
    public void revertBoosts() { //0 str, 1 def, 2 skl, 3 spd, 4 maxHP
        strength -= boosts[0];
        defense -= boosts[1];
        skill -= boosts[2];
        speed -= boosts[3];
        changeMaxHP(-boosts[4]);
        for (int i = 0; i <= 4; i++) {
            boosts[i] = 0;
        }
    }

    public int getScore(){
        return score;
    }

    public void boostStrength(int boost) {
        boosts[0] += boost;
        strength += boost;
    }

    public void boostDefense(int boost) {
        boosts[1] += boost;
        defense += boost;
    }

    public void boostSkill(int boost) {
        boosts[2] += boost;
        skill += boost;
    }

    public void boostSpeed(int boost) {
        boosts[3] += boost;
        speed += boost;
    }

    public void boostMaxHP(int boost) {
        boosts[4] += boost;
        changeMaxHP(boost);
    }

    public int getStrGrowth() {
        return strGrowth;
    }

    public int getDefGrowth() {
        return defGrowth;
    }

    public int getSklGrowth() {
        return sklGrowth;
    }

    public int getSpdGrowth() {
        return spdGrowth;
    }

    public int getHpGrowth() {
        return hpGrowth;
    }
}
