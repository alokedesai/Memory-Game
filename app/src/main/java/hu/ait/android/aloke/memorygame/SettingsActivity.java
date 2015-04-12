package hu.ait.android.aloke.memorygame;

import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import hu.ait.android.aloke.memorygame.fragment.SettingsFragment;


public class SettingsActivity extends PreferenceActivity {
    public static final String SETTINGS_AGE = "SETTINGS_AGE";
    public static final String SETTINGS_NAME = "SETTINGS_NAME";
    public static final String SETTINGS_GENDER = "SETTING_GENDER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new SettingsFragment()).commit();

    }
}
