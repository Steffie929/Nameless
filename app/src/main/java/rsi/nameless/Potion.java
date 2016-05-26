package rsi.nameless;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class Potion extends Item {
    private final int HPRestore;
    private int nrUses;

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
     * Use the potion!
     * @return true if the potion can be used (and has been used)
     */
    public boolean canBeUsed() {
        return nrUses > 0;
    }

    public boolean afterUsing() {
        if (nrUses > 0) {
            nrUses--;
        }
        return nrUses > 0;
    }

}
