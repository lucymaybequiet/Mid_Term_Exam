package com.example.wangyiyunmusic.Adapter;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wangyiyunmusic.Class.Library;
import com.example.wangyiyunmusic.R;
import com.example.wangyiyunmusic.Utils.HttpToMap;

import java.util.List;

public class MyAlbumAdapter extends RecyclerView.Adapter<MyAlbumAdapter.ViewHolder> {

    private List<Library> libraries;
   // private ButtonInterface buttonInterface;

    public MyAlbumAdapter(List<Library> libraries) {
        Log.d("7777777", libraries.get(1).getLibraryName());
        this.libraries = libraries;
    }

//    public void buttonSetOnClick(ButtonInterface buttonInterface){
//        this.buttonInterface=buttonInterface;
//    }
//    public interface ButtonInterface{
//        public void onclick(View view, int p);
//    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        RelativeLayout relativeLayout;
        TextView name;
        TextView detail;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            relativeLayout = itemView.findViewById(R.id.ll_rv_album);
            name = itemView.findViewById(R.id.tv_rv_album_name);
            detail = itemView.findViewById(R.id.tv_rv_album_songcount);
            button = itemView.findViewById(R.id.btn_rv_ablum_detail);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_albums,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Library library = libraries.get(position);
        holder.name.setText(library.getLibraryName());
        holder.detail.setText(library.getSongCount()+"  Tracks");
        HttpToMap.getBitmapForHttp(library.getUrl(),library);
        holder.relativeLayout.setBackground(new BitmapDrawable(library.getMap()));
//        holder.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (buttonInterface!=null){
//                    buttonInterface.onclick(v,position);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

}
