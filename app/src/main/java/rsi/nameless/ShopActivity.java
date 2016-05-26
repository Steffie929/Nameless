package rsi.nameless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {
    private Shop shop;
    private ArrayList<Item> items;
    private Character player;
    private ArrayList<Item> backpack;
    private TextView playerGold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        playerGold = (TextView) findViewById(R.id.gold_counter);
        shop = (Shop) getIntent().getSerializableExtra("CURRENT_SHOP");
        items = shop.getItemsInShop();
        player = shop.getPlayer();
        backpack = player.getBackpack();
        playerGold.setText("" + player.getGold());
        startAct();
    }

    public void startAct(){
        GridView gridShop = (GridView) findViewById(R.id.ShopInventory);
        ImageAdapter iaShop = new ImageAdapter(this);

        //fill shop
        for(int i=0; i< items.size() ;i++){
            iaShop.addItemId(items.get(i).getImgID());
        }
        gridShop.setAdapter(iaShop);

        GridView gridPlayer = (GridView) findViewById(R.id.PlayerInventory);
        ImageAdapter iaPlayer = new ImageAdapter(this);

        //fill player backpack
        for(int i=0; i< backpack.size() ;i++){
            iaPlayer.addItemId(backpack.get(i).getImgID());
        }
        gridPlayer.setAdapter(iaPlayer);
    }
}
