package com.example.root.alumnusapp.screens.Callback;

public interface StorageRemoteCallback<T> {
    void onSuccess(T task);
    void onFailure(String message);
    void onLoading();
}
