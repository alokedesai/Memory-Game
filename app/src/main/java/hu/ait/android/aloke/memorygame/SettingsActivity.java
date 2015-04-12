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
import android.widget.TextView;


public class SettingsActivity extends ActionBarActivity {
    public static final String SETTINGS_PREF = "SETTINGS_PREFERENCES";
    public static final String SETTINGS_AGE = "SETTINGS_AGE";
    public static final String SETTINGS_NAME = "SETTINGS_NAME";
    public static final String SETTINGS_GENDER = "SETTING_GENDER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Spinner spinner = (Spinner) findViewById(R.id.spnGender);
        setAdapterForSpinner(spinner);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etAge = (EditText) findViewById(R.id.etAge);

        SharedPreferences sp = getSharedPreferences(SETTINGS_PREF, MODE_PRIVATE);
        String name = sp.getString(SETTINGS_NAME, null);

        if (name != null) {
            // set the form to its old views
            setTextViews(etName, etAge);
        } else {
            showWelcomeView();
        }

        Button btnSave = (Button) findViewById(R.id.btnSave);
        setBtnSaveOnClickListener(spinner, etName, etAge, btnSave);

    }

    private void setBtnSaveOnClickListener(final Spinner spinner, final EditText etName, final EditText etAge, Button btnSave) {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(etName.getText().toString())) {
                    etName.setError(getString(R.string.you_must_enter_a_name_error_text));
                } else if ("".equals(etAge.getText().toString())) {
                    etAge.setError(getString(R.string.you_must_enter_an_age_error_text));
                } else {
                    // save to shared preferences
                    saveSettings(etName, spinner, etAge);

                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }

    private void setTextViews(EditText etName, EditText etAge) {
        SharedPreferences sp = getSharedPreferences(SETTINGS_PREF, MODE_PRIVATE);
        String name = sp.getString(SETTINGS_NAME, null);
        String age = sp.getString(SETTINGS_AGE, "");

        etName.setText(name);
        etAge.setText(age);
    }

    private void showWelcomeView() {
        // show the welcome view
        TextView tvWelcomeMessageSettings = (TextView) findViewById(R.id.tvWelcomeMessageSettings);
        tvWelcomeMessageSettings.setVisibility(View.VISIBLE);
    }

    private void setAdapterForSpinner(final Spinner spinner) {
        // set the different gender options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void saveSettings(EditText etName, Spinner spinner, EditText etAge) {
        SharedPreferences.Editor editor = getSharedPreferences(SETTINGS_PREF, MODE_PRIVATE).edit();

        editor.putString(SETTINGS_NAME, etName.getText().toString());
        editor.putString(SETTINGS_GENDER, spinner.getSelectedItem().toString());
        editor.putString(SETTINGS_AGE, etAge.getText().toString());

        editor.apply();
    }
}
