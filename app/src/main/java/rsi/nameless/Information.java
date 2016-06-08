package rsi.nameless;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Information extends AppCompatActivity {
    private ImageView image;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        text = (TextView) findViewById(R.id.explanation);
        image = (ImageView) findViewById(R.id.topImage);
        int imgID = getIntent().getIntExtra("IMAGE_ID", 0);
        String information = getIntent().getStringExtra("INFORMATION_TEXT");
        display(imgID, information);
    }

    private void display(int imgID, String information) {
        Drawable draw = ResourcesCompat.getDrawable(getResources(), imgID, null);
        image.setImageDrawable(draw);
        text.setText(information);
    }

    public void backToTutorial(View v) {
        finish();
    }
}
