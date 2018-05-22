package com.example.root.alumnusapp.screens;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.alumnusapp.R;
import com.example.root.alumnusapp.data.local.AppDatabase;
import com.example.root.alumnusapp.data.model.Kelas;
import com.example.root.alumnusapp.data.remote.KelasRemoteSource;
import com.example.root.alumnusapp.screens.Callback.KelasCallback;
import com.example.root.alumnusapp.screens.post.PostImagePresenter;
import com.example.root.alumnusapp.util.ImagePicker;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostImageKelasActivity extends AppCompatActivity implements KelasCallback {
    @BindView(R.id.post_image)
    ImageView imageView;
    ImagePicker imagePicker;
    @BindView(R.id.post_caption)
    TextView post_caption;
    @BindView(R.id.post_submit)
    FloatingActionButton post_submit;
    public static MutableLiveData<File> user_image_file = new MutableLiveData();
    PostImagePresenter postImagePresenter  = new PostImagePresenter(this);
    KelasRemoteSource kelasRemoteSource = new KelasRemoteSource(FirebaseFirestore.getInstance(),this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_image_kelas);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        user_image_file.observe(this, new Observer<File>() {
            @Override
            public void onChanged(@Nullable final File file) {
                Log.d("have image", "onChanged: "+file.getName());
                Picasso.get().load(file).into(imageView);
                post_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        kelasRemoteSource.uploadImageKelas(AppDatabase.get(PostImageKelasActivity.this).userDao().getUser().getKelasId(),file, FirebaseStorage.getInstance());
                    }
                });
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
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

    @Override
    public void onSuccess(Kelas kelas) {

    }

    @Override
    public void getAllKelas(List<Kelas> kelasList) {

    }
}
