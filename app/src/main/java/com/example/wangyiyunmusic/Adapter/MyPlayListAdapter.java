package com.example.wangyiyunmusic.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wangyiyunmusic.Class.Library;
import com.example.wangyiyunmusic.Class.Song;
import com.example.wangyiyunmusic.R;
import com.example.wangyiyunmusic.Utils.HttpToMap;

import java.util.List;

public class MyPlayListAdapter extends RecyclerView.Adapter<MyPlayListAdapter.ViewHolder> {

    private List<Song> songs;
    int time;

    public MyPlayListAdapter(List<Song> songs) {
        this.songs = songs;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView name;
        TextView count;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.tv_rv_song_name);
            count = itemView.findViewById(R.id.tv_rv_song_count);
            time = itemView.findViewById(R.id.tv_rv_song_time);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_songs, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.name.setText(song.getName());
        holder.count.setText(String.valueOf(position + 1));
        time = song.getTime();
        holder.time.setText(timeToString(time));
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    private String timeToString(int t) {
        int time = t / 1000;
        int m = time / 60;
        int s = time % 60;
        String a;
        if (s < 10) {
            a = m + ":0" + s;
        } else {
            a = m + ":" + s;
        }
        return a;
    }
}
