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
        EASY(0), MEDIUM(1), HARD(2);

        private int value;

        private Difficulty(int value) {
            this.value = value;
        }


        public static Difficulty fromInt(int value) {
            for (Difficulty d : Difficulty.values()) {
                if (d.value == value) {
                    return d;
                }
            }
            return EASY;
        }

        public int getValue() {
            return value;
        }
    }


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
