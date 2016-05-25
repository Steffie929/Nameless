package rsi.nameless;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public enum BattleAction {
    ATTACK (false, true),
    DEFEND (false, false),
    USE_ITEM (true, false),
    CHANGE_WEAPON (true, false),
    RUN (false, true),
    NOTHING (false, false);

    private final boolean itemPossible;
    private final boolean repeatable;

    BattleAction (boolean itemPossible, boolean repeatable) {
        this.itemPossible = itemPossible;
        this.repeatable = repeatable;
    }

    /**
     * Getter for itemPossible
     * @return true if an item can be used while performing the action
     */
    public boolean itemPossible () {
        return itemPossible;
    }

    /**
     * Getter for repeatable
     * @return true if the action can be performed multiple times in 1 turn
     */
    public boolean repeatable () {
        return repeatable;
    }
}
