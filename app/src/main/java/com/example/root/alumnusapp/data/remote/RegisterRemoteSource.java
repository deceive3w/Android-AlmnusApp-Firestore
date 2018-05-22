package com.example.root.alumnusapp.data.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.root.alumnusapp.data.model.User;
import com.example.root.alumnusapp.screens.Callback.DaoCallback;
import com.example.root.alumnusapp.screens.register.RegisterCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterRemoteSource {
    RegisterCallback registerCallback;
    FirebaseAuth firebaseAuth;
    public RegisterRemoteSource(RegisterCallback registerCallback,FirebaseAuth firebaseAuth){
        this.registerCallback = registerCallback;
        this.firebaseAuth = firebaseAuth;
    }
    public void doRegister(String email, String password, final User user){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull final Task<AuthResult> task) {
                if(task.isSuccessful()){
                    user.setUid(task.getResult().getUser().getUid());
                    UserRemoteSource.get().updateProfile(user, FirebaseFirestore.getInstance(), new DaoCallback() {
                        @Override
                        public void onSuccess(@Nullable Object o) {
                            registerCallback.onSuccess(task.getResult().getUser());
                        }

                        @Override
                        public void onFailure(String errorMessage) {

                        }

                        @Override
                        public void onLoading() {

                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                registerCallback.onError(e.getMessage());
            }
        });
    }


}
