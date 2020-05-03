package com.example.wangyiyunmusic.Contract;

public interface HomeContract {

    interface Prestener{
        void getBanner();
        void getData();
    }
    interface View{
        void onDataGotten(String s);
        void onBannerGotten(String s);
    }
}
