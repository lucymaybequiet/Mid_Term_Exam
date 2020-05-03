package com.example.wangyiyunmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wangyiyunmusic.Activity.PlayListActivity;
import com.example.wangyiyunmusic.Adapter.MyAlbumAdapter;
import com.example.wangyiyunmusic.Adapter.MyLibraryAdapter;
import com.example.wangyiyunmusic.Class.Library;
import com.example.wangyiyunmusic.Contract.HomeContract;
import com.example.wangyiyunmusic.Prestener.AlbumPrestener;
import com.example.wangyiyunmusic.R;

import java.util.List;

public class HomeFragmengt extends Fragment implements HomeContract.View {

    private AlbumPrestener prestener = new AlbumPrestener(this);
    private View view;
    private RecyclerView rv;
    private List<Library> libraries;
    private LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        prestener.getBanner();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(".....33", "sleeping");
        init(view);
        return view;
    }

    @Override
    public void onDataGotten(List<Library> libraries) {

    }

    @Override
    public void onBannerGotten(List<Library> libraries) {
        Log.d("---------gotit", libraries.get(0).getLibraryName());
        this.libraries = libraries;
    }

    private void init(View view) {
        rv = view.findViewById(R.id.rv_h);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(layoutManager);
        MyAlbumAdapter myAlbumAdapter = new MyAlbumAdapter(libraries);
        rv.setAdapter(myAlbumAdapter);
//        myAlbumAdapter.buttonSetOnClick(new MyAlbumAdapter.ButtonInterface() {
//            @Override
//            public void onclick(View view, int p) {
//                Library library = libraries.get(p);
//                Intent intent = new Intent(getActivity(), PlayListActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("name", library.getLibraryName());
//                bundle.putString("creator", library.getSinger());
//                bundle.putInt("n", library.getSongCount());
//                bundle.putString("url", library.getUrl());
//                bundle.putLong("id", library.getId());
//                intent.putExtras(bundle);
//
//                Log.d("2222222222", String.valueOf(library.getId()));
//                startActivity(intent);
//            }
//        });
    }
}
