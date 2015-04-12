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
    // the fragment from the activity. Passed as a parameter so we can
    // access crucial methods to toggle the UI
    private GameFragment fragment;

    // the actual items in our board
    private ArrayList<GameItem> images = new ArrayList<>();

    /*
    variables for whether this is first or second guess. If this is the second guess,
    lastGuess is true and oldAnimator is not null
     */
    private Integer lastGuess;
    private ViewRevealAnimator oldAnimator;

    // used for the pause delay
    private Handler handler = new Handler();

    // boolean to toggle whether the user can click.
    // the user cannot click during one second pause after an incorrect guess
    private boolean canClick = true;

    //used to for the progress bar and to determine when there's a game over
    private int numPiecesLeft;
    private int numPieces;

    private boolean gameStarted = false;

    private Context ctx;

    public GameAdapter(Context ctx, int numPieces, GameFragment fragment) {
        this.ctx = ctx;
        this.numPieces = numPieces;
        this.fragment = fragment;

        numPiecesLeft = numPieces;

        GameItem.SquareType[] values = GameItem.SquareType.values();
        // populate images
        for (int i = 0; i < numPieces; i++) {
            GameItem.SquareType type = values[i];
            images.add(new GameItem(type));
            images.add(new GameItem(type));
        }

        // shuffle the images to get a random location of images every game
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
            setViewHolder(ivMain, ivActualImage, animator, holder);

            /*
                set the on click listener for the animator. This is where essentially
                all the logic for the game is
             */
            setAnimatorOnClickListener(position, animator);

            v.setTag(holder);
        }

        GameItem currentItem = images.get(position);

        if (currentItem != null) {
            ViewHolder holder = (ViewHolder) v.getTag();
            holder.ivActualImage.setImageResource(images.get(position).getImageResource());
        }

        return v;
    }

    private void setAnimatorOnClickListener(final int position, final ViewRevealAnimator animator) {
        animator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameStarted) {
                    startGame();
                }

                if (canClick && !images.get(position).isChosen()) {
                    if (lastGuess == null) {
                        // this was the first guess
                        showImage(position, animator);
                        oldAnimator = animator;

                        lastGuess = position;
                    } else {
                        int currentValue = images.get(position).getSquareType().getValue();
                        int lastValue = images.get(lastGuess).getSquareType().getValue();

                        showImage(position, animator);

                        if (currentValue != lastValue) {
                            // incorrect guess
                            hideImagesAfterDelay(position, animator);
                        } else {
                            // correct guess
                            correctGuess();
                        }

                        lastGuess = null;
                    }
                }
            }
        });
    }

    // set the fields for the viewholder
    private void setViewHolder(ImageView ivMain, ImageView ivActualImage, ViewRevealAnimator animator, ViewHolder holder) {
        holder.ivMain = ivMain;
        holder.ivActualImage = ivActualImage;
        holder.animator = animator;
    }

    private void correctGuess() {
        numPiecesLeft--;

        // update the progress bar
        double progress = ((double) numPiecesLeft) / numPieces;
        fragment.updateProgressBar((int) ((1 - progress) * 100));
        checkForGameOver();
    }

    private void startGame() {
        gameStarted = true;
        fragment.startChronometer();
    }

    private void checkForGameOver() {
        if (numPiecesLeft == 0) {
            String result = fragment.stopChronometer();

            // create a new score
            createScore(result);

            ((GameActivity) ctx).launchGameOverDialog(result);
        }
    }

    private void showImage(int position, ViewRevealAnimator animator) {
        animator.showNext();
        images.get(position).setChosen(true);
    }

    // create the proper fields for a score object and then save using Sugar ORM
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
