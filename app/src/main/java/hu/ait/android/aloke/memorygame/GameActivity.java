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

    //TODO: is there a better way of doing this?
    public void startChronometer() {
        gameFragment.startChronometer();
    }

    public String stopChronometer() {
        return gameFragment.stopChronometer();
    }

    public void resetChronometer() {
        gameFragment.resetChronomter();
    }

    public long getChronometerBase() {
        return gameFragment.getChronometerBase();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startScoresActivity() {
        Intent intent = new Intent(this, ScoresActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameFragment.stopChronometer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameFragment.restartChronometer();
    }
}
