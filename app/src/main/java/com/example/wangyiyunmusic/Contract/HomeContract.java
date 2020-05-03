package com.example.wangyiyunmusic.Contract;

import com.example.wangyiyunmusic.Class.Library;

import java.util.List;

public interface HomeContract {

    interface Prestener{
        void getBanner();
        void getData();
    }
    interface View{
        void onDataGotten(List<Library> libraries);
        void onBannerGotten(List<Library> libraries);
    }
}
