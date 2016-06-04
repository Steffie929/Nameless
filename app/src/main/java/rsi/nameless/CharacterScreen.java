package rsi.nameless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class CharacterScreen extends AppCompatActivity {
    private Character player;
    private ArrayList<Item> backpack;
    private TextView statTitle, playerStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_screen);
        player = (Character) getIntent().getSerializableExtra("CURRENT_PLAYER");
        backpack = player.getBackpack();
        statTitle = (TextView) findViewById(R.id.playerStatsHeader);
        playerStats =  (TextView) findViewById(R.id.playerStatsTV);
    }
}
