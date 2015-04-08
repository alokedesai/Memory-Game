package hu.ait.android.aloke.memorygame.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.ait.android.aloke.memorygame.R;
import hu.ait.android.aloke.memorygame.adapter.ScoreAdapter;
import hu.ait.android.aloke.memorygame.model.Score;

/**
 * Created by Aloke on 4/8/15.
 */
public class ScoreFragment extends android.support.v4.app.ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        List<Score> scores = Score.listAll(Score.class);
        setListAdapter(new ScoreAdapter(getActivity(), scores));
        return view;
    }
}
