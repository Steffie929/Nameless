package rsi.nameless;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BackpackActivity extends AppCompatActivity {
    private Character player;
    private ArrayList<Item> backpack;
    private final int maxBackpackSize = 30;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpack);
        player = (Character) getIntent().getSerializableExtra("CURRENT_PLAYER");
        backpack = player.getBackpack();
        gridLayout = (GridLayout) findViewById(R.id.Backpack_Grid);
        fillBackpack();
    }

    private void fillBackpack() {
        int nrOfItems = backpack.size();
        if (nrOfItems > 0) {
            for (int i = 0; i < nrOfItems; i++) {
                ImageView image = getImageView(i);
                if (image != null) {
                    Drawable draw = ResourcesCompat.getDrawable(getResources(), backpack.get(i).getImgID(), null);
                    image.setImageDrawable(draw);
                }
                TextView text = getTextView(i);
                if (text != null) {
                    text.setText(backpack.get(i).getName());
                }
            }
            for (int i = nrOfItems; i < 30; i++) {
                ImageView image = getImageView(i);
                if (image != null) {
                    image.setImageDrawable(null);
                }
                TextView text = getTextView(i);
                if (text != null) {
                    text.setText("There is no item here!");
                }
            }
        }
    }

    private TextView getTextView(int i) {
        switch(i) {
            case 0:
                return (TextView) findViewById(R.id.backpackTV1);
            case 1:
                return (TextView) findViewById(R.id.backpackTV2);
            case 2:
                return (TextView) findViewById(R.id.backpackTV3);
            case 3:
                return (TextView) findViewById(R.id.backpackTV4);
            case 4:
                return (TextView) findViewById(R.id.backpackTV5);
            case 5:
                return (TextView) findViewById(R.id.backpackTV6);
            case 6:
                return (TextView) findViewById(R.id.backpackTV7);
            case 7:
                return (TextView) findViewById(R.id.backpackTV8);
            case 8:
                return (TextView) findViewById(R.id.backpackTV9);
            case 9:
                return (TextView) findViewById(R.id.backpackTV10);
            case 10:
                return (TextView) findViewById(R.id.backpackTV11);
            case 11:
                return (TextView) findViewById(R.id.backpackTV12);
            case 12:
                return (TextView) findViewById(R.id.backpackTV13);
            case 13:
                return (TextView) findViewById(R.id.backpackTV14);
            case 14:
                return (TextView) findViewById(R.id.backpackTV15);
            case 15:
                return (TextView) findViewById(R.id.backpackTV16);
            case 16:
                return (TextView) findViewById(R.id.backpackTV17);
            case 17:
                return (TextView) findViewById(R.id.backpackTV18);
            case 18:
                return (TextView) findViewById(R.id.backpackTV19);
            case 19:
                return (TextView) findViewById(R.id.backpackTV20);
            case 20:
                return (TextView) findViewById(R.id.backpackTV21);
            case 21:
                return (TextView) findViewById(R.id.backpackTV22);
            case 22:
                return (TextView) findViewById(R.id.backpackTV23);
            case 23:
                return (TextView) findViewById(R.id.backpackTV24);
            case 24:
                return (TextView) findViewById(R.id.backpackTV25);
            case 25:
                return (TextView) findViewById(R.id.backpackTV26);
            case 26:
                return (TextView) findViewById(R.id.backpackTV27);
            case 27:
                return (TextView) findViewById(R.id.backpackTV28);
            case 28:
                return (TextView) findViewById(R.id.backpackTV29);
            case 29:
                return (TextView) findViewById(R.id.backpackTV30);
        }
        return null;
    }

    private ImageView getImageView(int i) {
        switch(i) {
            case 0:
                return (ImageView) findViewById(R.id.backpackIcon1);
            case 1:
                return (ImageView) findViewById(R.id.backpackIcon2);
            case 2:
                return (ImageView) findViewById(R.id.backpackIcon3);
            case 3:
                return (ImageView) findViewById(R.id.backpackIcon4);
            case 4:
                return (ImageView) findViewById(R.id.backpackIcon5);
            case 5:
                return (ImageView) findViewById(R.id.backpackIcon6);
            case 6:
                return (ImageView) findViewById(R.id.backpackIcon7);
            case 7:
                return (ImageView) findViewById(R.id.backpackIcon8);
            case 8:
                return (ImageView) findViewById(R.id.backpackIcon9);
            case 9:
                return (ImageView) findViewById(R.id.backpackIcon10);
            case 10:
                return (ImageView) findViewById(R.id.backpackIcon11);
            case 11:
                return (ImageView) findViewById(R.id.backpackIcon12);
            case 12:
                return (ImageView) findViewById(R.id.backpackIcon13);
            case 13:
                return (ImageView) findViewById(R.id.backpackIcon14);
            case 14:
                return (ImageView) findViewById(R.id.backpackIcon15);
            case 15:
                return (ImageView) findViewById(R.id.backpackIcon16);
            case 16:
                return (ImageView) findViewById(R.id.backpackIcon17);
            case 17:
                return (ImageView) findViewById(R.id.backpackIcon18);
            case 18:
                return (ImageView) findViewById(R.id.backpackIcon19);
            case 19:
                return (ImageView) findViewById(R.id.backpackIcon20);
            case 20:
                return (ImageView) findViewById(R.id.backpackIcon21);
            case 21:
                return (ImageView) findViewById(R.id.backpackIcon22);
            case 22:
                return (ImageView) findViewById(R.id.backpackIcon23);
            case 23:
                return (ImageView) findViewById(R.id.backpackIcon24);
            case 24:
                return (ImageView) findViewById(R.id.backpackIcon25);
            case 25:
                return (ImageView) findViewById(R.id.backpackIcon26);
            case 26:
                return (ImageView) findViewById(R.id.backpackIcon27);
            case 27:
                return (ImageView) findViewById(R.id.backpackIcon28);
            case 28:
                return (ImageView) findViewById(R.id.backpackIcon29);
            case 29:
                return (ImageView) findViewById(R.id.backpackIcon30);
        }
        return null;
    }

    /**
     * The method called by activity_backpack.xml when Back_Button is clicked
     * Go back to the previous activity by closing this one
     */
    public void backpackBackButton (View v) {
        finish();
    }

    /**
     * The method called by activity_backpack.xml when Character_Button is clicked
     * Go to the "Character" screen
     */
    public void backpackCharacterButton (View v) {
        //TODO Character screen
    }
}
