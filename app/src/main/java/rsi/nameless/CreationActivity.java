package rsi.nameless;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CreationActivity extends AppCompatActivity {
    private int maxChanges, hpMod, strMod, defMod, sklMod, spdMod;
    private TextView hpPoints, strPoints, defPoints, sklPoints, spdPoints, points;

    /**
     * This method is called when the activity is created. It sets the view with default settings
     * and calls updateTextxs().
     * @param savedInstanceState
     */
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

    /**
     * This method sets the return intent and ends the activity
     * @param v
     */
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
        returnIntent.putExtra("NEW_GAME", true);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    public void hpQuestion(View v) {
        String information = "Your hit points (HP).\nEnemy attacks lower your HP.\n"
                + "You die when your HP reaches 0.";
        showMessage("HP Information", information);
    }

    public void strQuestion(View v) {
        String information = "Your strength (STR).\nDetermines how much damage you do.\n";
        showMessage("Strength Information", information);
    }

    public void defQuestion(View v) {
        String information = "Your defense (DEF).\nReduces the damage done by opponents.\n";
        showMessage("Defense Information", information);
    }

    public void sklQuestion(View v) {
        String information = "Your skill (SKL).\nIncreases the chance you\n   successfully hit your opponent.\n"
                + "Also decreases the chance\n   your opponent hits you.\n";
        showMessage("Skill Information", information);
    }

    public void spdQuestion(View v) {
        String information = "Your speed (SPD).\nDetermines who attacks first.\n"
                + "If the speed difference is great enough," +
                "\nyou can attack multiple times!";
        showMessage("Speed Information", information);
    }

    public void showMessage(String title, String information) {
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle(title);
        helpBuilder.setMessage(information);
        helpBuilder.setNegativeButton("Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
}
