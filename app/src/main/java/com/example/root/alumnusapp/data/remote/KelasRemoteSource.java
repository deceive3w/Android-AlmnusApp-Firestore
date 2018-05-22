package com.example.root.alumnusapp.data.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.root.alumnusapp.data.model.Foto;
import com.example.root.alumnusapp.data.model.Kelas;
import com.example.root.alumnusapp.screens.Callback.DaoCallback;
import com.example.root.alumnusapp.screens.Callback.KelasCallback;
import com.example.root.alumnusapp.screens.Callback.StorageRemoteCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class KelasRemoteSource {
    FirebaseFirestore firebaseFirestore;
    KelasCallback kelasCallback;
    String COLLECTION_NAME = "kelas";
    public KelasRemoteSource(FirebaseFirestore firebaseFirestore,KelasCallback kelasCallback){
        this.firebaseFirestore = firebaseFirestore;
        this.kelasCallback = kelasCallback;
    }

    public void getKelas(final String document, final DaoCallback<Kelas> daoCallback){
        firebaseFirestore.collection(COLLECTION_NAME).document(document).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                Kelas kelas = documentSnapshot.toObject(Kelas.class);
                kelasCallback.onSuccess(kelas);
                daoCallback.onSuccess(kelas);
            }
        });
    }
    public void getAllKelas(){
        firebaseFirestore.collection(COLLECTION_NAME).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                List<Kelas> myData = new ArrayList<>();
                for (int i = 0; i < documentSnapshots.getDocuments().size();i++){
                    Kelas kelas = documentSnapshots.getDocuments().get(i).toObject(Kelas.class);
                    kelas.setUid(documentSnapshots.getDocuments().get(i).getId());
                    myData.add(kelas);
                }
                kelasCallback.getAllKelas(myData);
            }
        });
    }
    public void updateKelas(Kelas kelas, final DaoCallback daoCallback){
        firebaseFirestore.collection("kelas").document(kelas.getUid()).set(kelas).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                daoCallback.onSuccess(null);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                daoCallback.onFailure(e.getMessage());
            }
        });
    }
    //todo: ganti model document
    public void uploadImageKelas(final String kelasId, File file, FirebaseStorage firebaseStorage){
        StorageRemoteSource.get().uploadImage("foto_kelas/", kelasId, file, new StorageRemoteCallback() {
            @Override
            public void onSuccess(Object task) {
                UploadTask.TaskSnapshot uploadTask = (UploadTask.TaskSnapshot) task;
                getKelas(kelasId, new DaoCallback<Kelas>() {
                    @Override
                    public void onSuccess(@Nullable Kelas kelas) {
                        kelas.getFoto_kelas().add(new Foto("adasd","asd"));
                        updateKelas(kelas, new DaoCallback() {
                            @Override
                            public void onSuccess(@Nullable Object o) {
                                Log.d("upload image", "onSuccess: done upload");
                            }

                            @Override
                            public void onFailure(String errorMessage) {
                                Log.d("upload image", "onSuccess: error upload"+errorMessage);
                            }

                            @Override
                            public void onLoading() {

                            }
                        });
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.d("upload image", "onSuccess: error upload"+errorMessage);
                    }

                    @Override
                    public void onLoading() {

                    }
                });
            }

            @Override
            public void onFailure(String message) {
                Log.d("upload image", "onSuccess: error upload"+message);
            }

            @Override
            public void onLoading() {

            }
        });
    }
}
