package rsi.nameless;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {
    private AnimationDrawable skeletonAnimation;
    private TextView tV;
    private ImageView sklImageView;

    /**
     * When this activity is created it makes the textview invisible and starts an animation.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        sklImageView = (ImageView) findViewById(R.id.skl_dieing);
        tV = (TextView) findViewById(R.id.end_game_TV);

        tV.setVisibility(View.GONE);

        sklImageView.setBackgroundResource(R.drawable.skl_die_anim);
        skeletonAnimation = (AnimationDrawable) sklImageView.getBackground();
        skeletonAnimation.start();

    }

    /**
     * When the screen is tapped, the animation stops and the text shows.
     * Or if the the text is already visible it hides the text and shows the animation again.
     * @param event
     * @return
     */
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if(tV.getVisibility() == View.VISIBLE) {
                tV.setVisibility(View.GONE);
                sklImageView.setVisibility(View.VISIBLE);
                skeletonAnimation.start();
            }
            else {
                tV.setVisibility(View.VISIBLE);
                sklImageView.setVisibility(View.GONE);
            }
            return true;
        }
        return super.onTouchEvent(event);
    }
}
