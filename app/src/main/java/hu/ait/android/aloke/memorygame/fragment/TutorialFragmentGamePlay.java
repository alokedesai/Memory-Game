package hu.ait.android.aloke.memorygame.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.ait.android.aloke.memorygame.R;

/**
 * Created by Aloke on 4/10/15.
 */
public class TutorialFragmentGamePlay extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tutorial_gameplay, container, false);
    }
}
