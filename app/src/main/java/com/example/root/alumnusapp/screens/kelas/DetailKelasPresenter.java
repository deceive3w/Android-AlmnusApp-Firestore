package com.example.root.alumnusapp.screens.kelas;

import com.example.root.alumnusapp.data.model.Kelas;
import com.example.root.alumnusapp.data.remote.KelasRemoteSource;
import com.example.root.alumnusapp.data.remote.StorageRemoteSource;
import com.example.root.alumnusapp.screens.Callback.DaoCallback;
import com.example.root.alumnusapp.screens.Callback.KelasCallback;
import com.example.root.alumnusapp.screens.Callback.StorageRemoteCallback;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;

import javax.annotation.Nullable;

public class DetailKelasPresenter {
    KelasRemoteSource kelasRemoteSource;
    public DetailKelasPresenter(){

    }
    public void getDetail(KelasCallback kelasCallback,String id){
        kelasRemoteSource = new KelasRemoteSource(FirebaseFirestore.getInstance(),kelasCallback);
        kelasRemoteSource.getKelas(id, new DaoCallback<Kelas>() {
            @Override
            public void onSuccess(@Nullable Kelas kelas) {

            }

            @Override
            public void onFailure(String errorMessage) {

            }

            @Override
            public void onLoading() {

            }
        });
    }
    public void changeSampul(String key, File image, StorageRemoteCallback storageRemoteCallback){
        StorageRemoteSource.get().uploadImage(StorageRemoteSource.CONST_FOLDER_SAMPUL,key,image,storageRemoteCallback);
    }
    public void updateKelas(Kelas kelas, DaoCallback daoCallback){
        kelasRemoteSource = new KelasRemoteSource(FirebaseFirestore.getInstance(),null);
        kelasRemoteSource.updateKelas(kelas,daoCallback);
    }
}
