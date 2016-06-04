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
    private int defense;
    private int skill;
    private int speed;
    private int maxHP;
    private int currentHP;
    private int gold;
    private int level;
    private int currentXP;
    private int maxXP;

    private Armour armour;
    private Weapon weapon;
    private ArrayList<Item> backpack;
    private final int maxBackpackSize = 30;

    /**
     * Default constructor
     */
    public Character (String playerName) {
        name = playerName;
        imgID = R.drawable.char_icon;
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
        weapon = new Weapon ("No weapon","No bonusses, no penalties.", 0, 0, 0, 0, 0, 1, 0, R.drawable.empty);
        armour = new Armour ("No armour","No bonusses, no penalties.", 0, 0, 0, 0, 0, 1, 0, R.drawable.empty);
    }

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
        this.backpack = new ArrayList<>();
        this.weapon = new Weapon ("No weapon","No bonusses, no penalties.", 0, 0, 0, 0, 0, 1, 0, R.drawable.item1);
        setWeapon(weapon);
        this.armour = new Armour ("No armour","No bonusses, no penalties.", 0, 0, 0, 0, 0, 1, 0, R.drawable.item3);
        setArmour(armour);
        currentXP = 0;
    }



    /**
     * Level up!
     * Change attributes according to current level
     */
    public void levelUp () {
        if (currentXP > maxXP) {
            strength += level;
            defense += level;
            skill += level;
            speed += level;
            maxHP += level;
            setCurrentHP(getCurrentHP() + level);
            level++;
            currentXP -= maxXP;
            maxXP = level*10;
        }
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
     * @return the gold
     */
    public int getGold() {
        return gold;
    }

    public void setGold(int difference) {
        gold += difference;
    }

    /**
     * @return the weapon
     */
    public Weapon getWeapon() {
        return weapon;
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

    public void setArmour(int index) {
        Armour old = this.armour;
        Item item = backpack.get(index);
        if (item.getType() == ItemType.ARMOUR) {
            Armour arm = (Armour) item;
            setArmour(arm);
            backpack.remove(arm);
            if (!old.getName().equals("No weapon"))
                backpack.add(old);
        }
    }

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

    public void changeCharIcon (int imgID){
        if (weapon.getName().contains("Sword") && !armour.getName().contains("Shield"))
            this.imgID = R.drawable.char_training_sword;
        else if (!weapon.getName().contains("Sword") && armour.getName().contains("Shield"))
            this.imgID = R.drawable.char_shield;
        else if (!weapon.getName().contains("Sword") && !armour.getName().contains("Shield"))
            this.imgID = R.drawable.char_icon;
    }
}
