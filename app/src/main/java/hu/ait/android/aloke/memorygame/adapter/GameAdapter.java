package hu.ait.android.aloke.memorygame.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import hu.ait.android.aloke.memorygame.GameActivity;
import hu.ait.android.aloke.memorygame.R;
import hu.ait.android.aloke.memorygame.fragment.GameFragment;
import hu.ait.android.aloke.memorygame.model.GameItem;
import hu.ait.android.aloke.memorygame.model.Score;

/**
 * Created by Aloke on 4/6/15.
 */
public class GameAdapter extends BaseAdapter {
    private ArrayList<GameItem> images = new ArrayList<>();
    private Integer lastGuess;
    private Handler handler = new Handler();
    private boolean canClick = true;
    private int numPiecesLeft;
    private int numPieces;
    private boolean gameStarted = false;
    private Context ctx;

    private ArrayList<Pair<Integer, GameItem.SquareType>> imageOptions = new ArrayList<>(Arrays.asList(
            new Pair<>(R.drawable.apple, GameItem.SquareType.APPLE),
            new Pair<>(R.drawable.banana, GameItem.SquareType.BANANA),
            new Pair<>(R.drawable.cherry, GameItem.SquareType.CHERRY),
            new Pair<>(R.drawable.watermelon, GameItem.SquareType.WATERMELON),
            new Pair<>(R.drawable.orange, GameItem.SquareType.ORANGE),
            new Pair<>(R.drawable.grape, GameItem.SquareType.GRAPE),
            new Pair<>(R.drawable.strawberry, GameItem.SquareType.STRAWBERRY),
            new Pair<>(R.drawable.lemon, GameItem.SquareType.LEMON),
            new Pair<>(R.drawable.kiwi, GameItem.SquareType.KIWI),
            new Pair<>(R.drawable.lime, GameItem.SquareType.LIME),
            new Pair<>(R.drawable.raspberry, GameItem.SquareType.RASPBERRY),
            new Pair<>(R.drawable.mango, GameItem.SquareType.MANGO),
            new Pair<>(R.drawable.coconut, GameItem.SquareType.COCONUT),
            new Pair<>(R.drawable.grapefruit, GameItem.SquareType.GRAPEFRUIT),
            new Pair<>(R.drawable.peach, GameItem.SquareType.PEACH),
            new Pair<>(R.drawable.pear, GameItem.SquareType.PEAR),
            new Pair<>(R.drawable.lemon, GameItem.SquareType.LEMON),
            new Pair<>(R.drawable.pineapple, GameItem.SquareType.PINEAPPLE)
    ));

    public GameAdapter(Context ctx, int numPieces) {
        this.ctx = ctx;
        this.numPieces = numPieces;
        numPiecesLeft = numPieces;

        // populate images
        for (int i = 0; i < numPieces; i++) {
            images.add(new GameItem(imageOptions.get(i).first, imageOptions.get(i).second));
            images.add(new GameItem(imageOptions.get(i).first, imageOptions.get(i).second));
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

    //TODO: clean up this method, extract into other methods
    public void setChosen(final int position, boolean chosen) {
        // start clock if this is the first time clicking the board
        if (!gameStarted) {
            gameStarted = true;
            ((GameActivity) ctx).startChronometer();
        }
        if (canClick && !images.get(position).isChosen()) {
            if (lastGuess == null) {
                // first guess
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
                } else {
                    // correct guess
                    numPiecesLeft--;
                    checkForGameOver();
                }

                lastGuess = null;
            }
        }
    }

    private void checkForGameOver() {
        System.out.println("num pieces left " + numPiecesLeft);
        if (numPiecesLeft == 0) {
            gameStarted = false;
            String result = ((GameActivity) ctx).stopChronometer();

            // create a new score
            createScore(result);

            ((GameActivity) ctx).launchGameOverDialog(result);
        }
    }

    private void createScore(String readableTime) {
        long ellapsedTime = SystemClock.elapsedRealtime() - ((GameActivity) ctx).getChronometerBase();
        String date = getDateAsString();

        // get difficuty
        Score.Difficulty difficulty;
        switch (numPieces) {
            case (GameActivity.EASY_GAME):
                difficulty = Score.Difficulty.EASY;
                break;
            case (GameActivity.MEDIUM_GAME):
                difficulty = Score.Difficulty.MEDIUM;
                break;
            case (GameActivity.HARD_GAME):
                difficulty = Score.Difficulty.HARD;
            default:
                difficulty = Score.Difficulty.EASY;
        }

        Score score = new Score(readableTime, ellapsedTime, date, difficulty);

        score.save();
    }

    private String getDateAsString() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
        Date now = new Date();
        return sdfDate.format(now);
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

    public void resetGame() {
        // reset variables
        numPiecesLeft = numPieces;
        lastGuess = null;
        gameStarted = false;

        //reset chronometer
        ((GameActivity) ctx).resetChronometer();

        // mark all the images as not chosen
        for (GameItem item : images) {
            item.setChosen(false);
        }

        // reshuffle the items
        Collections.shuffle(images);
        notifyDataSetChanged();
    }
}
