package rsi.nameless;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {
    private Shop shop;
    private Character player;
    private TextView playerGold;
    private Context context;
    private GridView gridShop, gridPlayer;
    private ImageAdapter iaShop, iaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        playerGold = (TextView) findViewById(R.id.gold_counter);
        shop = (Shop) getIntent().getSerializableExtra("CURRENT_SHOP");
        player = shop.getPlayer();
        playerGold.setText("" + player.getGold());
        context = this;
        gridShop = (GridView) findViewById(R.id.ShopInventory);
        iaShop = new ImageAdapter(this);
        gridPlayer = (GridView) findViewById(R.id.PlayerInventory);
        iaPlayer = new ImageAdapter(this);
        updateGridviews();

        startAct();
    }

    /**
     * Create the interactions that happen when an item is clicked in the inventory of the shop/player
     */
    public void startAct(){
        gridShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
                final Item clickedItem = shop.getItemsInShop().get(position);
                final AlertDialog.Builder helpBuilder = new AlertDialog.Builder(context);
                helpBuilder.setTitle(clickedItem.getName());
                helpBuilder.setMessage(clickedItem.getDescription()+"\n\nCosts: " + clickedItem.getPrice() + " gold.");

                helpBuilder.setPositiveButton("Buy",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                if(player.getGold()< clickedItem.getPrice()){
                                    extraMessage("You don't have enough gold");
                                } else if (player.backpackIsFull()) {
                                    extraMessage("There is no space in your backpack!\nA backpack can hold a maximum of 30 items");
                                } else {
                                    if(shop.getItemsInShop().remove(clickedItem)) {
                                        player.addItemToBackpack(clickedItem);
                                        player.changeGold(-clickedItem.getPrice());
                                        iaShop.removeItem(position);
                                    }
                                    updateGridviews();
                                }
                            }
                        });
                helpBuilder.setNegativeButton("Back",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                AlertDialog helpDialog = helpBuilder.create();
                helpDialog.show();

            }
        });

        gridPlayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
                Log.d("SHOP", "playergold: " + player.getGold() + " pos: " + position);
                final Item clickedItem = player.getItemFromBackPack(position);
                final AlertDialog.Builder helpBuilder = new AlertDialog.Builder(context);
                helpBuilder.setTitle(clickedItem.getName());
                helpBuilder.setMessage(clickedItem.getDescription()+"\n\nSell value: " + (clickedItem.getPrice()/2) + " gold.");

                helpBuilder.setPositiveButton("Sell",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                player.changeGold((clickedItem.getPrice()/2));
                                player.removeItemFromBackpack(position);
                                shop.getItemsInShop().add(clickedItem);
                                updateGridviews();
                            }
                        });
                helpBuilder.setNegativeButton("Back",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                AlertDialog helpDialog = helpBuilder.create();
                helpDialog.show();

            }
        });

    }

    /**
     * Display message s using a pop-up window (AlertDialog)
     * Pop-up window is closed by clicking the OK button, no other interactions possible
     * @param s The message to display
     */
    public void extraMessage(String s){
        final AlertDialog.Builder helpBuilder = new AlertDialog.Builder(context);
        helpBuilder.setTitle("");
        helpBuilder.setMessage(s);

        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    /**
     * Update the grids of the shop and the player
     * The user will see this as the appearing/disappearing of items that were bought/sold
     */
    public void updateGridviews(){
        iaShop.clearItems();
        ArrayList<Item> items = shop.getItemsInShop();
        for(int i=0; i< items.size() ;i++){
            iaShop.addItemId(items.get(i).getImgID());
        }
        gridShop.setAdapter(iaShop);

        iaPlayer.clearItems();
        ArrayList<Item> backpack = player.getBackpack();
        for(int i=0; i< backpack.size() ;i++){
            iaPlayer.addItemId(backpack.get(i).getImgID());
        }
        gridPlayer.setAdapter(iaPlayer);
        playerGold.setText("" + player.getGold());
    }

    /**
     * Exit the shop
     * Returns the player that was buying items in the shop to the previous activity,
     * so the previous activity can handle the changed items/gold of the player
     */
    public void exitShop(View v) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("Character_Key", player);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

}
