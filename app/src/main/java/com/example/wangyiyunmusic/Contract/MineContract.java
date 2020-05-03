package com.example.wangyiyunmusic.Contract;

public interface MineContract {

    interface Prestener{
        void getData();
    }
    interface View{
        void onDataGotten(String s);
    }
}
