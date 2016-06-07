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

    }

    public void battleInfo(View v) {
        int imgID = R.drawable.icon_battle_small;
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

    }

    public void backpackInfo(View v) {

    }

    public void conversationInfo(View v) {

    }

    public void shopInfo(View v) {

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

    }

    private void displayInformationScreen(int imgID, String information) {
        Intent intent = new Intent(this, Information.class);
        intent.putExtra("IMAGE_ID", imgID);
        intent.putExtra("INFORMATION_TEXT", information);
        startActivity(intent);
    }
}
