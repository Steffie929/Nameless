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

    public void startAct(){
        gridShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
                Log.d("SHOP", "playergold: " + player.getGold() + " pos: " + position);
                final Item clickedItem = shop.getItemsInShop().get(position);
                final AlertDialog.Builder helpBuilder = new AlertDialog.Builder(context);
                helpBuilder.setTitle(clickedItem.getName());
                helpBuilder.setMessage(clickedItem.getDescription()+"\n\nCosts: " + clickedItem.getPrice() + " gold.");

                helpBuilder.setPositiveButton("Buy",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                if(player.getGold()< clickedItem.getPrice()){

                                }
                                else{
                                    int dummy = player.addItemToBackpack(clickedItem);
                                    player.setGold(0-clickedItem.getPrice());
                                    boolean a = shop.getItemsInShop().remove(clickedItem);
                                    iaShop.removeItem(position);
                                    updateGridviews();
                                }
                            }
                        });
                helpBuilder.setNegativeButton("Back",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO back
                            }
                        });

                // Remember, create doesn't show the dialog
                AlertDialog helpDialog = helpBuilder.create();
                helpDialog.show();

            }
        });

    }

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

    public void exitShop(){
        finish();
    }

}
