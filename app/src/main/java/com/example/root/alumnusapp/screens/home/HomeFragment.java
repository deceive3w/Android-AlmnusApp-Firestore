package com.example.root.alumnusapp.screens.home;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.alumnusapp.DetailKelasActivity;
import com.example.root.alumnusapp.R;
import com.example.root.alumnusapp.data.model.Kelas;
import com.example.root.alumnusapp.screens.Callback.KelasCallback;
import com.example.root.alumnusapp.screens.Callback.RecyclerOnClick;
//import com.example.root.alumnusapp.screens.kelas.KelasFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView,KelasCallback {

    public MutableLiveData<List<Kelas>> kelasMutableLiveData = new MutableLiveData<>();
    @BindView(R.id.home_rv_kelas)
    RecyclerView rv_list_kelas;
    HomePresenter homePresenter;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        setupRecycler();
        homePresenter = new HomePresenter(this);
        homePresenter.displasyAllKelas(this);

        kelasMutableLiveData.observe(this, new Observer<List<Kelas>>() {
            @Override
            public void onChanged(@Nullable List<Kelas> kelas) {
                HomeListAdapter homeListAdapter = new HomeListAdapter(kelas, new RecyclerOnClick<HomeSharedView>() {
                    @Override
                    public void response(HomeSharedView data, @Nullable HomeSharedView... args) {
//                        Log.d("response", "response: "+ViewCompat.getTransitionName(data.kelas_image_view));
                        Intent intent = new Intent(getActivity(),DetailKelasActivity.class);
                        intent.putExtra("home_detail_data",data);
                        intent.putExtra("home_detail_transition",ViewCompat.getTransitionName(data.kelas_image_view));
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                getActivity(),data.kelas_image_view,ViewCompat.getTransitionName(data.kelas_image_view)
                        );
                        startActivity(intent,options.toBundle());
                        DetailKelasActivity.sharedData.postValue(data);
//                        KelasFragment.detail_kelas.setValue(data.kelas);
                    }
                });
                rv_list_kelas.setAdapter(homeListAdapter);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    void setupRecycler(){
        rv_list_kelas.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onSuccess(Kelas kelas) {

    }

    @Override
    public void getAllKelas(List<Kelas> kelasList) {
        kelasMutableLiveData.postValue(kelasList);
    }

}
