package hu.ait.android.aloke.memorygame.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.ait.android.aloke.memorygame.GameActivity;
import hu.ait.android.aloke.memorygame.R;
import hu.ait.android.aloke.memorygame.adapter.ScoreAdapter;
import hu.ait.android.aloke.memorygame.model.Score;

/**
 * Created by Aloke on 4/8/15.
 */
public class ScoreFragment extends android.support.v4.app.ListFragment {
    public static final String SEARCH_QUERY = "SEARCH_QUERY";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        String searchQuery = getArguments().getString(SEARCH_QUERY, Score.Difficulty.EASY.toString());

        // get all the current scores
        List<Score> scores = Score.find(Score.class, "difficulty = ?", new String[]{searchQuery},null, "num_milis ASC", "5");
        setListAdapter(new ScoreAdapter(getActivity(), scores));

        return view;
    }
}
