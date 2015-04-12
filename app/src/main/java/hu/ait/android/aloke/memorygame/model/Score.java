package hu.ait.android.aloke.memorygame.model;

import com.orm.SugarRecord;

import hu.ait.android.aloke.memorygame.R;

/**
 * Created by Aloke on 4/8/15.
 */
public class Score extends SugarRecord<Score> {
    private String readableTime;
    private long numMilis;
    private String date;
    private Difficulty difficulty;

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    // constructor for sugar orm
    public Score() {
    }

    public Score(String readableTime, long millis, String date, Difficulty difficulty) {
        this.readableTime = readableTime;
        numMilis = millis;
        this.date = date;
        this.difficulty = difficulty;
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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
