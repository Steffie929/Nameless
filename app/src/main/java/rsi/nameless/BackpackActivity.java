package rsi.nameless;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class BackpackActivity extends AppCompatActivity {
    private Character player;
    private ArrayList<Item> backpack;
    private final int maxBackpackSize = 30;
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpack);
        player = (Character) getIntent().getSerializableExtra("CURRENT_PLAYER");
        backpack = player.getBackpack();
        table = (TableLayout) findViewById(R.id.backpack_table);
        fillBackpack();
    }

    private void fillBackpack() {
        table.removeAllViews();
        TableRow tr_head = new TableRow(this);
        tr_head.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        TextView label_hello = new TextView(this);
        String playerName = player.getName();
        if (playerName != null && !playerName.equals("") & playerName.charAt(playerName.length() - 1) == 's') {
            playerName += "'";
        } else {
            playerName += "'s";
        }
        label_hello.setText(playerName);
        label_hello.setTextColor(Color.WHITE);
        label_hello.setPadding(5, 5, 5, 5);
        tr_head.addView(label_hello);

        TextView label_android = new TextView(this);
        label_android.setText("Backpack");
        label_android.setTextColor(Color.WHITE);
        label_android.setPadding(5, 5, 5, 5);
        tr_head.addView(label_android);

        table.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        final BackpackActivity backpackactivity = this;
        int size = backpack.size();
        for (int i = 0; i < size; i++) {
            final int itemIndex = i;
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            final Item item = backpack.get(i);
            ImageView image = new ImageView(this);
            Drawable draw = ResourcesCompat.getDrawable(getResources(), item.getImgID(), null);
            image.setImageDrawable(draw);
            image.setPadding(5, 5, 5, 5);
            row.addView(image);

            TextView text = new TextView(this);
            text.setText(getItemInformation(item, false));
            text.setTextColor(Color.WHITE);
            text.setPadding(5, 5, 5, 5);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Backpack", item.getName() + " was clicked");
                    final ItemType itemType = item.getType();
                    String positiveAction;
                    String message;
                    if (itemType == ItemType.POTION) {
                        positiveAction = "Use";
                        message = backpackactivity.getItemInformation(item, true);
                    }  else {
                        positiveAction = "Equip";
                        message = "Equid the following item:\n" + backpackactivity.getItemInformation(item, true);
                        message += "\n\n";
                        if (itemType == ItemType.WEAPON) {
                            if (!player.getWeapon().getName().equals("No weapon"))
                                message += "Current weapon:\n" + backpackactivity.getItemInformation(player.getWeapon(), true);
                            else {
                                message += "You currently have no weapon!";
                            }
                        } else if (itemType == ItemType.ARMOUR){
                            if (!player.getArmour().getName().equals("No armour"))
                                message += "Current armour:\n" + backpackactivity.getItemInformation(player.getArmour(), true);
                            else {
                                message += "You currently have no armour!";
                            }
                        }
                    }
                    final AlertDialog.Builder helpBuilder = new AlertDialog.Builder(backpackactivity);
                    helpBuilder.setTitle(item.getName());
                    helpBuilder.setMessage(message);
                    helpBuilder.setPositiveButton(positiveAction,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    if (itemType == ItemType.POTION) {
                                        Potion potion = (Potion) item;
                                        if (potion.canBeUsed()) {
                                            player.changeCurrentHP(potion.getHPRestore());
                                            player.boostStrength(potion.getStrengthBonus());
                                            player.boostDefense(potion.getDefenseBonus());
                                            player.boostSkill(potion.getSkillBonus());
                                            player.boostSpeed(potion.getSpeedBonus());
                                            player.boostMaxHP(potion.getHPBonus());
                                            if (!potion.afterUsing()) {
                                                player.getBackpack().remove(potion);
                                                backpack = player.getBackpack();
                                                fillBackpack();
                                            }
                                        }
                                    } else if (itemType == ItemType.WEAPON) {
                                        player.setWeapon(itemIndex);
                                        player.changeCharIcon();
                                        backpack = player.getBackpack();
                                        fillBackpack();
                                    } else if (itemType == ItemType.ARMOUR) {
                                        player.setArmour(itemIndex);
                                        player.changeCharIcon();
                                        backpack = player.getBackpack();
                                        fillBackpack();
                                    }
                                }
                            });
                    helpBuilder.setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    AlertDialog helpDialog = helpBuilder.create();
                    helpDialog.setCancelable(false);
                    helpDialog.show();
                }
            });
            row.addView(text);

            table.addView(row, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    /**
     * The method called by activity_backpack.xml when Back_Button is clicked
     * Go back to the previous activity by closing this one
     */
    public void backpackBackButton (View v) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("Character_Key", player);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    /**
     * The method called by activity_backpack.xml when Character_Button is clicked
     * Go to the "Character" screen
     */
    public void backpackCharacterButton (View v) {
        Intent intent = new Intent(this, CharacterScreen.class);
        intent.putExtra("CURRENT_PLAYER", player);
        startActivity(intent);
    }

    /**
     * Makes a String with all information about the Item neatly organized
     * @return The String that contains all information about the Item
     */
    public String getItemInformation(Item item, boolean removeOneTabFromStrength) {
        String result = new String("");
        result += item.getName() + "\n";
        int strTabNumber = getTabNumber(item.getStrengthBonus(), 12);
        if (removeOneTabFromStrength) {
            strTabNumber--;
        }
        int defTabNumber = getTabNumber(item.getDefenseBonus(), 12);
        int sklTabNumber = getTabNumber(item.getSkillBonus(), 15);
        result += "Strength: " + item.getStrengthBonus() + getTabs(strTabNumber) + "Speed: " + item.getSpeedBonus() + "\n";
        result += "Defense: " + item.getDefenseBonus() + getTabs(defTabNumber) + "HP: " + item.getHPBonus() + "\n";
        result += "Skill: " + item.getSkillBonus() + getTabs(sklTabNumber) + "Level: " + item.getLevel() + "\n";
        return result;
    }

    /**
     * @param number The number of tabs
     * @return A string that contains the specified number of tabs
     */
    private String getTabs(int number) {
        String result = new String("");
        for (int i = 0; i < number; i++) {
            result += getString(R.string.tab);
        }
        return result;
    }

    /**
     * Calculates the number of tabs needed to perfectly align the next column
     * @param stat The number of the stat after which the tabs are needed
     * @param base The base number of tabs
     * @return The number of tabs that should be used
     */
    private int getTabNumber(int stat, int base) {
        if (stat < 0 || stat >= 10) {
            return base - 1;
        } else {
            return base;
        }
    }
}