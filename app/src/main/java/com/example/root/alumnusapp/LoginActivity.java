package com.example.root.alumnusapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.dx.command.Main;
import com.example.root.alumnusapp.data.local.AppDatabase;
import com.example.root.alumnusapp.data.model.Kelas;
import com.example.root.alumnusapp.data.model.User;
import com.example.root.alumnusapp.data.remote.KelasRemoteSource;
import com.example.root.alumnusapp.data.remote.StorageRemoteSource;
import com.example.root.alumnusapp.screens.Callback.EmailVerification;
import com.example.root.alumnusapp.screens.Callback.KelasCallback;
import com.example.root.alumnusapp.screens.login.LoginPresenter;
import com.example.root.alumnusapp.screens.login.LoginView;
import com.example.root.alumnusapp.screens.main.MainActivity;
import com.example.root.alumnusapp.screens.register.ChooseKelas;
import com.example.root.alumnusapp.services.EmailVerificationServices;
import com.example.root.alumnusapp.util.DialogLoading;
import com.example.root.alumnusapp.util.Validator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView,EmailVerification{
    LoginPresenter loginPresenter;
    @BindView(R.id.login_et_email)
    TextInputLayout et_email;
    @BindView(R.id.login_et_password)
    TextInputLayout et_password;
    DialogLoading dialogLoading;
    EmailVerificationServices emailVerificationServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        AppDatabase.get(this).clearAllTables();
        boolean isLogin = AppDatabase.get(this).userDao().getUser() != null;

        if(isLogin){
            Log.d("room", "onCreate: "+AppDatabase.get(this).userDao().getUser());
           Intent intent = new Intent(this,MainActivity.class);
           startActivity(intent);
           return;
        }
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this,this);
        dialogLoading = new DialogLoading(this);
        emailVerificationServices = new EmailVerificationServices(this);

//        User user = new User();
//        user.setNama("defri");
//        AppDatabase.get(this).userDao().insert(user);
//        Log.d("room", "onCreate: "+AppDatabase.get(this).userDao().getUser().getNama());

    }

    @OnClick(R.id.login_btn_action)
    void perfomLogin(){
        Log.d("login activity", "perfomLogin: clicked");
        if(Validator.isEmpty(getEmail())){
            showToastErrorMessage("Email kosong");
            return;
        }
        if (Validator.isEmpty(getPassword())){
            showToastErrorMessage("Password kosong");
            return;
        }
        loginPresenter.onLoginClicked();

    }

    @OnClick(R.id.login_btn_register)
    void openRegister(){
        Intent intent = new Intent(this, ChooseKelas.class);
        startActivity(intent);
    }
    @Override
    public String getEmail()
    {
        return et_email.getEditText().getText().toString();
    }

    @Override
    public String getPassword() {
        return et_password.getEditText().getText().toString();
    }

    @Override
    public void showToastErrorMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingBar() {
        dialogLoading.showLoading();
    }

    @Override
    public void dismissLoadingBar() {
        dialogLoading.dismissLoading();
    }

    @Override
    public void onSuccessLogin(Boolean status) {
        Toast.makeText(this,"Login berhasil",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isVerified(User user) {
        Log.d("email status", "isVerified: "+user.getNama());
        AppDatabase.get(this).userDao().insert(user);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnSuccessSend() {

    }

    @Override
    public void OnFailedSend(String message) {

    }

    @Override
    public void isNotVerified(FirebaseUser firebaseUser) {
        Log.d("email status", "isNotVerified: ");
        emailVerificationServices.openDialogEmailNotVerif(firebaseUser,this);
    }
}
