package com.example.root.alumnusapp.screens.register.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.root.alumnusapp.data.model.Kelas;

import java.util.ArrayList;
import java.util.List;

public class ChooseKelasAdapter extends ArrayAdapter<Kelas> {
    List<Kelas> allKelas;
    public ChooseKelasAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        allKelas = new ArrayList<>();
    }
    public void bind(List<Kelas> kelas){
        this.allKelas = kelas;
    }
    @Override
    public int getCount() {
        return allKelas.size();
    }

    @Nullable
    @Override
    public Kelas getItem(int position) {
        return allKelas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label_spiner = (TextView) super.getView(position, convertView, parent);
        label_spiner.setTextColor(Color.BLACK);
        label_spiner.setText(allKelas.get(position).getNama());
        return label_spiner;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label_spiner = (TextView) super.getView(position, convertView, parent);
        label_spiner.setTextColor(Color.BLACK);
        label_spiner.setText(allKelas.get(position).getNama());
        return label_spiner;
    }
}
