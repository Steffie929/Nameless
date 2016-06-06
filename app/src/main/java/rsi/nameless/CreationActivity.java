package rsi.nameless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CreationActivity extends AppCompatActivity {
    private int maxChanges, hpMod, strMod, defMod, sklMod, spdMod;
    private TextView hpPoints, strPoints, defPoints, sklPoints, spdPoints, points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);
        hpPoints = (TextView) findViewById(R.id.hpPoints);
        strPoints = (TextView) findViewById(R.id.strPoints);
        defPoints = (TextView) findViewById(R.id.defPoints);
        sklPoints = (TextView) findViewById(R.id.sklPoints);
        spdPoints = (TextView) findViewById(R.id.spdPoints);
        points = (TextView) findViewById(R.id.Points);

        maxChanges = 15;
        hpMod = 0;
        strMod = 0;
        defMod = 0;
        sklMod = 0;
        spdMod = 0;

        updateTexts();
    }

    public void updateTexts() {
        hpPoints.setText(Integer.toString(hpMod));
        strPoints.setText(Integer.toString(strMod));
        defPoints.setText(Integer.toString(defMod));
        sklPoints.setText(Integer.toString(sklMod));
        spdPoints.setText(Integer.toString(spdMod));
        points.setText(Integer.toString(maxChanges));
    }


    public void hpMinus(View v) {
        if (hpMod > 0) {
            hpMod--;
            maxChanges++;
            points.setText(Integer.toString(maxChanges));
            hpPoints.setText(Integer.toString(hpMod));
        }
    }

    public void hpPlus(View v) {
        if (maxChanges > 0) {
            maxChanges--;
            hpMod++;
            points.setText(Integer.toString(maxChanges));
            hpPoints.setText(Integer.toString(hpMod));
        }
    }

    public void strMinus(View v) {
        if (strMod > 0) {
            strMod--;
            maxChanges++;
            points.setText(Integer.toString(maxChanges));
            strPoints.setText(Integer.toString(strMod));
        }
    }

    public void strPlus(View v) {
        if (maxChanges > 0) {
            maxChanges--;
            strMod++;
            points.setText(Integer.toString(maxChanges));
            strPoints.setText(Integer.toString(strMod));
        }
    }

    public void defMinus(View v) {
        if(defMod > 0) {
            defMod--;
            maxChanges++;
            points.setText(Integer.toString(maxChanges));
            defPoints.setText(Integer.toString(defMod));
        }
    }

    public void defPlus(View v) {
        if(maxChanges > 0) {
            maxChanges--;
            defMod++;
            points.setText(Integer.toString(maxChanges));
            defPoints.setText(Integer.toString(defMod));
        }
    }

    public void sklMinus(View v) {
        if(sklMod > 0) {
            sklMod--;
            maxChanges++;
            points.setText(Integer.toString(maxChanges));
            sklPoints.setText(Integer.toString(sklMod));
        }
    }

    public void sklPlus(View v) {
        if(maxChanges > 0) {
            maxChanges--;
            sklMod++;
            points.setText(Integer.toString(maxChanges));
            sklPoints.setText(Integer.toString(sklMod));
        }
    }

    public void spdMinus(View v) {
        if(spdMod > 0) {
            spdMod--;
            maxChanges++;
            points.setText(Integer.toString(maxChanges));
            spdPoints.setText(Integer.toString(spdMod));
        }
    }

    public void spdPlus(View v) {
        if(maxChanges > 0) {
            maxChanges--;
            spdMod++;
            points.setText(Integer.toString(maxChanges));
            spdPoints.setText(Integer.toString(spdMod));
        }
    }

    public void startGame(View v) {
        Intent returnIntent = new Intent();
        EditText editText = (EditText) findViewById(R.id.choosePlayerName);
        String playerName = editText.getText().toString();
        returnIntent.putExtra("HP_MOD", hpMod);
        returnIntent.putExtra("STR_MOD", strMod);
        returnIntent.putExtra("DEF_MOD", defMod);
        returnIntent.putExtra("SKL_MOD", sklMod);
        returnIntent.putExtra("SPD_MOD", spdMod);
        returnIntent.putExtra("PLAYER_NAME", playerName);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
