package com.example.sphien2011.sessionmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by sphien2011 on 29/10/2016.
 */
public class AlertDialogManager {
    public void showAlergDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        if (status != null) {
//            alertDialog.setIcon(status ? R.drawable.success : R.drawable.fail);
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            alertDialog.show();
        }
    }
}
