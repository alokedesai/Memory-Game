package hu.ait.android.aloke.memorygame.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by Aloke on 4/6/15.
 */
public class DifficultyDialog extends DialogFragment implements DialogInterface.OnClickListener {
    public static final String TAG = "DifficultyDialog";

    private static String[] levels = {"Easy", "Medium", "Hard"};

    public interface DifficultyDialogFragmentInterface {
        public void onDifficultyDialogFragmentResult(String item);
    }

    private DifficultyDialogFragmentInterface difficultyDialogFragmentInterface;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setItems(levels, this);

        builder.setNegativeButton("Cancel", null);
        builder.setTitle("Choose Difficulty");

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        difficultyDialogFragmentInterface.onDifficultyDialogFragmentResult(levels[which]);
    }

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
