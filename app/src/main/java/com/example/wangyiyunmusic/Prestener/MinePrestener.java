package com.example.wangyiyunmusic.Prestener;

import android.util.Log;

import com.example.wangyiyunmusic.Contract.MineContract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MinePrestener implements MineContract.Prestener {

    MineContract.View view;

    public MinePrestener(MineContract.View view) {
        this.view = view;
    }

    @Override
    public void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://47.99.165.194/user/detail?uid=318082831");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder res = new StringBuilder();
                    String line;
                    while ((line = reader.readLine())!=null){
                        res.append(line);
                    }
                   getit(res);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (reader!=null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection!=null){
                        connection.disconnect();
                    }
                }

            }
        }).start();

    }
    private void getit(StringBuilder stringBuilder){
        view.onDataGotten(stringBuilder.toString());
        Log.d("=========","got it");
    }
}
