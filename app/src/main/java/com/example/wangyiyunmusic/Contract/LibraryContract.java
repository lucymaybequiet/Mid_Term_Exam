package com.example.wangyiyunmusic.Contract;

import com.example.wangyiyunmusic.Class.Library;

import java.util.List;

public interface LibraryContract {

    interface Prestener{
        void getData();
    }
    interface View{
        void onDataGotten(List<Library> libraries);
    }
}
