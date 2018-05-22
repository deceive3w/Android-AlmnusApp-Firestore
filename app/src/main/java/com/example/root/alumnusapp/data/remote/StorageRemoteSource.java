package com.example.root.alumnusapp.data.remote;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.root.alumnusapp.screens.Callback.StorageRemoteCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class StorageRemoteSource {
    public static String CONST_FOLDER_SAMPUL = "foto_sampul/";
    public static String CONST_FOLDER_KELAS = "foto_kelas/";
    public FirebaseStorage firebaseStorage;
    static StorageRemoteSource instance;
    public StorageRemoteSource(){
      this.firebaseStorage = FirebaseStorage.getInstance();
    }
    public static StorageRemoteSource get(){
        if(instance == null){
            instance = new StorageRemoteSource();
        }
        return instance;
    }
    public StorageReference getSampulImage(String image_name){
        String path = CONST_FOLDER_SAMPUL+image_name;
        StorageReference storageReference = firebaseStorage.getReference().child(path);
        return storageReference;
    }

    public void uploadImage(String path, String id, File image, final StorageRemoteCallback storageRemoteCallback){
        final StorageReference storageReference = firebaseStorage.getReference().child(path+id+"/"+image.getName());
        Uri uri = Uri.fromFile(image);
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                getDownloadUrl(taskSnapshot);
                storageRemoteCallback.onSuccess(taskSnapshot);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                storageRemoteCallback.onFailure(e.getMessage());
            }
        });
    }
    public void getDownloadUrl(UploadTask.TaskSnapshot taskSnapshot){
        Log.d("link download", "getDownloadUrl: "+taskSnapshot.getDownloadUrl());
    }
}
