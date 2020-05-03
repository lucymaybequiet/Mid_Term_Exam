package com.example.wangyiyunmusic.Prestener;

import android.util.Log;

import com.example.wangyiyunmusic.Class.Library;
import com.example.wangyiyunmusic.Contract.HomeContract;
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

public class AlbumPrestener implements HomeContract.Prestener {

    HomeContract.View view;

    public AlbumPrestener(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void getBanner() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://47.99.165.194/album/newest");
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

    @Override
    public void getData() {


    }

    private void getit(StringBuilder string) {
        try {
            JSONObject jsonObject = new JSONObject(string.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("albums");
            List<Library> libraries = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                long id = jsonObject.getLong("id");
                int songcount = jsonObject.getInt("size");
                String url = jsonObject.getString("picUrl");
                Library library = new Library(id,name,songcount,url);
                libraries.add(library);
                Log.d("---------", library.getLibraryName());
            }
            view.onBannerGotten(libraries);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
