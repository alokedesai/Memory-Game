package hu.ait.android.aloke.memorygame.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hu.ait.android.aloke.memorygame.GameActivity;
import hu.ait.android.aloke.memorygame.fragment.GameFragment;
import hu.ait.android.aloke.memorygame.fragment.ScoreFragment;
import hu.ait.android.aloke.memorygame.model.Score;

/**
 * Created by Aloke on 4/9/15.
 */
public class ScoreFragmentAdapter extends FragmentPagerAdapter {

    public ScoreFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();
        switch (position) {
            case 0:
                args.putString(ScoreFragment.SEARCH_QUERY, Score.Difficulty.EASY.toString());
                break;
            case 1:
                args.putString(ScoreFragment.SEARCH_QUERY, Score.Difficulty.MEDIUM.toString());
                break;
            case 2:
                args.putString(ScoreFragment.SEARCH_QUERY, Score.Difficulty.HARD.toString());
                break;
            default:
                args.putString(ScoreFragment.SEARCH_QUERY, Score.Difficulty.EASY.toString());
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                //TODO: how do I extract string resource here?
                return "Easy";
            case 1:
                return "Medium";
            case 2:
                return "Hard";
            default:
                return "Easy";
        }
    }
}
