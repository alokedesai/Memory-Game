package hu.ait.android.aloke.memorygame.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by Aloke on 4/6/15.
 */
public class DifficultyDialog extends DialogFragment {
    private static String[] levels = {"Easy", "Medium", "Hard"};

   

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(levels, null);
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Cancel", null);
        builder.setTitle("Choose Difficulty");

        return builder.create();
    }
}
