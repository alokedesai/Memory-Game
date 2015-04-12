package hu.ait.android.aloke.memorygame;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import hu.ait.android.aloke.memorygame.fragment.GameFragment;
import hu.ait.android.aloke.memorygame.fragment.GameOverDialog;


public class GameActivity extends ActionBarActivity {
    public static String BOARD_SIZE = "BOARD_SIZE";

    // the number of pairs a game requires
    public static final int EASY_GAME = 3;
    public static final int MEDIUM_GAME = 8;
    public static final int HARD_GAME = 10;

    private GameFragment gameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameFragment = new GameFragment();
        gameFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, gameFragment).commit();
    }

    public void launchGameOverDialog(String time) {
        GameOverDialog gameOverDialog = new GameOverDialog();

        Bundle args = new Bundle();
        args.putString(GameOverDialog.TIME, time);

        gameOverDialog.setArguments(args);
        gameOverDialog.show(getSupportFragmentManager(), GameOverDialog.TAG);
    }

    public void resetGame() {
        gameFragment.resetGame();
    }

    public void startScoresActivity() {
        Intent intent = new Intent(this, ScoresActivity.class);
        startActivity(intent);
    }
}
