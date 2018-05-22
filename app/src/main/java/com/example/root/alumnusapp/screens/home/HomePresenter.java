package com.example.root.alumnusapp.screens.home;

import com.example.root.alumnusapp.data.model.Kelas;
import com.example.root.alumnusapp.data.remote.KelasRemoteSource;
import com.example.root.alumnusapp.screens.Callback.KelasCallback;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class HomePresenter{
    KelasRemoteSource kelasRemoteSource;
    KelasCallback kelasCallback;
    HomeView homeView;
    public HomePresenter(HomeView homeView){
        this.homeView = homeView;
    }
    public void displasyAllKelas(KelasCallback kelasCallback){
        kelasRemoteSource = new KelasRemoteSource(FirebaseFirestore.getInstance(),kelasCallback);
        kelasRemoteSource.getAllKelas();
    }

}
