package rsi.nameless;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.util.ArrayList;

/**
 * Created by Rick Koenders on 1-6-2016.
 */
public class RowAdapter extends BaseAdapter {
    private ArrayList<Integer> mThumbIds;
    private Context mContext;

    /**
     * Constructor
     */
    public RowAdapter(Context c) {
        mContext = c;
        mThumbIds = new ArrayList<>();
    }

    /**
     * @return the size of the ArrayList mThumbsIds
     */
    public int getCount() {
        return mThumbIds.size();
    }

    /**
     * The item at the given index in the ArrayList mThumbIds
     */
    public Object getItem(int position) {
        return mThumbIds.get(position);
    }

    /**
     * @param position The position which you are looking at
     * @return The image
     */
    public long getItemId(int position) {
        if(mThumbIds.get(position) == R.drawable.item1)
            return 1;
        else if(mThumbIds.get(position) == R.drawable.item2)
            return 2;
        else if(mThumbIds.get(position) == R.drawable.item3)
            return 3;
        else if(mThumbIds.get(position) == R.drawable.item4)
            return 4;
        else
            return 0;
    }


    /**
     * Create a new ImageView for each item referenced by the Adapter
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds.get(position));
        return imageView;
    }

}
