package hu.ait.android.aloke.memorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.BassBoost;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

import hu.ait.android.aloke.memorygame.fragment.DifficultyDialog;
import hu.ait.android.aloke.memorygame.fragment.MainActivityFragment;


public class MainActivity extends ActionBarActivity implements DifficultyDialog.DifficultyDialogFragmentInterface {
    public static final int RESULT_SETTINGS = 201;
    private MainActivityFragment mainActivityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //redirect to settings if this is the first time the user has used app
        if (isFirstTimeUser()) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent, RESULT_SETTINGS);
        }
        setContentView(R.layout.activity_main);

        // start the main activity fragment
        startMainActivityFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainActivityFragment.setName(getName());
    }

    private void startMainActivityFragment() {
        mainActivityFragment = new MainActivityFragment();
        Bundle args = new Bundle();
        args.putString(SettingsActivity.SETTINGS_NAME, getName());
        mainActivityFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivityFrame, mainActivityFragment).commit();
    }

    private boolean isFirstTimeUser() {
        return getName() == null;
    }

    private String getName() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        return sp.getString(SettingsActivity.SETTINGS_NAME, null);
    }

    @Override
    public void onDifficultyDialogFragmentResult(String item, int numPieces) {
        // get the difficulty and start the game activity
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(GameActivity.BOARD_SIZE, numPieces);
        startActivity(intent);
    }
}
