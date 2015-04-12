package hu.ait.android.aloke.memorygame;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import hu.ait.android.aloke.memorygame.adapter.ScoreFragmentAdapter;
import hu.ait.android.aloke.memorygame.adapter.TutorialFragmentAdapter;


public class TutorialActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        ViewPager pager = (ViewPager) findViewById(R.id.pagerTutorial);
        pager.setAdapter(new TutorialFragmentAdapter(getSupportFragmentManager(), this));
    }
}
