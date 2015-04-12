package hu.ait.android.aloke.memorygame;

import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Paint;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import github.chenupt.springindicator.SpringIndicator;
import hu.ait.android.aloke.memorygame.adapter.ScoreFragmentAdapter;
import hu.ait.android.aloke.memorygame.fragment.ScoreFragment;


public class ScoresActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scores);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);

        // set adapter for both the pager and the spring indicator
        pager.setAdapter(new ScoreFragmentAdapter(getSupportFragmentManager(), this));
        springIndicator.setViewPager(pager);
    }
}