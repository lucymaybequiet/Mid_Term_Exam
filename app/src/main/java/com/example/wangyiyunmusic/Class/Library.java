package com.example.wangyiyunmusic.Class;

import android.graphics.Bitmap;

public class Library {

    private long id;
    private String singer;
    private String libraryName;
    private int songCount;
    private String url;
    private Bitmap map;

    public Library(long id, String singer, String libraryName, int songCount, String url) {
        this.id = id;
        this.singer = singer;
        this.libraryName = libraryName;
        this.songCount = songCount;
        this.url = url;
    }

    public Library() {
    }

    public Library(long id, String libraryName, int songCount, String url) {
        this.id = id;
        this.libraryName = libraryName;
        this.songCount = songCount;
        this.url = url;
    }

    public Library(long id, String singer, String libraryName, int songCount, String url, Bitmap map) {
        this.id = id;
        this.singer = singer;
        this.libraryName = libraryName;
        this.songCount = songCount;
        this.url = url;
        this.map = map;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public int getSongCount() {
        return songCount;
    }

    public void setSongCount(int songCount) {
        this.songCount = songCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getMap() {
        return map;
    }

    public void setMap(Bitmap map) {
        this.map = map;
    }
}
