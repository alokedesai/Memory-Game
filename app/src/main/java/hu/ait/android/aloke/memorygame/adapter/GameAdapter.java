package hu.ait.android.aloke.memorygame.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import hu.ait.android.aloke.memorygame.R;
import hu.ait.android.aloke.memorygame.model.GameItem;

/**
 * Created by Aloke on 4/6/15.
 */
public class GameAdapter extends BaseAdapter {
    private ArrayList<GameItem> images = new ArrayList<>();
    private Integer lastGuess;
    private Handler handler = new Handler();
    private boolean canClick = true;

    private Context ctx;

    private ArrayList<Pair<Integer, GameItem.SquareType>> imageOptions = new ArrayList<>(Arrays.asList(
            new Pair<>(R.drawable.apple, GameItem.SquareType.APPLE),
            new Pair<>(R.drawable.banana, GameItem.SquareType.BANANA),
            new Pair<>(R.drawable.cherry, GameItem.SquareType.CHERRY),
            new Pair<>(R.drawable.watermelon, GameItem.SquareType.WATERMELON)
//            new Pair<>(R.drawable.grape, GameItem.SquareType.GRAPE),
//            new Pair<>(R.drawable.orange, GameItem.SquareType.ORANGE),
//            new Pair<>(R.drawable.strawberry, GameItem.SquareType.STRAWBERRY),
//            new Pair<>(R.drawable.lemon, GameItem.SquareType.LEMON)
    ));

    public GameAdapter(Context ctx, int boardSize) {
        this.ctx = ctx;

        // populate images
        for (int i = 0; i < boardSize; i++) {
            images.add(new GameItem(imageOptions.get(i).first, imageOptions.get(i).second));
            images.add(new GameItem(imageOptions.get(i).first, imageOptions.get(i).second));
        }

        Collections.shuffle(images);
    }

    public GameAdapter(Context ctx) {
        this(ctx, 4);
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

    //TODO: clean up this method, extract into other methods
    public void setChosen(final int position, boolean chosen) {
        if (canClick) {
            if (lastGuess == null) {
                images.get(position).setChosen(chosen);
                lastGuess = position;
            } else {

                // only mark them as chosen if they were the same
                // otherwise mark both of them as chosen: false
                int currentValue = images.get(position).getSquareType().getValue();
                int lastValue = images.get(lastGuess).getSquareType().getValue();

                images.get(position).setChosen(true);
                if (currentValue != lastValue) {
                    hideImagesAfterDelay(position);
                }
                lastGuess = null;
            }
        }
    }

    private void hideImagesAfterDelay(final int position) {
        final int tempGuess = lastGuess;
        canClick = false;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // incorrect guess
                images.get(tempGuess).setChosen(false);
                images.get(position).setChosen(false);
                notifyDataSetChanged();

                canClick = true;
            }
        }, 1000);
    }


}
