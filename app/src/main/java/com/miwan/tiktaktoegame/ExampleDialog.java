package com.miwan.tiktaktoegame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ExampleDialog extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builderdiag= new AlertDialog.Builder(getActivity());
        builderdiag.setTitle("Rule").setMessage("The first player to get 4 marks in a row wins absolutely. If all spaces are full and there is no winner, whoever has the higher amount of 3 marks in a row wins, if both players have the same amount, the game is tied.").setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return builderdiag.create();
    }
}
