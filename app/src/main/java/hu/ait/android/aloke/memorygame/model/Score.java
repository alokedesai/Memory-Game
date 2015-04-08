package hu.ait.android.aloke.memorygame.model;

import com.orm.SugarRecord;

/**
 * Created by Aloke on 4/8/15.
 */
public class Score extends SugarRecord<Score> {
    private String readableTime;
    private long numMilis;
    private String date;

    public Score() {
    }

    ;

    public Score(String readableTime, long milis, String date) {
        this.readableTime = readableTime;
        numMilis = milis;
        this.date = date;
    }

    public String getReadableTime() {
        return readableTime;
    }

    public void setReadableTime(String readableTime) {
        this.readableTime = readableTime;
    }

    public long getNumMilis() {
        return numMilis;
    }

    public void setNumMilis(long numMilis) {
        this.numMilis = numMilis;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
