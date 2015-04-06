package hu.ait.android.aloke.memorygame.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

import hu.ait.android.aloke.memorygame.R;
import hu.ait.android.aloke.memorygame.model.GameItem;

/**
 * Created by Aloke on 4/6/15.
 */
public class GameAdapter extends BaseAdapter {
    private ArrayList<GameItem> images = new ArrayList<>();
    private Integer lastGuess;


    private Context ctx;

    public GameAdapter(Context ctx) {
        this.ctx = ctx;

        // populate images
        for (int i = 0; i < 4; i++) {
            images.add(new GameItem(R.drawable.banana, GameItem.SquareType.BANANA));
            images.add(new GameItem(R.drawable.apple, GameItem.SquareType.APPLE));
        }

        Collections.shuffle(images);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(ctx);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(images.get(position).getImageResource());

        return imageView;
    }

    public void setChosen(int position, boolean chosen) {
        if (lastGuess == null) {
            images.get(position).setChosen(chosen);
            lastGuess = position;
        } else {
            System.out.println("the last guess is " + lastGuess);

            // only mark them as chosen if they were the same
            // otherwise mark both of them as chosen: false
            int currentValue = images.get(position).getSquareType().getValue();
            int lastValue = images.get(lastGuess).getSquareType().getValue();

            if (currentValue == lastValue) {
                // correct guess
                images.get(position).setChosen(true);
            } else {
                // incorrect guess
                images.get(lastGuess).setChosen(false);
            }
            lastGuess = null;
        }

    }
}
