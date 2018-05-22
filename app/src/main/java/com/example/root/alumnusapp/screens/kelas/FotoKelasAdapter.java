package com.example.root.alumnusapp.screens.kelas;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class FotoKelasAdapter extends RecyclerView.Adapter<FotoKelasAdapter.FotoKelasHolder> {

    String[] image_list;
    public FotoKelasAdapter(String[] image_list){
        this.image_list = image_list;
    }
    @NonNull
    @Override
    public FotoKelasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FotoKelasHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return image_list.length;
    }

    class FotoKelasHolder extends RecyclerView.ViewHolder {

        public FotoKelasHolder(View itemView) {
            super(itemView);
        }
    }
}
