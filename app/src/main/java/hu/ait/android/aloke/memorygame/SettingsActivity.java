package hu.ait.android.aloke.memorygame;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class SettingsActivity extends ActionBarActivity {
    public static final String SETTINGS_PREF = "SETTINGS_PREFERENCES";
    public static final String SETTINGS_AGE = "SETTINGS_AGE";
    public static final String SETTINGS_NAME = "SETTINGS_NAME";
    public static final String SETTINGS_GENDER = "SETTING_GENDER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Spinner spinner = (Spinner) findViewById(R.id.spnGender);
        setAdapterForSpinner(spinner);
    }

    private void setAdapterForSpinner(final Spinner spinner) {
        // set the different gender options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etAge = (EditText) findViewById(R.id.etAge);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(etName.getText().toString())) {
                    etName.setError("You must enter a name");
                } else if ("".equals(etAge.getText().toString())) {
                    etAge.setError("You must enter an age");
                } else {
                    // save to shared preferences
                    saveSettings(etName, spinner, etAge);
                    finish();
                }
            }
        });

    }

    private void saveSettings(EditText etName, Spinner spinner, EditText etAge) {
        SharedPreferences.Editor editor = getSharedPreferences(SETTINGS_PREF, MODE_PRIVATE).edit();

        editor.putString(SETTINGS_NAME, etName.getText().toString());
        editor.putString(SETTINGS_GENDER, spinner.getSelectedItem().toString());
        editor.putString(SETTINGS_AGE, etAge.getText().toString());

        editor.apply();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
}
