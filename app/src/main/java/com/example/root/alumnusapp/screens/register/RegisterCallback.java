package com.example.root.alumnusapp.screens.register;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public interface RegisterCallback {
    void onSuccess(FirebaseUser firebaseUser);
    void onError(String errorMessage);
}
