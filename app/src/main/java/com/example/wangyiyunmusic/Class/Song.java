package com.example.wangyiyunmusic.Class;

public class Song {
    String name;
    int id;
    int time;

    public Song(String name, int id, int time) {
        this.name = name;
        this.id = id;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
