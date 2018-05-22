package com.example.root.alumnusapp.screens.register;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.root.alumnusapp.R;
import com.example.root.alumnusapp.RegisterActivity;
import com.example.root.alumnusapp.data.model.Kelas;
import com.example.root.alumnusapp.data.remote.KelasRemoteSource;
import com.example.root.alumnusapp.screens.Callback.KelasCallback;
import com.example.root.alumnusapp.screens.register.adapter.ChooseKelasAdapter;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseKelas extends AppCompatActivity implements KelasCallback {
    KelasRemoteSource kelasRemoteSource;
    @BindView(R.id.choose_kelas_spinner)
    Spinner spinner;
    @BindView(R.id.choose_kelas_btn_next)
    Button btn_next;
    MutableLiveData<List<Kelas>> fetchListKelasObservable;
    public static MutableLiveData<Kelas> user_choice =  new MutableLiveData<>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_kelas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Register");
        toolbar.setElevation(0);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        kelasRemoteSource = new KelasRemoteSource(FirebaseFirestore.getInstance(),this);
        kelasRemoteSource.getAllKelas();
        fetchListKelasObservable = new MutableLiveData<>();

        chooseKelasAdapter = new ChooseKelasAdapter(this,R.layout.support_simple_spinner_dropdown_item);
        bind_observable();
        spinner.setAdapter(chooseKelasAdapter);
        spinner.setPrompt("Daftar Kelas");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user_choice.postValue(chooseKelasAdapter.getItem(position));
                btn_next.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                btn_next.setEnabled(false);
            }
        });
    }
    void bind_observable(){
        fetchListKelasObservable.observe(this, new Observer<List<Kelas>>() {
            @Override
            public void onChanged(@Nullable List<Kelas> kelas) {
                Log.d("firstore", "onChanged: new data" +kelas.size());
                chooseKelasAdapter.clear();
                chooseKelasAdapter.bind(kelas);
//                chooseKelasAdapter.notifyDataSetChanged();
                spinner.setAdapter(chooseKelasAdapter);
            }
        });
    }
    @Override
    public void onSuccess(Kelas kelas) {

    }
    @OnClick(R.id.choose_kelas_btn_next)
    public void nextRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
    ChooseKelasAdapter chooseKelasAdapter;

    @Override
    public void getAllKelas(List<Kelas> kelasList) {
        fetchListKelasObservable.postValue(kelasList);
    }
}
