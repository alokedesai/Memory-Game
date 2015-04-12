package hu.ait.android.aloke.memorygame.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hu.ait.android.aloke.memorygame.GameActivity;
import hu.ait.android.aloke.memorygame.R;
import hu.ait.android.aloke.memorygame.fragment.GameFragment;
import hu.ait.android.aloke.memorygame.fragment.ScoreFragment;
import hu.ait.android.aloke.memorygame.model.Score;

/**
 * Created by Aloke on 4/9/15.
 */
public class ScoreFragmentAdapter extends FragmentPagerAdapter {
    private Context ctx;

    public ScoreFragmentAdapter(FragmentManager fm, Context ctx) {
        super(fm);
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();

        // regardless of the tab the user is on, the same fragment is used. The
        // only thing that is toggled is the search query (based on Score Difficulty)
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
                return ctx.getString(R.string.easy_text_score_view_pager);
            case 1:
                return ctx.getString(R.string.medium_text_score_view_pager);
            case 2:
                return ctx.getString(R.string.hard_text_score_view_pager);
            default:
                return ctx.getString(R.string.easy_text_score_view_pager);
        }
    }
}
