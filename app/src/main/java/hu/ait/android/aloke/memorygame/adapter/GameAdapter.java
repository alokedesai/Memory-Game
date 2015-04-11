package hu.ait.android.aloke.memorygame.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
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
import it.sephiroth.android.library.viewrevealanimator.ViewRevealAnimator;

/**
 * Created by Aloke on 4/6/15.
 */
public class GameAdapter extends BaseAdapter {
    private GameFragment fragment;

    private ArrayList<GameItem> images = new ArrayList<>();
    private Integer lastGuess;

    private ViewRevealAnimator oldAnimator;

    // used for the pause delay
    private Handler handler = new Handler();

    // boolean to toggle whether the user can click.
    // the user cannot click during one second pause after an incorrect guess
    private boolean canClick = true;


    private int numPiecesLeft;
    private int numPieces;
    private boolean gameStarted = false;

    private Context ctx;

    public GameAdapter(Context ctx, int numPieces, GameFragment fragment) {
        this.ctx = ctx;
        this.numPieces = numPieces;
        this.fragment = fragment;

        numPiecesLeft = numPieces;

        GameItem.SquareType.values();
        // populate images
        for (int i = 0; i < numPieces; i++) {
            GameItem.SquareType type = GameItem.SquareType.values()[i];
            images.add(new GameItem(type));
            images.add(new GameItem(type));
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(ctx).inflate(R.layout.grid_cell_layout, null);
            ImageView ivMain = (ImageView) v.findViewById(R.id.ivMain);
            ImageView ivActualImage = (ImageView) v.findViewById(R.id.ivActualImage);
            final ViewRevealAnimator animator = (ViewRevealAnimator) v.findViewById(R.id.animator);

            //set View holder
            ViewHolder holder = new ViewHolder();
            holder.ivMain = ivMain;
            holder.ivActualImage = ivActualImage;
            holder.animator = animator;

            animator.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!gameStarted) {
                        gameStarted = true;
                        fragment.startChronometer();
                    }

                    if (canClick && !images.get(position).isChosen()) {
                        if (lastGuess == null) {
                            // first guess
                            images.get(position).setChosen(true);
                            oldAnimator = animator;

                            animator.showNext();
                            lastGuess = position;
                        } else {
                            int currentValue = images.get(position).getSquareType().getValue();
                            int lastValue = images.get(lastGuess).getSquareType().getValue();

                            animator.showNext();
                            images.get(position).setChosen(true);

                            if (currentValue != lastValue) {
                                // incorrect guess
                                hideImagesAfterDelay(position, animator);
                            } else {
                                // correct guess
                                numPiecesLeft--;

                                // update the progress bar
                                double progress = ((double) numPiecesLeft) / numPieces;
                                fragment.updateProgressBar((int) ((1 - progress) * 100));
                                checkForGameOver();
                            }

                            lastGuess = null;
                        }
                    }
                }
            });
            v.setTag(holder);
        }

        GameItem currentItem = images.get(position);

        if (currentItem != null) {
            ViewHolder holder = (ViewHolder) v.getTag();
            holder.ivActualImage.setImageResource(images.get(position).getImageResource());
        }

        return v;
    }

    private void checkForGameOver() {
        if (numPiecesLeft == 0) {
            String result = fragment.stopChronometer();

            // create a new score
            createScore(result);

            ((GameActivity) ctx).launchGameOverDialog(result);
        }
    }

    private void createScore(String readableTime) {
        long ellapsedTime = SystemClock.elapsedRealtime() - fragment.getChronometerBase();
        String date = getDateAsString();

        // get difficulty
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
                break;
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

    private void hideImagesAfterDelay(final int position, final ViewRevealAnimator currentAnimator) {
        final int tempGuess = lastGuess;
        canClick = false;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // incorrect guess
                images.get(tempGuess).setChosen(false);
                images.get(position).setChosen(false);

                // make animation back to question marks
                currentAnimator.showPrevious();
                oldAnimator.showPrevious();

                notifyDataSetChanged();
                canClick = true;
            }
        }, 1000);
    }

    public static class ViewHolder {
        ViewRevealAnimator animator;
        public ImageView ivActualImage;
        public ImageView ivMain;
    }

}
