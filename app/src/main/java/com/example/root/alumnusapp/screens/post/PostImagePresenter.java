package com.example.root.alumnusapp.screens.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.root.alumnusapp.screens.PostImageKelasActivity;
import com.example.root.alumnusapp.util.ImagePicker;

import java.io.File;

import javax.annotation.Nullable;

public class PostImagePresenter implements PostImageView {
    Context context;
    public PostImagePresenter(Context context){
        this.context = context;
    }
    int resultCode;
    public void PickImage(){

    }

    @Override
    public void onCreate(ImagePicker imagePicker, int resultCode) {
        this.resultCode = resultCode;
        imagePicker.PickImage(resultCode);
    }

    @Override
    public void getImageFromPicker(File currentImage) {
        Log.d("image picker", "getImageFromPicker: "+currentImage.getPath());
        context.startActivity(new Intent(context, PostImageKelasActivity.class));
        PostImageKelasActivity.user_image_file.postValue(currentImage);
    }
}
