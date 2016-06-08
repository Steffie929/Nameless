package rsi.nameless;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class Weapon extends Item {

    /**
     * Constructor
     * @param imgID The id of the drawable associated with the weapon
     */
    public Weapon (String itemName, String description, int strB, int defB, int sklB, int spdB, int HPB, int level, int price, int imgID) {
        super(itemName, description, strB, defB, sklB, spdB, HPB, level, price, ItemType.WEAPON, imgID);
    }
}
