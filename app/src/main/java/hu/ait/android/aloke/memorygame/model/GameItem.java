package hu.ait.android.aloke.memorygame.model;

import hu.ait.android.aloke.memorygame.R;

/**
 * Created by Aloke on 4/6/15.
 */
public class GameItem {
    private boolean chosen;
    private SquareType squareType;

    public enum SquareType {
        SUNSHINE(0, R.drawable.sunshine), COBALT(1, R.drawable.cobalt),
        ORCHID(2, R.drawable.orchid), SANDSTONE(3, R.drawable.sandstone),
        BLOOD_ORANGE(4, R.drawable.blood_orange), PEACH(5, R.drawable.peach),
        CHIVE(6, R.drawable.chive), PERIWINKLE(7, R.drawable.periwinkle), TEAL(8, R.drawable.teal),
        BRICK(9, R.drawable.brick);

        private int value;
        private int iconId;

        private SquareType(int value, int iconId) {
            this.value = value;
            this.iconId = iconId;
        }

        public int getIconId() {
            return iconId;
        }

        public int getValue() {
            return value;
        }
    }

    public GameItem(SquareType type) {
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
        return squareType.getIconId();
    }
}
