package hu.ait.android.aloke.memorygame.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import hu.ait.android.aloke.memorygame.MainActivity;
import hu.ait.android.aloke.memorygame.R;

/**
 * Created by Aloke on 4/12/15.
 */
public class FirstTimeDialog extends DialogFragment {
    public static final String TAG = "DialogFragment";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Welcome!");

        builder.setMessage(getActivity().getString(
                R.string.first_time_update_settings_dialog_message));

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((MainActivity) getActivity()).startSettingsActivity();
            }
        });

        builder.setNegativeButton("No", null);

        return builder.create();
    }
}
