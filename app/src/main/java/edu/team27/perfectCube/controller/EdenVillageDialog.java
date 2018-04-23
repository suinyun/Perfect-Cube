package edu.team27.perfectCube.controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * creates the pop up window, which clarify if Eden Village's
 * family or single capacity should change
 * */
public class EdenVillageDialog extends DialogFragment {

    private String who;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String message = "Are you making a reservation for a family" +
                "or for an individual?";
        String title = "Eden Village Reservation";
        String f = "Family";
        String i = "Individual";
        String sendBack = "";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton(f, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                who = "f";
                dialog.cancel();
            }
        });
        builder.setNegativeButton(i, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                who = "i";
                dialog.cancel();
            }
        });
        sendBack = who;
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

}
