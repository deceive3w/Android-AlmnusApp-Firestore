package com.example.root.alumnusapp.screens.Callback;

import com.example.root.alumnusapp.data.model.User;
import com.google.firebase.auth.FirebaseUser;

public interface EmailVerification {
    void isVerified(User user);
    void OnSuccessSend();
    void OnFailedSend(String message);
    void isNotVerified(FirebaseUser firebaseUser);
}
