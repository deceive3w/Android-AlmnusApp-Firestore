package com.example.root.alumnusapp.screens.Callback;

import com.example.root.alumnusapp.data.model.Kelas;

import java.util.List;

public interface KelasCallback {
    void onSuccess(Kelas kelas);
    void getAllKelas(List<Kelas> kelasList);
}
