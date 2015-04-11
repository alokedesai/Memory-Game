package hu.ait.android.aloke.memorygame.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import hu.ait.android.aloke.memorygame.GameActivity;
import hu.ait.android.aloke.memorygame.R;

/**
 * Created by Aloke on 4/8/15.
 */
public class GameOverDialog extends DialogFragment {

    public static final String TAG = "GameOverDialog";
    public static final String TIME = "TIME";
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String time = getArguments().getString(TIME);

        builder.setTitle(getString(R.string.you_won_game_over_fragment));
        builder.setMessage(getString(R.string.your_time_game_over_dialog, time));

        builder.setPositiveButton(getString(R.string.restart_dialog_label), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((GameActivity) getActivity()).resetGame();
            }
        });

        builder.setNegativeButton(getString(R.string.main_menu_dialog), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                (getActivity()).finish();
            }
        });

        builder.setNeutralButton(getString(R.string.high_scores_dialog_label), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((GameActivity) getActivity()).startScoresActivity();
            }
        });

        return builder.create();
    }
}
