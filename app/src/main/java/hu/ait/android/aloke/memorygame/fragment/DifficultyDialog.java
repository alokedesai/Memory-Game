package hu.ait.android.aloke.memorygame.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

import hu.ait.android.aloke.memorygame.GameActivity;
import hu.ait.android.aloke.memorygame.R;

/**
 * Created by Aloke on 4/6/15.
 */
public class DifficultyDialog extends DialogFragment implements DialogInterface.OnClickListener {
    public static final String TAG = "DifficultyDialog";

    private static String[] levels = {"Easy", "Medium", "Hard"};

    //the corresponding game board sizes to the levels
    private static int[] levelSizes = {GameActivity.EASY_GAME, GameActivity.MEDIUM_GAME, GameActivity.HARD_GAME};

    public interface DifficultyDialogFragmentInterface {
        public void onDifficultyDialogFragmentResult(String item, int numPieces);
    }

    private DifficultyDialogFragmentInterface difficultyDialogFragmentInterface;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setItems(levels, this);

        builder.setNegativeButton(getActivity().getString(R.string.cancel_button_text_difficulty_dialog), null);
        builder.setTitle(getActivity().getString(R.string.choose_dificulty_text_difficulty_dialog));

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        difficultyDialogFragmentInterface.onDifficultyDialogFragmentResult(levels[which], levelSizes[which]);
    }


    // on Attach override to instruct developer that he/she must implement this method
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            difficultyDialogFragmentInterface = (DifficultyDialogFragmentInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement DifficultyDialogFragmentInterface");
        }
    }
}
