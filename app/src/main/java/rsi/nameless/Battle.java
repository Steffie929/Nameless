package rsi.nameless;

import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class Battle extends Event implements Serializable{
    private Character player;
    private Character enemy;
    private BattleAction playerAction;
    private int playerActionRepeats;
    private int inventoryNumber;
    private BattleAction enemyAction;
    private int enemyActionRepeats;
    private int enemyInventoryNumber;
    private Random random;
    private boolean ranAway;
    private String battleInfo;
    private String battleRewards;

    public Battle (Character player, Character enemy) {
        this.player = player;
        this.enemy = enemy;
        playerAction = BattleAction.NOTHING;
        enemyAction = BattleAction.NOTHING;
        inventoryNumber = -1;
        random = new Random();
        ranAway = false;
        battleInfo = "Not initialised yet";
        battleRewards = new String();
    }

    /**
     * Determines if someone ran away or someone is dead
     * @return true if the battle is over
     */
    public boolean battleFinished () {
        return isRanAway() || playerDead() || playerWon();
    }

    /**
     * Determine if the player has won the battle
     * @return true if the enemy is dead but the player is not
     */
    public boolean playerWon () {
        return enemy.getCurrentHP() <= 0
                && player.getCurrentHP() > 0;
    }

    /**
     * Determine if te player is dead
     * @return true if the HP of the player is 0 (or lower)
     */
    public boolean playerDead () {
        return player.getCurrentHP() <= 0;
    }

    /**
     * Basic setter for playerAction
     * inventory only relevent for USE_ITEM and CHANGE_WEAPON
     * Otherwise, inventoryNumber will be set to -1
     * @param newAction The next action to be performed by the player
     * @param inventoryNumber If applicable, the number of the item to use in the inventory
     */
    public void setPlayerAction (BattleAction newAction, int inventoryNumber) {
        if (playerAction == newAction) {
            playerActionRepeats++;
        } else {
            playerActionRepeats = 1;
        }
        if (playerAction == BattleAction.DEFEND && newAction == BattleAction.DEFEND) {
            int rand = random.nextInt(100);
            int goal = 100 / playerActionRepeats;
            if (rand > goal) {
                playerAction = BattleAction.NOTHING;
            }
        } else {
            playerAction = newAction;
        }
        if (playerAction.itemPossible()) {
            this.inventoryNumber = inventoryNumber;
        } else {
            this.inventoryNumber = -1;
        }
    }

    /**
     * Perform both the enemy action and the player action
     *  Calls the methods performPlayerAction() and performEnemyAction() in the right order.
     */
    public void performActions () {
        battleInfo = "";
        int playerSpeed = player.getSpeed();
        int enemySpeed = enemy.getSpeed();
        int speedDifference = playerSpeed - enemySpeed;
        int speedTier = speedDifference / 5;
        if (speedDifference > 0) {
            boolean repeated = false;
            do {
                performPlayerAction(repeated);
                speedTier--;
                repeated = true;
            } while (playerAction.repeatable() && speedTier >= 0 && !battleFinished());
            performEnemyAction(false);
        } else if (speedDifference < 0) {
            boolean repeated = false;
            do {
                performEnemyAction(repeated);
                speedTier++;
                repeated = true;
            } while (enemyAction.repeatable() && speedTier <= 0 && !battleFinished());
            performPlayerAction(false);
        } else {
            if (random.nextBoolean()) {
                performPlayerAction(false);
                performEnemyAction(false);
            } else {
                performEnemyAction(false);
                performPlayerAction(false);
            }
        }
    }

    /**
     * Perform the action of the player
     */
    private void performPlayerAction (boolean repeated) {
        if (player.getCurrentHP() <= 0) {
            battleInfo += "You died";
            return;
        }

        switch (playerAction) {
            case ATTACK:
                battleInfo += player.getName() + " attacks " + enemy.getName() + "\n";
                if (hitSucces(true) && enemyAction != BattleAction.DEFEND) {
                    int damage = 5 + player.getStrength() - enemy.getDefense();
                    if (repeated) {
                        damage = damage/2;
                    }
                    if (damage > 0) {
                        battleInfo += player.getName() + " does " + damage + " damage\n";
                        enemy.changeCurrentHP(-damage);
                    }
                    else
                        battleInfo += player.getName() + " does 0 damage\n";
                }
                else if(enemyAction == BattleAction.DEFEND)
                    battleInfo += "Your attack was blocked\n";
                else
                    battleInfo += "You missed\n";
                break;
            case DEFEND:
                battleInfo += player.getName() + " defends\n";
                if (enemyAction == BattleAction.ATTACK) {
                    enemy.changeCurrentHP(-player.getLevel());
                    battleInfo += player.getName() + " inflicted " + player.getLevel() + " damage while defending\n";
                }

                break;
            case USE_ITEM:
                Item pitem = player.getItemFromBackPack(inventoryNumber);
                if (pitem == null) {
                    battleInfo += "Using item failed\n";
                    return;
                }
                ItemType ptype = pitem.getType();
                if (ptype == ItemType.POTION) {
                    Potion potion = (Potion) pitem;
                    if (potion.canBeUsed()) {
                        battleInfo += player.getName() + " used " + potion.getName() + "\n";
                        player.changeCurrentHP(potion.getHPRestore());
                        player.boostStrength(potion.getStrengthBonus());
                        player.boostDefense(potion.getDefenseBonus());
                        player.boostSkill(potion.getSkillBonus());
                        player.boostSpeed(potion.getSpeedBonus());
                        player.boostMaxHP(potion.getHPBonus());
                        if (!potion.afterUsing())
                            player.getBackpack().remove(potion);
                    }
                } else if (ptype == ItemType.WEAPON) {
                    player.setWeapon(inventoryNumber);
                    battleInfo += player.getName() + " equiped " + player.getWeapon().getName() + "\n";
                } else if (ptype == ItemType.ARMOUR) {
                    player.setArmour(inventoryNumber);
                    battleInfo += player.getName() + " equiped " + player.getArmour().getName() + "\n";
                }
                break;
            case CHANGE_WEAPON:
                player.setWeapon(inventoryNumber);
                break;
            case RUN:
                battleInfo += player.getName() + " tries to run away\n";
                int goal = 50;
                int difference = player.getSpeed() - enemy.getSpeed();
                goal += difference*10;
                int rand = random.nextInt(100);
                if (rand < goal) {
                    battleInfo += "You escaped!\n";
                    ranAway = true;
                }
                else
                    battleInfo += enemy.getName() + " was too fast to escape!\n";
                break;
            default:
                battleInfo += player.getName() + " did nothing\n";
                break;
        }
    }

    /**
     * Perform the action of the enemy
     */
    private void performEnemyAction (boolean repeated) {
        if (enemy.getCurrentHP() <= 0) {
            battleInfo += enemy.getName() + " died.\n";
            return;
        }
        switch (enemyAction) {
            case ATTACK:
                battleInfo += enemy.getName() + " attacks " + player.getName() + "\n";
                if (hitSucces(false) && playerAction != BattleAction.DEFEND) {
                    int damage = 5 + enemy.getStrength() - player.getDefense();
                    if (repeated) {
                        damage = damage/2;
                    }
                    if (damage > 0) {
                        battleInfo += enemy.getName() + " does " + damage + " damage\n";
                        player.changeCurrentHP(-damage);
                    }
                    else
                        battleInfo += enemy.getName() + " does 0 damage\n";
                }
                else if(playerAction == BattleAction.DEFEND)
                    battleInfo += enemy.getName() + " was blocked\n";
                else
                    battleInfo += enemy.getName() + " missed\n";
                break;
            case DEFEND:
                battleInfo += enemy.getName() + " defends\n";
                if (playerAction == BattleAction.ATTACK) {
                    player.changeCurrentHP(-enemy.getLevel());
                    battleInfo += enemy.getName() + " inflicted " + enemy.getLevel() + " damage while defending\n";
                }
                break;
            case USE_ITEM:
                battleInfo += enemy.getName() + " uses an item\n";
                Item pitem = enemy.getItemFromBackPack(enemyInventoryNumber);
                if (pitem == null) {
                    battleInfo += "Item use failed\n";
                    return;
                }
                ItemType ptype = pitem.getType();
                if (ptype == ItemType.POTION) {
                    Potion potion = (Potion) pitem;
                    if (potion.canBeUsed()) {
                        battleInfo += enemy.getName() + " used " + potion.getName() + "\n";
                        enemy.changeCurrentHP(potion.getHPRestore());
                        enemy.changeStrength(potion.getStrengthBonus());
                        enemy.changeDefense(potion.getDefenseBonus());
                        enemy.changeSkill(potion.getSkillBonus());
                        enemy.changeSpeed(potion.getSpeedBonus());
                        enemy.changeMaxHP(potion.getHPBonus());
                        if (!potion.afterUsing())
                            enemy.getBackpack().remove(potion);
                    } else if (ptype == ItemType.WEAPON) {
                        enemy.setWeapon(inventoryNumber);
                        battleInfo += enemy.getName() + " equiped " + enemy.getWeapon().getName() + "\n";
                    } else if (ptype == ItemType.ARMOUR) {
                        enemy.setArmour(inventoryNumber);
                        battleInfo += enemy.getName() + " equiped " + enemy.getArmour().getName() + "\n";
                    }
                }
                break;
            case CHANGE_WEAPON:
                enemy.setWeapon(enemyInventoryNumber);
                break;
            case RUN:
                battleInfo += enemy.getName() + " tries to run away\n";
                int goal = 50;
                int difference = enemy.getSpeed() - player.getSpeed();
                goal += difference*10;
                int rand = random.nextInt(100);
                if (rand < goal) {
                    battleInfo += enemy.getName() + " escaped\n";
                    ranAway = true;
                }
                else
                    battleInfo += "You kept it from escaping\n";
                break;
            default:
                battleInfo += enemy.getName() + " did nothing\n";
                break;
        }
    }

    /**
     * Determines if the action performed by the character specified by the
     * boolean playerTurn is a succes of fails
     * @param playerTurn true if player is performing the action
     * @return true if the action is a succes
     */
    private boolean hitSucces (boolean playerTurn) {
        int difference;
        int rand = random.nextInt(100);
        if (playerTurn) {
            difference = player.getSkill() - enemy.getSkill();
        } else {
            difference = enemy.getSkill() - player.getSkill();
        }
        int goal;
        if (difference > 0) {
            goal = 80 + difference*difference - difference;
        } else {
            goal = 80 - difference*difference + difference;
        }
        return rand < goal;
    }

    /**
     * Getter for player
     * @return the player
     */
    public Character getPlayer () {
        return player;
    }

    public Character getEnemy () {
        return enemy;
    }

    public void chooseEnemyAction () {
        int rand = random.nextInt(100);
        if (enemyAction == BattleAction.DEFEND) {
            if (rand < 10) {
                enemyActionRepeats++;
                int randDef = random.nextInt(100);
                int goal = 100 / enemyActionRepeats;
                if (randDef > goal) {
                    enemyActionRepeats = 1;
                    enemyAction = BattleAction.NOTHING;
                } else {
                    enemyAction = BattleAction.DEFEND;
                }
            } else {
                enemyActionRepeats = 1;
                enemyAction = BattleAction.ATTACK;
            }
        } else {
            if (rand < 25) {
                enemyActionRepeats = 1;
                enemyAction = BattleAction.DEFEND;
            } else if (rand < 40 && enemy.checkBackPackForPotion()) {
                ArrayList<Item> backpack = enemy.getBackpack();
                int size = backpack.size();
                for (int i = 0; i < size; i++) {
                    Item item = backpack.get(i);
                    if (item.getType() == ItemType.POTION) {
                        enemyAction = BattleAction.USE_ITEM;
                        enemyInventoryNumber = i;
                        break;
                    }
                }
            } else {
                enemyActionRepeats++;
                enemyAction = BattleAction.ATTACK;
            }
        }
    }

    public String getBattleInfo(){
        return battleInfo;
    }


    public String toString() {
        String result = "";
        result += player.getName() + " hp: " + player.getCurrentHP() + "/" + player.getMaxHP() + " " + player.getWeapon().getName() + "\n";
        result += enemy.getName() + " hp: " + enemy.getCurrentHP() + "/" + enemy.getMaxHP() + " " + enemy.getWeapon().getName() +  "\nEnemy chose: ";
        switch(enemyAction) {
            case ATTACK:
                result += "Attack";
                break;
            case DEFEND:
                result += "Defend";
                break;
            case USE_ITEM:
                result += "Use item";
                break;
            default:
                result += "Misc";
        }
        result += "\n";
        return result;
    }

    /**
     * @return the ranAway
     */
    public boolean isRanAway() {
        return ranAway;
    }

    public void getRewards() {
        int goldReward = enemy.getGold();
        player.changeGold(goldReward);
        battleRewards += "Gold earned: " + goldReward + "\n";

        int bonusXP = enemy.getMaxXP();
        player.giveBonusXP(bonusXP);
        battleRewards += "XP earned: " + bonusXP + "\n";

        ArrayList<Item> possibleRewards = enemy.getBackpack();
        Weapon enemyWeapon = enemy.getWeapon();
        if (enemyWeapon != null && !enemyWeapon.getName().equals("No weapon")) {
            possibleRewards.add(enemyWeapon);
        }
        Armour enemyArmour = enemy.getArmour();
        if (enemyArmour != null && !enemyArmour.getName().equals("No armour")) {
            possibleRewards.add(enemyArmour);
        }
        int nrOfRewards = random.nextInt(2) + 1;
        if (nrOfRewards > possibleRewards.size()) {
            nrOfRewards = possibleRewards.size();
        }
        boolean first = true;
        for (int i = 0; i < nrOfRewards; i++) {
            if (first) {
                battleRewards += "\nItems found:\n";
            }
            int position;
            Item reward = possibleRewards.get(i);
            if (reward.getType() == ItemType.POTION) {
                Potion potionReward = (Potion) reward;
                position = player.addItemToBackpack(potionReward);
            } else if (reward.getType() == ItemType.WEAPON) {
                Weapon weaponReward = (Weapon) reward;
                position = player.addItemToBackpack(weaponReward);
            } else if (reward.getType() == ItemType.ARMOUR) {
                Armour armourReward = (Armour) reward;
                position = player.addItemToBackpack(armourReward);
            } else {
                position = player.addItemToBackpack(reward);
            }
            if (position == -1) {
                battleRewards += "There was no space in your backpack for: ";
            } else {
                battleRewards += "-";
            }
            battleRewards += reward.getName() + "\n";
            first = false;
        }
    }

    public String getRewardString() {
        return battleRewards;
    }
}
