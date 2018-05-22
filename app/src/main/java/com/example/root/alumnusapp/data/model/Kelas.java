package com.example.root.alumnusapp.data.model;

import android.widget.ImageView;

import java.util.List;

public class Kelas {
    public String uid;
    public String nama;
    public String sampul;
    public String motto;
    public ImageView cache_sampul;
    public List<Foto> foto_kelas;
    public Kelas() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String UID) {
        this.uid = UID;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSampul() {
        return sampul;
    }

    public void setSampul(String sampul) {
        this.sampul = sampul;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public ImageView getCache_sampul() {
        return cache_sampul;
    }

    public void setCache_sampul(ImageView cache_sampul) {
        this.cache_sampul = cache_sampul;
    }

    public List<Foto> getFoto_kelas() {
        return foto_kelas;
    }

    public void setFoto_kelas(List<Foto> foto_kelas) {
        this.foto_kelas = foto_kelas;
    }
}
