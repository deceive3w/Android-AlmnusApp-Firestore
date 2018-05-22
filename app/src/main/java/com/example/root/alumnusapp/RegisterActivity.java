package com.example.root.alumnusapp;

import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.root.alumnusapp.R;
import com.example.root.alumnusapp.data.model.Kelas;
import com.example.root.alumnusapp.data.model.User;
import com.example.root.alumnusapp.data.remote.StorageRemoteSource;
import com.example.root.alumnusapp.screens.Callback.EmailVerification;
import com.example.root.alumnusapp.screens.Callback.StorageRemoteCallback;
import com.example.root.alumnusapp.screens.register.ChooseKelas;
import com.example.root.alumnusapp.screens.register.RegisterPresenter;
import com.example.root.alumnusapp.screens.register.RegisterView;
import com.example.root.alumnusapp.util.DialogLoading;
import com.example.root.alumnusapp.util.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterView,EmailVerification {
    @BindView(R.id.register_email)
    TextInputLayout register_email;
    @BindView(R.id.register_password)
    TextInputLayout register_password;
    @BindView(R.id.register_alamat)
    TextInputLayout register_alamat;
    @BindView(R.id.register_motto)
    TextInputLayout register_motto;
    @BindView(R.id.register_pekerjaan)
    TextInputLayout register_pekerjaan;
    @BindView(R.id.register_nama)
    TextInputLayout register_nama;
    RegisterPresenter registerPresenter;
    Kelas my_kelas;
    DialogLoading dialogLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ChooseKelas.user_choice.observe(this, new Observer<Kelas>() {
            @Override
            public void onChanged(@Nullable Kelas kelas) {
                my_kelas = kelas;
            }
        });
        dialogLoading = new DialogLoading(this);
        ButterKnife.bind(this);
//        imagePicker = new ImagePicker(this,register_profile_image);

        registerPresenter = new RegisterPresenter(this,this);
    }

    @OnClick(R.id.register_btn_action)
    void registerOnClick(){
//        FirebaseAuth.getInstance().signInWithEmailAndPassword("defrykun1@gmail.com","gagak321").addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//            @Override
//            public void onSuccess(AuthResult authResult) {
//                FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
//                Uri uri = Uri.fromFile(imagePicker.getCurrentImage());
//                StorageReference riversRef = firebaseStorage.getReference("foto_sampul").child(imagePicker.getCurrentImage().getName());
//
//                riversRef.putFile(uri)
//                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                // Get a URL to the uploaded content
//                                Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                                Log.e("upload",taskSnapshot.getDownloadUrl().toString());
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception exception) {
//                                // Handle unsuccessful uploads
//                                // ...
//                                Log.e("upload", "onFailure: "+exception.getMessage());
//                            }
//                        });
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d("auth user", "onFailure: "+e.getMessage());
//            }
//        });
//        Log.d("image view", "registerOnClick: "+imagePicker.getCurrentImage().getName());
        registerPresenter.OnRegisterClicked();
    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }

    @Override
    public String getEmail() {
        return register_email.getEditText().getText().toString();
    }

    @Override
    public String getPassword() {
        return register_password.getEditText().getText().toString();
    }

    @Override
    public User getUser() {
        User user = new User();
        user.setAlamat(register_alamat.getEditText().getText().toString());
        user.setMotto(register_motto.getEditText().getText().toString());
        user.setNama(register_nama.getEditText().getText().toString());
        user.setPekerjaan(register_nama.getEditText().getText().toString());
        user.setKelasId(my_kelas.getUid());
        return user;
    }

    @Override
    public void showLoading() {
        dialogLoading.showLoading();
    }

    @Override
    public void isVerified(User user) {

    }

    @Override
    public void OnSuccessSend() {
        dialogLoading.dismissLoading();
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setMessage("Silahkan cek email, untuk mengaktifkan akun anda");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setTitle("Registrasi Berhasil");
        builder.show();
    }

    @Override
    public void OnFailedSend(String message) {
        dialogLoading.dismissLoading();
        Toast.makeText(this,"Gagal mengirim email"+message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void isNotVerified(FirebaseUser firebaseUser) {

    }

}
