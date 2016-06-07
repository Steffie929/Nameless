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
    private ArrayList<Item> quest;

    /**
     * Constructor
     */
    public ItemLibrary () {
        weapons = new ArrayList<>();
        potions = new ArrayList<>();
        armour = new ArrayList<>();
        quest = new ArrayList<>();
        addWeapons();
        addPotions();
        addArmour();



        Item artefact = new Item("Strange Jewel", "I really don't know what this is", 0,0,0,0,0,0,0,ItemType.QUEST,R.drawable.jewel);
        quest.add(artefact);
    }

    private void addWeapons () {
        //Name, description, str, def, skl, spd, HP, level, price, imgId
        weapons.add( new Weapon ("No weapon", "No bonusses, no penalties.", 0, 0, 0, 0, 0, 1, 0, R.drawable.item1) ); //0
        weapons.add( new Weapon ("Stick", "A simple stick, it is better than nothing.", 1, 0, 1, 0, 0, 1, 0, R.drawable.stick) ); //1
        weapons.add( new Weapon ("Training Sword", "A simple and worn training sword. Not very sharp, but it does more damage than a stick.", 4, -1, 3, 1, 0, 1, 5, R.drawable.training_sword) );
        weapons.add( new Weapon ("Bunny Teeth","A bunny's weapon of choice." , 1, 0, 0, 0, 0, -1, 2, R.drawable.bunny_teeth) );//3
        weapons.add( new Weapon ("Claws","Sharp, quick and dangerous" , 1, 0, 2, 1, 0, 1, 3, R.drawable.claws) );//4
        weapons.add( new Weapon ("Iron Doomblade","An old an dangerous sword" , 5, -1, 5, 3, 0, 2, 10, R.drawable.sword) );//5
        weapons.add( new Weapon ("Sundering Axe","This axe will leave just about anything in pieces" , 7, -3, 4, 1, 0, 2, 10, R.drawable.axe) );//6
        weapons.add( new Weapon ("Iron Cleaver","A slow but very powerful axe" , 10, -4, 3, -2, 0, 2, 10, R.drawable.axe_double) );//7
    }

    private void addPotions () {
        //Name, description str, def, skl, spd, MaxHP, lvl, price, HPRes, nrUses, imgId
        potions.add( new Potion ("Refreshment Potion","Heals minor wounds." , 0, 0, 0, 0, 0, 1, 5, 5, 2, R.drawable.hp_pot1) ); //0
        potions.add( new Potion ("Potion of Fortitude","Makes you a little stronger" , 2, 0, 0, 0, 0, 1, 5, 0, 1, R.drawable.str_pot1) );//1
        potions.add( new Potion ("Potion of Protection","Increases your defensive skills a little." , 0, 2, 0, 0, 0, 1, 5, 0, 1, R.drawable.def_pot1) );//2
        potions.add( new Potion ("Potion of Talent", "Increases your level of skill a little.", 0, 0, 2, 0, 0, 1, 5, 0, 1, R.drawable.skill_pot1) );//3
        potions.add( new Potion ("Potion of Agility", "Makes you a little faster.", 0, 0, 0, 2, 0, 1, 5, 0, 1, R.drawable.spd_pot1) );//4

        potions.add( new Potion ("Strong Refreshment Potion","Heals  wounds." , 0, 0, 0, 0, 0, 2, 10, 10, 3, R.drawable.hp_pot2) ); //5
        potions.add( new Potion ("Strong potion of Fortitude","Makes you stronger." , 4, 0, 0, 0, 0, 2, 8, 0, 1, R.drawable.str_pot2) );//6
        potions.add( new Potion ("Strong Potion of Protection","Increases your defensive skills." , 0, 4, 0, 0, 0, 2, 8, 0, 1, R.drawable.def_pot2) );//7
        potions.add( new Potion ("Strong Potion of Talent", "Increases your level of skill.", 0, 0, 4, 0, 0, 2, 8, 0, 1, R.drawable.skill_pot2) );//8
        potions.add( new Potion ("Strong Potion of Agility", "Makes you faster.", 0, 0, 0, 4, 0, 2, 8, 0, 1, R.drawable.spd_pot2) );//9
    }

    private void addArmour () {
        //itemName, description, strB, defB, sklB, spdB, HPB, level, price, imgID
        armour.add( new Armour ("No armour","No bonusses, no penalties.", 0, 0, 0, 0, 0, 1, 0, R.drawable.item3));//0
        armour.add( new Armour ("Snail Shell", "A snails protection and home.", 0, 2, 0, -1, 0, 1, 2,R.drawable.snailshell));//1
        armour.add( new Armour ("Small Shield","Easy to carry but only adds a little to defence." ,0, 3, 0, 1, 4, 1, 3,R.drawable.small_shield));//2
        armour.add( new Armour ("Flames","Effective and beautiful" ,0, 3, 1, 1, 0, -1, 5,R.drawable.flames));//3
        armour.add( new Armour ("Iron Shield","A sturdy but heavy shield" ,0, 6, 1, -2, 7, 2, 8,R.drawable.iron_shield));//4
        armour.add( new Armour ("Gold-Forged Bulwark","Can you even call this fortress a shield anymore?" ,0, 15, 5, 0, 9, 3, 20,R.drawable.upg_shield));//5
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

    public Item getQuestItem(int index){
        return quest.get(index);
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
