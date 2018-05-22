package com.example.root.alumnusapp.screens.login;

public interface LoginView {
    String getEmail();
    String getPassword();
    void showToastErrorMessage(String message);
    void showLoadingBar();
    void dismissLoadingBar();
    void onSuccessLogin(Boolean status);
}
