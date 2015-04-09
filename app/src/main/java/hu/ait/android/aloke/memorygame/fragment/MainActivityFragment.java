package hu.ait.android.aloke.memorygame.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import hu.ait.android.aloke.memorygame.GameActivity;
import hu.ait.android.aloke.memorygame.MainActivity;
import hu.ait.android.aloke.memorygame.R;
import hu.ait.android.aloke.memorygame.ScoresActivity;
import hu.ait.android.aloke.memorygame.SettingsActivity;

/**
 * Created by Aloke on 4/6/15.
 */
public class MainActivityFragment extends Fragment {
    private TextView tvWelcome;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_activity_main, container, false);
        tvWelcome = (TextView) rootView.findViewById(R.id.tvWelcome);
        // set the name for the textview
        setName();

        Button btnSettings = (Button) rootView.findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                getActivity().startActivityForResult(intent, MainActivity.RESULT_SETTINGS);
            }
        });

        Button btnNewGame = (Button) rootView.findViewById(R.id.btnNewGame);
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DifficultyDialog difficultyDialog = new DifficultyDialog();
                difficultyDialog.show(getActivity().getSupportFragmentManager(), DifficultyDialog.TAG);
            }
        });

        Button btnScores = (Button) rootView.findViewById(R.id.btnScores);
        btnScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScoresActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void setName() {
        String name = getArguments().getString(SettingsActivity.SETTINGS_NAME, "");
        tvWelcome.setText(getString(R.string.welcome_text, name));
    }

    public void setName(String name) {
        tvWelcome.setText(getString(R.string.welcome_text, name));
    }
}
