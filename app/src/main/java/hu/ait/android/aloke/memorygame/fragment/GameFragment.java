package hu.ait.android.aloke.memorygame.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;

import com.daimajia.numberprogressbar.NumberProgressBar;

import hu.ait.android.aloke.memorygame.GameActivity;
import hu.ait.android.aloke.memorygame.R;
import hu.ait.android.aloke.memorygame.adapter.GameAdapter;



/**
 * Created by Aloke on 4/6/15.
 */
public class GameFragment extends Fragment {
    private GridView gridView;
    private Chronometer chronometer;
    private GameAdapter adapter;
    private NumberProgressBar numberProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.game_fragment, container, false);

        gridView = (GridView) rootView.findViewById(R.id.gridView);
        Button btnReset = (Button) rootView.findViewById(R.id.btnReset);
        chronometer = (Chronometer) rootView.findViewById(R.id.chronometer);
        numberProgressBar = (NumberProgressBar) rootView.findViewById(R.id.numberProgressBar);

        Bundle bundle = getArguments();
        int numPieces = bundle.getInt(GameActivity.BOARD_SIZE, GameActivity.EASY_GAME);
        setDifficulty(numPieces);

        adapter = new GameAdapter(getActivity(), numPieces, this);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setChosen(position, true);
                adapter.notifyDataSetChanged();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.resetGame();
            }
        });

        return rootView;
    }

    private void setDifficulty(int numPieces) {
        switch (numPieces) {
            case GameActivity.EASY_GAME:
                // 3x2
                gridView.setNumColumns(3);
                break;
            case GameActivity.MEDIUM_GAME:
                gridView.setNumColumns(4);
                break;
            case GameActivity.HARD_GAME:
                gridView.setNumColumns(5);
                break;
        }
    }

    public void updateProgressBar(int progress) {
        numberProgressBar.setProgress(progress);
    }

    public void resetGame() {
        adapter.resetGame();
    }

    public void startChronometer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    public String stopChronometer() {
        chronometer.stop();
        return chronometer.getText().toString();
    }

    public void resetChronomter() {
        chronometer.stop();

        // reset base here just to clear the chronometer
        chronometer.setBase(SystemClock.elapsedRealtime());
    }

    public long getChronometerBase() {
        return chronometer.getBase();
    }

    public void restartChronometer() {
        chronometer.start();
    }
}
