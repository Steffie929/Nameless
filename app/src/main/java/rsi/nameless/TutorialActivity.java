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

    public void playerInfo(View v) {
        int imgID = R.drawable.char_icon_small;
        String information = "";
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
                "Stop your opponent from doing damage.\n" +
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
        String information = "In this game you can choose which route you want to take. Your current location on the map " +
                "is shown by the location of your icon. You start at the bottom of the map and you have to find a route " +
                "to the top. \nAt every point you have to choose between the one or two points directly above you. " +
                "You can do this by either tapping the point you want to travel to or by swiping up and left for the leftmost point " +
                "or swiping up and right for the rightmost point.";
        displayInformationScreen(imgID, information);
    }

    public void backpackInfo(View v) {
        int imgID = R.drawable.backpack;
        String information = "Here you can see all the items that are currently in your backpack.\n" +
                "You can also equip a different weapon or different armour. Just tap on the icon of a weapon or armor and you will be first shown" +
                " more information about it and then you can choose to equip it. \n" +
                "If you have potions with you you can use them the same way. Tap on the icon and then select the 'use' option.";
        displayInformationScreen(imgID, information);
    }

    public void conversationInfo(View v) {
        int imgID = R.drawable.textbubble;
        String information = "Sometimes you will enter a conversation with someone. This usually happens at the start and end of a level.\n" +
                "You can see what is being said at the top of the screen. \nAt the bottom of the screen you will see three " +
                "choices you can take. \nWhen you know how you want to react, just tap the option you like best.";
        displayInformationScreen(imgID, information);
    }

    public void shopInfo(View v) {
        int imgID = R.drawable.shop_small;
        String information = "This is where you can buy and sell items. \n" +
                "At the top of the screen you can see the items that are currently in the shop." +
                "At the bottom of the screen you can see the items you have in your inventory.\n" +
                "The items that the shop has in stock depend on which level you are currently in. \n" +
                "A higher level map means more interesting items in the shop ;). " +
                "If you tap on the icon of an item you will then be shown a dialog with more information and you can" +
                "choose to buy or sell that item.";
        displayInformationScreen(imgID, information);
    }

    public void characterInfo(View v) {
        int imgID = R.drawable.stat_icon;
        String information = "Character Screen\n\n" +
                "In the character screen you can see\nyour character's statistics.\n" +
                "From top to bottom, you will see your:\n-Name\n-Level\n-Maximum HP (+Bonus)\n-Current HP / Maximum HP\n" +
                "-Strength (+Bonus)\n-Defense (+Bonus)\n-Skill (+Bonus)\n-Speed (+Bonus)\n-Experience points (XP)\n-Gold\n" +
                "In the bottom left corner you can see\nthe weapon and armour you\ncurrently have equipped.\n" +
                "Click the Back button in the lower right corner to exit the screen.";
        displayInformationScreen(imgID, information);
    }

    public void savedInfo(View v) {
        int imgID = R.drawable.save_icon;
        String information = "When you are not in a battle or in a conversation you can save your progress by clicking on the icon above " +
                "when you are on the map. \nYou then have to choose in which save slot you want to store your information and that's it.";
        displayInformationScreen(imgID, information);
    }

    private void displayInformationScreen(int imgID, String information) {
        Intent intent = new Intent(this, Information.class);
        intent.putExtra("IMAGE_ID", imgID);
        intent.putExtra("INFORMATION_TEXT", information);
        startActivity(intent);
    }
}
