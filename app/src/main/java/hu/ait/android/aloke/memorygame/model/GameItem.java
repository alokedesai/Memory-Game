package hu.ait.android.aloke.memorygame.model;

import hu.ait.android.aloke.memorygame.R;

/**
 * Created by Aloke on 4/6/15.
 */
public class GameItem {
    private boolean chosen;
    private int imageResource;
    private SquareType squareType;

    public enum SquareType {
        BANANA(0, R.drawable.banana), APPLE(1, R.drawable.apple);

        private int value;
        private int iconId;

        private SquareType(int value, int iconId) {
            this.value = value;
            this.iconId = iconId;
        }


        public static SquareType fromInt(int value) {
            for (SquareType p : SquareType.values()) {
                if (p.value == value) {
                    return p;
                }
            }
            return BANANA;
        }

        public int getIconId() {
            return iconId;
        }

        public int getValue() {
            return value;
        }
    }

    public GameItem(int resource, SquareType type) {
        imageResource = resource;
        chosen = false;
        squareType = type;
    }

    public boolean isChosen() {
        return chosen;
    }

    public SquareType getSquareType() {
        return squareType;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    public int getImageResource() {
        return (chosen ? imageResource : R.drawable.questionmark);
    }
}
