package com.example.root.alumnusapp.screens.login;

import com.google.firebase.auth.FirebaseUser;

public interface LoginCallback {
    void OnSuccess(FirebaseUser firebaseUser);
    void OnError(String errorMessage);
    void NetworkError();
}
