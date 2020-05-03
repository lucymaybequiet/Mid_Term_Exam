package com.example.wangyiyunmusic.Prestener;

import android.util.Log;

import com.example.wangyiyunmusic.Class.Library;
import com.example.wangyiyunmusic.Contract.LibraryContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LibraryPrestener implements LibraryContract.Prestener {

    LibraryContract.View view;

    public LibraryPrestener(LibraryContract.View view) {
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
                    URL url = new URL("http://47.99.165.194/user/playlist?uid=318082831");
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
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }

            }
        }).start();

    }

    private void getit(StringBuilder string) {
        try {
            JSONObject jsonObject = new JSONObject(string.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("playlist");
            List<Library> libraries = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                long id = jsonObject.getInt("id");
                Log.d("44444444"+i, String.valueOf(id));
                int songcount = jsonObject.getInt("trackCount");
                String url = jsonObject.getString("coverImgUrl");
                jsonObject = jsonObject.getJSONObject("creator");
                String creatorName = jsonObject.getString("nickname");
                Library library = new Library(id,creatorName,name,songcount,url);
                libraries.add(library);
                Log.d("333333333", String.valueOf(library.getId()));
            }
            view.onDataGotten(libraries);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
