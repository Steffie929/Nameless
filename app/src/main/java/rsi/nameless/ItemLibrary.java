package rsi.nameless;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class ItemLibrary implements Serializable{
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
        weapons.add( new Weapon ("No weapon", "No bonusses, no penalties.", 0, 0, 0, 0, 0, 1, 0, R.drawable.item1) ); //Name, description, str, def, skl, spd, HP, level, price, imgId
        weapons.add( new Weapon ("Stick", "A simple stick, it is better than nothing.", 1, 0, 1, 0, 0, 1, 0, R.drawable.stick) );
        weapons.add( new Weapon ("Training Sword", "A simple and worn training sword. Not very sharp, but it does more damage than a stick.", 4, -1, 1, 1, 0, 1, 5, R.drawable.training_sword) );
        weapons.add( new Weapon ("Bunny Teeth","A bunny's weapon of choice." , 1, 0, 0, 0, 0, 1, 2, R.drawable.bunny_teeth) );
        weapons.add( new Weapon ("Wolf Teeth","A wolf's weapon of choice." , 1, 0, 0, 1, 0, 1, 3, R.drawable.bunny_teeth) );
    }

    private void addPotions () {
        potions.add( new Potion ("Refreshment potion","Heals minor wounds." , 0, 0, 0, 0, 0, 1, 6, 5, 3, R.drawable.hp_pot1) ); //Name, description str, def, skl, spd, MaxHP, lvl, price, HPRes, nrUses, imgId
        potions.add( new Potion ("Potion of Fortitude","Makes you a little stronger" , 2, 0, 0, 0, 0, 1, 5, 0, 1, R.drawable.str_pot1) );
        potions.add( new Potion ("Potion of Protection","Increases your defensive skills a little." , 0, 2, 0, 0, 0, 1, 5, 0, 1, R.drawable.def_pot1) );
        potions.add( new Potion ("Potion of Talent", "Increases your level of skill a little.", 0, 0, 2, 0, 0, 1, 5, 0, 1, R.drawable.skill_pot1) );
        potions.add( new Potion ("Potion of Agility", "Makes you a little faster.", 0, 0, 0, 2, 0, 1, 5, 0, 1, R.drawable.spd_pot1) );
    }

    private void addArmour () {
        armour.add( new Armour ("No armour","No bonusses, no penalties.", 0, 0, 0, 0, 0, 1, 0, R.drawable.item3));
        armour.add( new Armour ("Snail Shell", "A snails protection and home.", 0, 2, 0, -1, 0, 1, 2,R.drawable.snailshell));
        armour.add( new Armour ("Small Shield","Easy to carry but only adds a little to defence." ,0, 3, 0, 1, 4, 1, 3,R.drawable.small_shield));
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
