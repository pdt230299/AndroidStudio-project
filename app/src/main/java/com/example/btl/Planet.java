package com.example.btl;

import java.io.Serializable;

public class Planet implements Serializable {
    private String name;
    private String url;
    private String mota;

    public Planet(){

    }

    public Planet(String name, String url, String mota) {
        this.name = name;
        this.url = url;
        this.mota = mota;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
