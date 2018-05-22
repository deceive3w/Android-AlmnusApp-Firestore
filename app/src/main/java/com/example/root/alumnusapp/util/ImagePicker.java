package com.example.root.alumnusapp.util;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;

import com.example.root.alumnusapp.screens.BaseView;

import java.io.File;
import java.io.IOException;

import id.zelory.compressor.Compressor;

public class ImagePicker implements BaseView {
    public int CONST_IMAGE_PICK = 55;
    public int CONST_FRAG_PICK = 56;
    int ResultCode;
    Activity activity;
    ImageView imageView;
    Fragment fragment;
    public ImagePicker(Activity activity){
        this.activity = activity;
    }
    public ImagePicker(Fragment fragment){
        this.fragment = fragment;
    }
    public void PickImage(int ResultCode){
        //TODO: ADDING RUNTIME PERMISSION
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        activity.startActivityForResult(intent,CONST_IMAGE_PICK);
        this.ResultCode = ResultCode;
        if(fragment != null){
            fragment.startActivityForResult(intent,ResultCode);
        }else{
            activity.startActivityForResult(intent,ResultCode);
        }
    }
    Bitmap currentBitmap;
    String imagePath;
    File currentImage;
    @Override
    public boolean OnActivityResult(int requestCode, int resultCode, Intent data) throws IOException {
        if(requestCode == ResultCode && data != null) {
            Uri path = data.getData();
            currentBitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), path);

//            imageView.setImageBitmap(currentBitmap);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Uri selectedImageUri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = activity.getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                Log.d("pick", "OnActivityResult: "+imagePath);
                currentImage = new Compressor(activity).setQuality(75).compressToFile(new File(imagePath));
                cursor.close();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean OnFragmentResult(int requestCode, int resultCode, Intent data) throws IOException {
        if(requestCode == ResultCode && data != null) {
            Uri path = data.getData();
            currentBitmap = MediaStore.Images.Media.getBitmap(fragment.getContext().getContentResolver(), path);

//            imageView.setImageBitmap(currentBitmap);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Uri selectedImageUri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = fragment.getContext().getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                Log.d("pick", "OnActivityResult: "+imagePath);
                currentImage = new Compressor(fragment.getContext()).setQuality(75).compressToFile(new File(imagePath));
                cursor.close();
                return true;
            }
        }
        return false;
    }

    public File getCurrentImage() {
        return currentImage;
    }

    public String getImagePath(){
        Log.d("image pick", "getImagePath: "+imagePath);
        return imagePath;
    }

    @Override
    public void OnCreate() {

    }
}
