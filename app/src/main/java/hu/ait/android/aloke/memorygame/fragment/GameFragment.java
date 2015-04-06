package hu.ait.android.aloke.memorygame.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import hu.ait.android.aloke.memorygame.R;
import hu.ait.android.aloke.memorygame.adapter.GameAdapter;

/**
 * Created by Aloke on 4/6/15.
 */
public class GameFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.game_fragment, container);

        GridView gridView = (GridView) rootView.findViewById(R.id.gridView);
        gridView.setAdapter(new GameAdapter());

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT);
            }
        });

        return rootView;
    }
}
