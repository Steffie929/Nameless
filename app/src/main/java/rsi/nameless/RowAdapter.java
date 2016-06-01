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

    public RowAdapter(Context c) {
        mContext = c;
        mThumbIds = new ArrayList<>();
    }


    public int getCount() {
        return mThumbIds.size();
    }

    public Object getItem(int position) {
        return mThumbIds.get(position);
    }

    /**
     * @param position Naar welke positie kijk je
     * @return wat voor plaatje is dit
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

    public void addItemId(int id){
        mThumbIds.add(id);
    }

    public void removeItem(int pos){
        mThumbIds.remove(pos);
    }

    public void clearItems(){
        mThumbIds = new ArrayList<>();
    }

    /**
     *  Deze functie veranderd plaatje uit mThumbIds
     * @param pos geeft aan waar je iets wilt veranderen
     * @param c geeft aan welk plaatje je wilt gebruiken
     */
    public void setImage(int pos, int c){
        switch(c) {
            case 1:
                mThumbIds.add(c, pos);
                break;
            case 2:
                mThumbIds.add(c, pos);
                break;
            case 3:
                mThumbIds.add(c, pos);
                break;
            case 4:
                mThumbIds.add(c, pos);
        }
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
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
