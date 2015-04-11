package hu.ait.android.aloke.memorygame.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hu.ait.android.aloke.memorygame.fragment.TutorialFragmentGamePlay;
import hu.ait.android.aloke.memorygame.fragment.TutorialFragmentHighScore;
import hu.ait.android.aloke.memorygame.fragment.TutorialFragmentLevels;

/**
 * Created by Aloke on 4/10/15.
 */
public class TutorialFragmentAdapter extends FragmentPagerAdapter {

    public TutorialFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    // Create 3 fragments and toggle them here
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // first fragment
                return new TutorialFragmentLevels();
            case 1:
                // second fragment
                return new TutorialFragmentGamePlay();
            case 2:
                // third fragment
                return new TutorialFragmentHighScore();
            default:
                return new TutorialFragmentLevels();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Levels";
            case 1:
                return "Gameplay";
            case 2:
                return "Highscore";
            default:
                return "Instructions";
        }
    }
}
