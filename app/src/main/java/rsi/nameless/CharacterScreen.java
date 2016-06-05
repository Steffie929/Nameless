package rsi.nameless;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CharacterScreen extends AppCompatActivity {
    private Character player;
    private ArrayList<Item> backpack;
    private TextView statTitle, playerStats, weapon, armor;
    private ImageView imgView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_screen);
        player = (Character) getIntent().getSerializableExtra("CURRENT_PLAYER");
        backpack = player.getBackpack();
        statTitle = (TextView) findViewById(R.id.playerStatsHeader);
        playerStats =  (TextView) findViewById(R.id.playerStatsTV);
        weapon =  (TextView) findViewById(R.id.weaponEquipped);
        armor =  (TextView) findViewById(R.id.armorEquipped);
        imgView = (ImageView) findViewById(R.id.characterImage);


        int imgID = player.getImgID();
        Drawable d = ResourcesCompat.getDrawable(getResources(), imgID, null);
        imgView.setImageDrawable(d);

        statTitle.setText(player.getName() + "'s statistics");
        String stats = "";
        int[] boosts = player.getBoosts();
        int strength = player.getStrength() - boosts[0];
        int defense = player.getDefense() - boosts[1];
        int skill = player.getSkill() - boosts[2];
        int speed = player.getSpeed() - boosts[3];
        int hp = player.getMaxHP() - boosts[4];
        stats += "Level: " + player.getLevel() + "\n";
        stats += "Maximum HP: " + hp + " (+" + boosts[4] + ")\n";
        stats += "Current HP: " + player.getCurrentHP() + " / " + player.getMaxHP()+ "\n";
        stats += "Strength: " + strength + " (+" + boosts[0] + ")\n";
        stats += "Defense: " + defense + " (+" + boosts[1] + ")\n";
        stats += "Skill: " + skill + " (+" + boosts[2] + ")\n";
        stats += "Speed: " + speed + " (+" + boosts[3] + ")\n";
        stats += "XP: " +  player.getCurrentXP() + " / " + player.getMaxXP()+ "\n";
        stats += "Gold: " + player.getGold();
        playerStats.setText(stats);

        weapon.setText("Weapon: " + player.getWeapon().getName());
        armor.setText("Armour: " + player.getArmour().getName());
    }
}
