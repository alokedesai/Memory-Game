package hu.ait.android.aloke.memorygame.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hu.ait.android.aloke.memorygame.R;
import hu.ait.android.aloke.memorygame.SettingsActivity;

/**
 * Created by Aloke on 4/6/15.
 */
public class MainActivityFragment extends Fragment {
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_activity_main, container);

        Button btnSettings = (Button) rootView.findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
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

        return rootView;
    }
}
