package hu.ait.android.aloke.memorygame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import hu.ait.android.aloke.memorygame.R;
import hu.ait.android.aloke.memorygame.model.Score;

/**
 * Created by Aloke on 4/8/15.
 */
public class ScoreAdapter extends BaseAdapter {
    private final Context ctx;

    //--------------------------------------------
    //TODO: remove this
    SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy 'at' hh:mm a");
    Date now = new Date();
    String strDate = sdfDate.format(now);
    //--------------------------------------------

    private List<Score> scores;

    public ScoreAdapter(Context ctx, List<Score> scores) {
        this.ctx = ctx;
        this.scores = scores;
    }

    @Override
    public int getCount() {
        return scores.size();
    }

    @Override
    public Object getItem(int position) {
        return scores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {
            v = LayoutInflater.from(ctx).inflate(R.layout.row_score, null);

            ViewHolder holder = new ViewHolder();
            holder.tvDate = (TextView) v.findViewById(R.id.tvDate);
            holder.tvTime = (TextView) v.findViewById(R.id.tvTime);

            // set tag for caching
            v.setTag(holder);
        }

        Score score = scores.get(position);

        if (score != null) {
            ViewHolder holder = (ViewHolder) v.getTag();
            holder.tvDate.setText(score.getDate());
            holder.tvTime.setText(score.getReadableTime());
        }

        return v;
    }

    public void addItem(Score score) {
        scores.add(score);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView tvDate;
        TextView tvTime;
    }
}
