package rsi.nameless;

import java.util.ArrayList;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class ItemLibrary {
    private ArrayList<Weapon> weapons;
    private ArrayList<Armour> armour;
    private ArrayList<Potion> potions;

    /**
     * Constructor
     */
    public ItemLibrary () {
        weapons = new ArrayList<>();
        potions = new ArrayList<>();
        armour = new ArrayList<>();
        addWeapons();
        addPotions();
        addArmour();
    }

    private void addWeapons () {
        weapons.add( new Weapon ("No weapon", 0, 0, 0, 0, 0, 1, 0) ); //Name, str, def, skl, spd, HP, level, price
        weapons.add( new Weapon ("Stick", 1, 0, 1, 0, 0, 1, 0) );
        weapons.add( new Weapon ("Training Sword", 2, -1, 1, 0, 0, 1, 5) );
        weapons.add( new Weapon ("Bunny Teeth", 1, 0, 0, 0, 0, 1, 0) );
    }

    private void addPotions () {
        potions.add( new Potion ("Refreshment potion", 0, 0, 0, 0, 0, 1, 7, 5, 3) ); //Name, str, def, skl, spd, MaxHP, lvl, price, HPRes, nrUses
        potions.add( new Potion ("Potion of Fortitude", 2, 0, 0, 0, 0, 1, 5, 0, 1) );
        potions.add( new Potion ("Potion of Protection", 0, 2, 0, 0, 0, 1, 5, 0, 1) );
        potions.add( new Potion ("Potion of Talent", 0, 0, 2, 0, 0, 1, 5, 0, 1) );
        potions.add( new Potion ("Potion of Agility", 0, 0, 0, 2, 0, 1, 5, 0, 1) );
    }

    private void addArmour () {
        armour.add( new Armour ("No armour", 0, 0, 0, 0, 0, 1, 0));
        armour.add( new Armour ("Snail Shell", 0, 2, 0, -1, 0, 1, 1));
        armour.add( new Armour ("Small Shield", 0, 2, 0, 0, 0, 1, 1));
    }

    public Weapon getWeapon (int index) {
        return weapons.get(index);
    }

    public Potion getPotion (int index) {
        return potions.get(index);
    }

    public Armour getArmour (int index) {
        return armour.get(index);
    }

    public int getNrWeapons(){
        return weapons.size();
    }
    public int getNrArmour(){
        return armour.size();
    }
    public int getNrPotions(){
        return potions.size();
    }

}
