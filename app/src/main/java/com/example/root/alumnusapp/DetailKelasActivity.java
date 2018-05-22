package com.example.root.alumnusapp;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.alumnusapp.data.model.Kelas;
import com.example.root.alumnusapp.screens.home.HomeSharedView;
import com.example.root.alumnusapp.util.ImagePicker;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailKelasActivity extends AppCompatActivity {

    @BindView(R.id.rv_detail_photos)
    RecyclerView rv_detail_photos;
    @BindView(R.id.detail_kelas_sampul)
    ImageView detail_kelas_sampul;
    @BindView(R.id.detail_kelas_motto)
    TextView detail_kelas_motto;
    @BindView(R.id.detail_kelas_edit_sampul)
    FloatingActionButton detail_kelas_edit_sampul;
    ImagePicker imagePicker;
    public static MutableLiveData<HomeSharedView> sharedData = new MutableLiveData<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kelas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
//        supportPostponeEnterTransition();
        imagePicker = new ImagePicker(this);
        Bundle extras = getIntent().getExtras();
        String imageTransition = extras.getString("home_detail_transition");
        detail_kelas_sampul.setTransitionName(imageTransition);
//        HomeSharedView sharedData = extras.getParcelable("home_detail_data");
        sharedData.observe(this, new Observer<HomeSharedView>() {
            @Override
            public void onChanged(@Nullable final HomeSharedView homeSharedView) {
                Picasso.get().load(homeSharedView.kelas.getSampul()).noFade().fit().centerCrop().into(detail_kelas_sampul, new Callback() {
                    @Override
                    public void onSuccess() {
                        detail_kelas_sampul.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                            @Override
                            public boolean onPreDraw() {
                                detail_kelas_sampul.getViewTreeObserver().removeOnPreDrawListener(this);
                                getSupportActionBar().setTitle(homeSharedView.kelas.getNama());
                                detail_kelas_motto.setText(homeSharedView.kelas.getMotto());
//                                startPostponedEnterTransition();
                                return true;
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                    }
                });
            }

        });
    }
    int CONST_PICK_IMAGE = 56;
    @OnClick(R.id.detail_kelas_edit_sampul)
    public void editSampul(){
        imagePicker.PickImage(CONST_PICK_IMAGE);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            imagePicker.OnActivityResult(requestCode,resultCode,data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
