package com.example.wangyiyunmusic.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wangyiyunmusic.Class.Library;
import com.example.wangyiyunmusic.R;
import com.example.wangyiyunmusic.Utils.HttpToMap;

import java.util.List;

public class MyLibraryAdapter extends RecyclerView.Adapter<MyLibraryAdapter.ViewHolder> {

    private List<Library> libraries;
    private ButtonInterface buttonInterface;

    public MyLibraryAdapter(List<Library> libraries) {
        this.libraries = libraries;
    }

    public void buttonSetOnClick(ButtonInterface buttonInterface){
        this.buttonInterface=buttonInterface;
    }
    public interface ButtonInterface{
        public void onclick(View view,int p);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        ImageView image;
        TextView name;
        TextView creatorName;
        TextView detail;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            image = itemView.findViewById(R.id.btn_rv_image);
            name = itemView.findViewById(R.id.tv_rv_library_name);
            creatorName = itemView.findViewById(R.id.tv_rv_singer_name);
            detail = itemView.findViewById(R.id.tv_rv_songcount_year);
            button = itemView.findViewById(R.id.btn_rv_play);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_library,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Library library = libraries.get(position);
        holder.name.setText(library.getLibraryName());
        holder.creatorName.setText(library.getSinger());
        holder.detail.setText(library.getSongCount()+"  Tracks");
        HttpToMap.getBitmapForHttp(library.getUrl(),library);
        holder.image.setImageBitmap(library.getMap());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonInterface!=null){
                    buttonInterface.onclick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return libraries.size();
    }

}
