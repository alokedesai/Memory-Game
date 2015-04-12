package hu.ait.android.aloke.memorygame.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import hu.ait.android.aloke.memorygame.R;

/**
 * Created by Aloke on 4/12/15.
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
