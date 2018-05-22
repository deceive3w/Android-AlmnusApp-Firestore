package com.example.root.alumnusapp.data.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.root.alumnusapp.data.model.User;
import com.example.root.alumnusapp.screens.Callback.DaoCallback;
import com.example.root.alumnusapp.screens.Callback.EmailVerification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class UserRemoteSource {
    static UserRemoteSource userRemoteSource;
    public static UserRemoteSource get(){
        if(userRemoteSource == null){
            userRemoteSource = new UserRemoteSource();
        }
        return userRemoteSource;
    }
    public void sendEmailVerification(FirebaseUser firebaseUser, final EmailVerification emailVerification){
        firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    emailVerification.OnSuccessSend();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                emailVerification.OnFailedSend(e.getMessage());
            }
        });
    }
    public boolean checkVerificationEmail(FirebaseUser firebaseUser){
        return firebaseUser.isEmailVerified();
    }
    public void updateProfile(User user, FirebaseFirestore firebaseFirestore, final DaoCallback daoCallback){
        firebaseFirestore.collection("user").document(user.getUid()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                daoCallback.onSuccess(null);
                Log.e("firestore", "onSuccess: update data" );
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                daoCallback.onFailure(e.getMessage());
                Log.e("firestore", "onFailure: "+e.getMessage() );
            }
        });
    }
    public void getUser(String uid, FirebaseFirestore firebaseFirestore, final DaoCallback daoCallback){
        firebaseFirestore.collection("user").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                daoCallback.onSuccess(user);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                daoCallback.onFailure(e.getMessage());
            }
        });
    }
}
