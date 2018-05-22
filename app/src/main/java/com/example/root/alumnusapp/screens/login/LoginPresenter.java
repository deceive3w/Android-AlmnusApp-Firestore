package com.example.root.alumnusapp.screens.login;

import com.example.root.alumnusapp.data.model.User;
import com.example.root.alumnusapp.data.remote.LoginRemoteSource;
import com.example.root.alumnusapp.data.remote.UserRemoteSource;
import com.example.root.alumnusapp.screens.Callback.DaoCallback;
import com.example.root.alumnusapp.screens.Callback.EmailVerification;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.annotation.Nullable;

public class LoginPresenter implements LoginCallback {
    LoginView loginView;
    LoginService loginService;
    LoginRemoteSource loginRemoteSource;
    FirebaseAuth firebaseAuth;
    EmailVerification emailVerification;
    public LoginPresenter(LoginView loginView,EmailVerification emailVerification){
        this.emailVerification = emailVerification;
        this.loginView = loginView;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.loginRemoteSource = new LoginRemoteSource(firebaseAuth,this);
    }

    public void onLoginClicked(){
        loginView.showLoadingBar();
        loginRemoteSource.doLogin(loginView.getEmail(),loginView.getPassword());
    }

    @Override
    public void OnSuccess(FirebaseUser firebaseUser) {
        loginView.onSuccessLogin(true);
        boolean emailStatus = UserRemoteSource.get().checkVerificationEmail(firebaseUser);
        if(emailStatus){
            User user;
            UserRemoteSource.get().getUser(firebaseUser.getUid(), FirebaseFirestore.getInstance(), new DaoCallback<User>() {
                @Override
                public void onSuccess(@Nullable User user) {
                    loginView.dismissLoadingBar();
                    emailVerification.isVerified(user);
                }

                @Override
                public void onFailure(String errorMessage) {

                }

                @Override
                public void onLoading() {

                }
            });

        }else {
            emailVerification.isNotVerified(firebaseUser);
        }
    }

    @Override
    public void OnError(String errorMessage) {
        loginView.dismissLoadingBar();
        loginView.showToastErrorMessage(errorMessage);
    }

    @Override
    public void NetworkError() {
        loginView.showToastErrorMessage("Network Error");
    }
}
