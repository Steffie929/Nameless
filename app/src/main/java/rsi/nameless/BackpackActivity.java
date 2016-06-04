package rsi.nameless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BackpackActivity extends AppCompatActivity {
    private Character player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpack);
        player = (Character) getIntent().getSerializableExtra("CURRENT_PLAYER");
    }
}
