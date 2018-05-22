package com.example.root.alumnusapp.screens.register;

import com.example.root.alumnusapp.data.model.User;

public interface RegisterView {
    void onSuccess();
    void onError();
    String getEmail();
    String getPassword();
    User getUser();
    void showLoading();
}
