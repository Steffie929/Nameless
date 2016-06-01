package rsi.nameless;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class Shop extends Event implements Serializable{
    private ArrayList<Weapon> WeaponsInShop;
    private ArrayList<Armour> ArmourInShop;
    private ArrayList<Potion> PotionsInShop;
    private ArrayList<Item> ItemsInShop;
    private ItemLibrary items;
    private int level;
    private Character player;

    public Shop(int level, ItemLibrary items){
        WeaponsInShop = new ArrayList<>();
        ArmourInShop = new ArrayList<>();
        PotionsInShop = new ArrayList<>();
        ItemsInShop = new ArrayList<>();
        this.items = items;
        this.level = level;
        addLevelItems();
    }

    public void addLevelItems(){
        WeaponsInShop.add(items.getWeapon(1));
        ItemsInShop.add(items.getWeapon(1));
        for(int i=0; i < items.getNrWeapons(); i++){
            Weapon weapon = items.getWeapon(i);
            boolean alreadyInShop = false;
            for(int j=0; j< WeaponsInShop.size(); j++){
                if(weapon.equals(WeaponsInShop.get(j)))
                    alreadyInShop = true;
            }
            if(!alreadyInShop && weapon.getLevel()==level && !weapon.getName().equals("No weapon")){
                WeaponsInShop.add(weapon);
                ItemsInShop.add(weapon);
            }
        }

        PotionsInShop.add(items.getPotion(0));
        ItemsInShop.add(items.getPotion(0));
        for(int i=0; i < items.getNrPotions(); i++){
            Potion potion = items.getPotion(i);
            boolean alreadyInShop = false;
            for(int j=0; j< PotionsInShop.size(); j++){
                if(potion.equals(PotionsInShop.get(j)))
                    alreadyInShop = true;
            }
            if(!alreadyInShop && potion.getLevel() == level){
                PotionsInShop.add(potion);
                ItemsInShop.add(potion);
            }
        }

        for(int i=0; i < items.getNrArmour(); i++){
            Armour armour = items.getArmour(i);
            boolean alreadyInShop = false;
            for(int j=0; j< ArmourInShop.size(); j++){
                if(armour.equals(ArmourInShop.get(j)))
                    alreadyInShop = true;
            }
            if(!alreadyInShop && armour.getLevel() == level && !armour.getName().equals("No armour")){
                ArmourInShop.add(armour);
                ItemsInShop.add(armour);
            }
        }
    }

    public void setPlayer(Character player){
        this.player = player;
    }

    public int getNrOfItems(){
        return ItemsInShop.size();
    }

    public ArrayList<Item> getItemsInShop(){
        return ItemsInShop;
    }

    public Character getPlayer(){
        return player;
    }
}
