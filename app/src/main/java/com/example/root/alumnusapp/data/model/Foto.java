package com.example.root.alumnusapp.data.model;

public class Foto {
    String url;
    String motto;

    public Foto(String url, String motto) {
        this.url = url;
        this.motto = motto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }
}
