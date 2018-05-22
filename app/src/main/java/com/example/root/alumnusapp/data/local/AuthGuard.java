package com.example.root.alumnusapp.data.local;

import android.app.Activity;

public class AuthGuard {
    static AuthGuard instance;
    Activity activity;
    public static AuthGuard get(Activity activity){
        if(instance == null){
            instance = new AuthGuard();
        }
        return instance;
    }
}
