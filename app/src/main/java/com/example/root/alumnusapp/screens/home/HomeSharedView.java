package com.example.root.alumnusapp.screens.home;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.example.root.alumnusapp.data.model.Kelas;

public class HomeSharedView implements Parcelable {
    public ImageView kelas_image_view;
    public Kelas kelas;

    public HomeSharedView(ImageView kelas_image_view, Kelas kelas) {
        this.kelas_image_view = kelas_image_view;
        this.kelas = kelas;
    }

    protected HomeSharedView(Parcel in) {
    }

    public static final Creator<HomeSharedView> CREATOR = new Creator<HomeSharedView>() {
        @Override
        public HomeSharedView createFromParcel(Parcel in) {
            return new HomeSharedView(in);
        }

        @Override
        public HomeSharedView[] newArray(int size) {
            return new HomeSharedView[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
