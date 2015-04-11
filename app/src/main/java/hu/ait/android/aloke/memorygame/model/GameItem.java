package hu.ait.android.aloke.memorygame.model;

import hu.ait.android.aloke.memorygame.R;

/**
 * Created by Aloke on 4/6/15.
 */
public class GameItem {
    private boolean chosen;
    private SquareType squareType;

    public enum SquareType {
//        BANANA(0, R.drawable.banana), APPLE(1, R.drawable.apple),
//        STRAWBERRY(2, R.drawable.strawberry), CHERRY(3, R.drawable.strawberry),
//        GRAPE(4, R.drawable.grape), LEMON(5, R.drawable.lemon),
//        WATERMELON(6, R.drawable.watermelon), ORANGE(7, R.drawable.orange),
//        BLUEBERRY(8, R.drawable.blueberry), KIWI(9, R.drawable.kiwi),
//        PINEAPPLE(10, R.drawable.pineapple), MANGO(11, R.drawable.mango), LIME(12, R.drawable.lime),
//        RASPBERRY(13, R.drawable.raspberry), COCONUT(14, R.drawable.coconut),
//        GRAPEFRUIT(15, R.drawable.grapefruit), PEAR(16, R.drawable.pear),
//        PEACH(17, R.drawable.peach);


//        ACCOUNT(0, R.drawable.acccount), ACCESSIBILITY(1, R.drawable.accessibility),
//        ALARM(2, R.drawable.alarm), ALBUM(3, R.drawable.album), BRIGHTNESS(4, R.drawable.brightness),
//        BUS(5, R.drawable.bus), CAKE(6, R.drawable.cake), CAMERA(7, R.drawable.camera),
//        FLORIST(8, R.drawable.florist), HEADSET(9, R.drawable.headset);

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


        public static SquareType fromInt(int value) {
            for (SquareType p : SquareType.values()) {
                if (p.value == value) {
                    return p;
                }
            }
            return TEAL;
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
        return (chosen ? squareType.getIconId() : R.drawable.new_question_mark);
    }
}
