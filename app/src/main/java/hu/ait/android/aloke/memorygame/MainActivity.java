package hu.ait.android.aloke.memorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.BassBoost;
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
        SharedPreferences sp = getSharedPreferences(SettingsActivity.SETTINGS_PREF, MODE_PRIVATE);
        String name = sp.getString(SettingsActivity.SETTINGS_NAME, null);
        System.out.println("the current name is " + name);
        return name;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //reupdate the text for the welcome message if the settings was updated
        if (requestCode == RESULT_SETTINGS && resultCode == RESULT_OK) {
            mainActivityFragment.setName(getName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onDifficultyDialogFragmentResult(String item, int numPieces) {
        // ignore the dificulty for now
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(GameActivity.BOARD_SIZE, numPieces);
        startActivity(intent);
    }
}
