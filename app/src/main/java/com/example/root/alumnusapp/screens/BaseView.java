package com.example.root.alumnusapp.screens;

import android.content.Intent;

import java.io.IOException;

public interface BaseView {
    void OnCreate();
    boolean OnActivityResult(int requestCode, int resultCode, Intent data) throws IOException;
    boolean OnFragmentResult(int requestCode, int resultCode, Intent data) throws IOException;
}
