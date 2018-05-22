package com.example.root.alumnusapp.screens.Callback;

import javax.annotation.Nullable;

public interface DaoCallback<M> {
    void onSuccess(@Nullable M m);
    void onFailure(String errorMessage);
    void onLoading();
}
