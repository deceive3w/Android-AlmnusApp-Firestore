package com.example.root.alumnusapp.screens.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.root.alumnusapp.data.remote.StorageRemoteSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest implements LoginView{
    @Mock
    private LoginService service;
    private LoginView view;
    private LoginPresenter presenter;

    @Mock
    FirebaseFirestore firebaseFirestore;
    @Before
    public void setUp() throws Exception {
        view = this;
        presenter = new LoginPresenter(view);
        firebaseFirestore = FirebaseFirestore.getInstance();
    }
    @Test
    public void test(){
        Log.d("test", "test: asd");
    }
    @Test
    public void onLoginClicked() {

    }
    @Test
    public void validasiInput(){
        presenter.onLoginClicked();
        verify(view.getEmail());
    }

    @Override
    public String getEmail() {
        return "defri@gmail.com";
    }

    @Override
    public String getPassword() {
        return "gagak321";
    }

    @Override
    public void showToastErrorMessage(String message) {
        assertTrue(false);
    }

    @Override
    public void showLoadingBar() {

    }

    @Override
    public void dismissLoadingBar() {

    }

    @Override
    public void onSuccessLogin(Boolean status) {
        assertTrue(false);
    }

    @Test
    public void testRegister(){
    }
}