package com.example.root.alumnusapp.data.remote;

import android.support.annotation.NonNull;

import com.example.root.alumnusapp.screens.login.LoginCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRemoteSource {
    FirebaseAuth firebaseAuth;
    LoginCallback loginCallback;
    public LoginRemoteSource(FirebaseAuth firebaseAuth, LoginCallback loginCallback){
        this.firebaseAuth = firebaseAuth;
        this.loginCallback = loginCallback;
    }
    public FirebaseUser getCurrentUser(){
        if(firebaseAuth.getCurrentUser() == null){
            loginCallback.OnError("Not Login");
        }
        return  firebaseAuth.getCurrentUser();
    }
    public void doLogin(String email,String password){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    loginCallback.OnSuccess(task.getResult().getUser());
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loginCallback.OnError(e.getMessage());
            }
        });
    }
}
