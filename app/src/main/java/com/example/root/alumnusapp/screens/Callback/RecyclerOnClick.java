package com.example.root.alumnusapp.screens.Callback;

import android.support.annotation.Nullable;

public interface RecyclerOnClick<T> {
    void response(T data,@Nullable T... args);
}
