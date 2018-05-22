package com.example.root.alumnusapp.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import com.google.firebase.FirebaseApp;

public class DialogLoading {
    Context context;
    ProgressDialog progressDialog;
    public DialogLoading(Context context){
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Is on progress......");
    }

    public ProgressDialog setMessageDialog(String message) {
        progressDialog.setMessage(message);
        return progressDialog;
    }
    public ProgressDialog setTitleDialog(String title){
        progressDialog.setTitle(title);
        return progressDialog;
    }

    public void showLoading(){
        progressDialog.show();
    }
    public void dismissLoading(){
        progressDialog.dismiss();
    }
}
