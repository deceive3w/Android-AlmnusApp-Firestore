package com.example.root.alumnusapp.screens.register;

import android.util.Log;

import com.example.root.alumnusapp.data.remote.RegisterRemoteSource;
import com.example.root.alumnusapp.data.remote.UserRemoteSource;
import com.example.root.alumnusapp.screens.Callback.EmailVerification;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterPresenter implements RegisterCallback {
    FirebaseAuth firebaseAuth;
    RegisterRemoteSource registerRemoteSource;
    RegisterView registerView;
    EmailVerification emailVerification;
    public RegisterPresenter(RegisterView registerView, EmailVerification emailVerification){
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.registerView = registerView;
        this.emailVerification = emailVerification;
        this.registerRemoteSource = new RegisterRemoteSource(this,firebaseAuth);
    }
    public void OnRegisterClicked(){
        registerView.showLoading();
        registerRemoteSource.doRegister(registerView.getEmail(),registerView.getPassword(),registerView.getUser());

    }

    @Override
    public void onSuccess(FirebaseUser firebaseUser) {
        UserRemoteSource.get().sendEmailVerification(firebaseUser,emailVerification);
    }

    @Override
    public void onError(String errorMessage) {
        Log.e("firestore", "onError: "+errorMessage);
    }
}
