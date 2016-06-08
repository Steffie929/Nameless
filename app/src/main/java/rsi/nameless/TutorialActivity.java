package rsi.nameless;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
    }

    public void returnToMainMenu(View v) {
        finish();
    }

    /**
     * Decides which drawable and string of information are appropriate for this part of the tutorial
     * All methods ending in -Info do the same thing, but for different subjects
     * Calls the method displayInformationScreen() to display the image and text
     */
    public void playerInfo(View v) {
        int imgID = R.drawable.char_icon_small;
        String information = "Player statistics\n\n" +
                "Your character has a multitude of stats associated to him or her.\n" +
                "Some affect your battle prowess, others are there for another purpose.\n" +
                "Your statistics are:\n\n" +
                "1) Player Name\nYou can name your character anything you want! This doesn't influence" +
                " anything related to the game, it just looks nice. This name also appears on your highscore list," +
                " so show your friends that it really was you who is the very best.\n\n" +
                "2) Level\nGives a vague idea how strong you are. You get stronger with every level. " +
                "In the Character Creation Screen, you can select which stats increase by how much by clicking the + or - buttons.\n" +
                "Those numbers are the bonuses you gain for leveling up.\n\n" +
                "3) Hit points (HP)\nEnemy attacks lower your HP. You die when your HP reaches 0. " +
                "You will often see this separated in two numbers: Maximum HP and Current HP.\n" +
                "Your Maximum HP denotes the highest number of hit points your character can possibly have. " +
                "Using health regeneration items will not increase your Maximum HP.\n" +
                "Your Current HP cannot exceed your Maximum HP, but it can be lower. Current HP denotes how much HP you currently have.\n" +
                "Using health regeneration items will restore your Current HP. Any boosts to your Maximum HP will also affect your Current HP.\n\n" +
                "4) Strength (STR)\nDetermines how much damage you do with a single attack. " +
                "This damage is reduced by the defense of your opponent.\n\n" +
                "5) Defense (DEF)\nReduces damage done by opponents.\n\n" +
                "6) Skill (SKL)\nIncreases the chance you successfully hit your opponent. " +
                "Also decreases the chance your opponent hits you.\n\n" +
                "7) Speed (SPD)\nThe character with the highest speed performs their action first.\n" +
                "If the speed difference is great enough, the character with higher speed can potentially strike multiple times in one turn.\n\n" +
                "8) Experience points (XP)\nUsually shown as Current XP/Maximum XP.\n" +
                "Your current XP is how much XP you currently have. This number will increase by killing enemies.\n" +
                "Your maximum XP is not actually a maximum. It is, however, the amount of XP you will need to accumulate to level up.\n" +
                "Any bonus XP you have left over after leveling up will directly go to your next level. No XP is wasted!\n\n" +
                "9) Gold\nDenotes how wealthy you are. To buy items at a shop, you need to have sufficient amounts of gold.\n" +
                "You can gain extra gold by killing enemies of by selling items at a shop.\n\n" +
                "10) Boosts\nBehind stats which can receive temporary boosts you will see the following sequence:\n(+Number), for example (+0) or (+2)\n" +
                "The number denotes the height of the boost.\nBoosts temporarily increase your stats. The boost will end after your next fight with an enemy.\n" +
                "You can gain boosts from special types of potions. The following stats can be increased by boosts:\n" +
                "Max HP, STR, DEF, SKL, SPD.";
        displayInformationScreen(imgID, information);
    }

    public void battleInfo(View v) {
        int imgID = R.drawable.icon_battle;
        String information = "Battles\n\nA battle is indicated on the map by two swords crossing.\n" +
                "In a battle you fight against one opponent.\n" +
                "You win the fight by defeated said opponent.\nThis will earn you a nice reward.\n" +
                "You lose the fight if your HP reaches 0.\nWhen this happens, you die.\n" +
                "You can see your name and HP in\nthe upper left corner of the screen.\n" +
                "Likewise, your opponents information is located\nin the upper right corner of the screen.\n" +
                "You can perform an action by clicking one of the buttons\n" +
                "on the bottom of your screen.\n\n" +
                "In a fight you have the following options:\n" +
                "1) Attacking\n" +
                "Deal damage to your opponent.\nThis damage is increased by you strength.\n" +
                "The damage is reduced by your opponents defense.\n" +
                "Your skill increases the chance you hit your opponent.\n" +
                "If your speed is hy enough, you can attack first\nor even attack multiple times.\n" +
                "2) Defend\n" +
                "Stop your opponent from doing damage. If your opponent attacks you, you do a small amount of damage to him instead. " +
                "This damage is equal to your current level.\n" +
                "3) Use an item\n" +
                "Use a potion to restore HP or boost one of your stats.\n" +
                "Alternatively, equip a different weapon or set of armour.\n" +
                "4) Run away\n" +
                "Try to run away from the battle.\n" +
                "If you manage to escape, the battle will end.\n" +
                "However, you will not get any rewards for running away.";
        displayInformationScreen(imgID, information);
    }

    public void movingInfo(View v) {
        int imgID = R.drawable.desert_background;
        String information = "Moving on the map\n\n" +
                "In this game you can choose which route you want to take. Your current location on the map " +
                "is shown by the location of your icon. You start at the bottom of the map and you have to find a route " +
                "to the top.\nAt every point you have to choose between one or two points directly above you.\n" +
                "You can do this by either tapping the point you want to travel to or by swiping up and left for the leftmost point " +
                "or swiping up and right for the rightmost point.";
        displayInformationScreen(imgID, information);
    }

    public void backpackInfo(View v) {
        int imgID = R.drawable.backpack;
        String information = "Backpack\n\nHere you can see all the items that are currently in your backpack.\n" +
                "You can also equip a different weapon or different armour. Just tap on the description of a weapon or armor and you will be first shown" +
                " more information about it and then you can choose to equip it.\n" +
                "If you have potions with you, you can use them in the same way. Tap on its description and then select the 'Use' option.";
        displayInformationScreen(imgID, information);
    }

    public void conversationInfo(View v) {
        int imgID = R.drawable.textbubble;
        String information = "Conversations\n\nSometimes you will enter a conversation with someone. This usually happens at the start\nand end of a level.\n" +
                "You can see what is being said\nat the top of the screen. \nAt the bottom of the screen you will\nsee three " +
                "choices you can take.\nWhen you know how you want to react, just tap\nthe option you like best.";
        displayInformationScreen(imgID, information);
    }

    public void shopInfo(View v) {
        int imgID = R.drawable.shop_small;
        String information = "Shopping\n\nThis is where you can buy and sell items. \n" +
                "At the top of the screen you can see the items that are currently in the shop." +
                "At the bottom of the screen you can see the items you have in your inventory.\n" +
                "The items that the shop has in stock depend on which level you are currently in. \n" +
                "A higher level map means more interesting items in the shop.\n" +
                "If you tap on the icon of an item you will then be shown a dialog with more information and you can" +
                "choose to buy or sell that item.\n" +
                "Buying an item costs gold. You can see how much gold you currently have on the bottom right of your screen.\n" +
                "Press the 'Exit shop' button on the bottom left of you screen to leave.\n" +
                "Note: You cannot enter a shop after you have left, so choose wisely!";
        displayInformationScreen(imgID, information);
    }

    public void characterInfo(View v) {
        int imgID = R.drawable.stat_icon;
        String information = "Character Screen\n\n" +
                "In the character screen you can see\nyour character's statistics.\n" +
                "From top to bottom, you will see your:\n-Name\n-Level\n-Maximum HP (+Bonus)\n-Current HP / Maximum HP\n" +
                "-Strength (+Bonus)\n-Defense (+Bonus)\n-Skill (+Bonus)\n-Speed (+Bonus)\n-Experience points (XP)\n-Gold\n" +
                "In the bottom left corner you can see\nthe weapon and armour you\ncurrently have equipped.\n" +
                "Click the Back button in the lower right corner to exit the screen.\n\n" +
                "(See the 'Player statistics' tutorial for addition information for all the statistics on the character screen)";
        displayInformationScreen(imgID, information);
    }

    public void savedInfo(View v) {
        int imgID = R.drawable.save_icon;
        String information = "Saving the game\n\n" +
                "When you are not in a battle or in a conversation you can save your progress by clicking on the icon above " +
                "when you are on the map. \nYou then have to choose in which save slot you want to store your information and that's it.";
        displayInformationScreen(imgID, information);
    }

    /**
     * Displays the information string and image in the Information class
     */
    private void displayInformationScreen(int imgID, String information) {
        Intent intent = new Intent(this, Information.class);
        intent.putExtra("IMAGE_ID", imgID);
        intent.putExtra("INFORMATION_TEXT", information);
        startActivity(intent);
    }
}
