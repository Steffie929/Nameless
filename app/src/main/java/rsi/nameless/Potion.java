package rsi.nameless;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class Potion extends Item {
    private final int HPRestore;
    private int nrUses;

    /**
     * Constructor, mostly passes everything on the the constructor of Item
     * Potion handles HPRestore, the amount of HP that is restored when using the Potion and
     * nrUses, the number of times the Potion can still be used, which decreases after every use
     */
    public Potion (String itemName, String description, int strB, int defB, int sklB, int spdB, int HPB, int level, int price, int HPRestore, int nrUses, int imgID) {
        super(itemName, description, strB, defB, sklB, spdB, HPB, level, price, ItemType.POTION, imgID);
        this.HPRestore = HPRestore;
        this.nrUses = nrUses;
    }

    /**
     * @return the HPRestore
     */
    public int getHPRestore() {
        return HPRestore;
    }
    /**
     * @return the nrUses
     */
    public int getNrUses() {
        return nrUses;
    }

    /**
     * Check if the potion can still be used
     * @return true if the potion can be used (and has been used)
     */
    public boolean canBeUsed() {
        return nrUses > 0;
    }

    /**
     * A method that needs to be called every time after using a potion
     * If the potion could still be used, decreases the number of uses it has
     * @return true if the potion can still be used after it was used this time
     */
    public boolean afterUsing() {
        if (nrUses > 0) {
            nrUses--;
        }
        return nrUses > 0;
    }

}
