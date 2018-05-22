package com.example.root.alumnusapp.screens.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.root.alumnusapp.R;
import com.example.root.alumnusapp.data.local.AppDatabase;
import com.example.root.alumnusapp.data.model.User;
import com.example.root.alumnusapp.data.remote.StorageRemoteSource;
import com.example.root.alumnusapp.screens.PostImageKelasActivity;
import com.example.root.alumnusapp.screens.home.HomeFragment;
import com.example.root.alumnusapp.screens.post.PostImagePresenter;
import com.example.root.alumnusapp.util.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImagePicker imagePicker = new ImagePicker(this);
    PostImagePresenter postImagePresenter = new PostImagePresenter(this);
    int resultCode = 55;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,new HomeFragment()).addToBackStack(null).commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        User user = new User();
//        user.setUid("asd");
//        user.setNama("asdw");
//        AppDatabase.get(this).userDao().insert(new User());
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_post:
                    postImagePresenter.onCreate(imagePicker,resultCode);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if(imagePicker != null){
                imagePicker.OnActivityResult(requestCode,resultCode,data);
                if(imagePicker.getCurrentImage() != null){
                    postImagePresenter.getImageFromPicker(imagePicker.getCurrentImage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
