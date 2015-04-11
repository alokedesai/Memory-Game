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

        pager.setAdapter(new ScoreFragmentAdapter(getSupportFragmentManager(), this));
        springIndicator.setViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}