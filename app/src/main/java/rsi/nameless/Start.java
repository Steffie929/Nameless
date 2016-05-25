package rsi.nameless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }


    public void startNewGame(View v){
        Intent intent = new Intent(this, MapActivity.class);
        EditText editText = (EditText) findViewById(R.id.choosePlayerName);
        String message = editText.getText().toString();
        intent.putExtra("PLAYER_NAME", message);
        startActivity(intent);
    }
}
