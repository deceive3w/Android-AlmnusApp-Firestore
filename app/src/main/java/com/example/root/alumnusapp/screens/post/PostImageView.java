package com.example.root.alumnusapp.screens.post;

import com.example.root.alumnusapp.util.ImagePicker;

import java.io.File;

public interface PostImageView {
    void onCreate(ImagePicker imagePicker, int resultCode);
    void getImageFromPicker(File currentImage);
}
