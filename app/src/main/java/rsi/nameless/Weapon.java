package rsi.nameless;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class Weapon extends Item {
    public Weapon (String itemName, String description, int strB, int defB, int sklB, int spdB, int HPB, int level, int price, int imgID) {
        super(itemName, description, strB, defB, sklB, spdB, HPB, level, price, ItemType.WEAPON, imgID);
    }
}
