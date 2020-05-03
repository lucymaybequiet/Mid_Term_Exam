package com.example.wangyiyunmusic.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wangyiyunmusic.Activity.PlayListActivity;
import com.example.wangyiyunmusic.Class.Library;
import com.example.wangyiyunmusic.Adapter.MyLibraryAdapter;
import com.example.wangyiyunmusic.Contract.LibraryContract;
import com.example.wangyiyunmusic.Prestener.LibraryPrestener;
import com.example.wangyiyunmusic.R;

import java.util.List;

public class LibraryFragmengt extends Fragment implements LibraryContract.View {

    private LibraryPrestener prestener = new LibraryPrestener(this);
    private RecyclerView rv;
    private View view;
    private List<Library> libraries;
    private LinearLayoutManager layoutManager;
    private Dialog dialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_library,container,false);
        prestener.getData();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(".....","sleeping");
        init(view);
        return view;
    }



    @Override
    public void onDataGotten(List<Library> libraries) {
        this.libraries = libraries;
        Log.d(".........","chuanle");
    }

    private void init(View view){
        rv = view.findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        MyLibraryAdapter myLibraryAdapter = new MyLibraryAdapter(libraries);
        rv.setAdapter(myLibraryAdapter);
        myLibraryAdapter.buttonSetOnClick(new MyLibraryAdapter.ButtonInterface() {
            @Override
            public void onclick(View view, int p) {
                Library library = libraries.get(p);
                Intent intent = new Intent(getActivity(), PlayListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",library.getLibraryName());
                bundle.putString("creator",library.getSinger());
                bundle.putInt("n",library.getSongCount());
                bundle.putString("url",library.getUrl());
                bundle.putLong("id",library.getId());
                intent.putExtras(bundle);

                Log.d("2222222222",String.valueOf(library.getId()));
                startActivity(intent);
                dialog = new ProgressDialog(getActivity());
                dialog.setTitle("请稍后");
                dialog.show();
            }
        });

    }
    @Override
    public void onStop() {
        super.onStop();
        dialog.dismiss();
        Log.d("888888","我消失了");
    }

}
