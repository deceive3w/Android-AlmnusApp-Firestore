package com.example.root.alumnusapp.services;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.root.alumnusapp.data.remote.UserRemoteSource;
import com.example.root.alumnusapp.screens.Callback.EmailVerification;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerificationServices {
    Context context;
    FirebaseUser firebaseUser;
    AlertDialog.Builder builder;
    public EmailVerificationServices(Context context){
        this.context = context;
        builder = new AlertDialog.Builder(context);
    }
    public void openDialogEmailNotVerif(final FirebaseUser firebaseUser, final EmailVerification emailVerification){
        builder.setMessage("Email belum di verifikasi, silahkan cek email, atau jika belum menerima, klik button kirim ulang");
        builder.setNegativeButton("Kirim Ulang", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserRemoteSource.get().sendEmailVerification(firebaseUser,emailVerification);
            }
        });
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
