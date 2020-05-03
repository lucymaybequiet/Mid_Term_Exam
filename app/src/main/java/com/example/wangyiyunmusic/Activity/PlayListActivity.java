package com.example.wangyiyunmusic.Activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangyiyunmusic.Adapter.MyLibraryAdapter;
import com.example.wangyiyunmusic.Adapter.MyPlayListAdapter;
import com.example.wangyiyunmusic.Class.Library;
import com.example.wangyiyunmusic.Class.Song;
import com.example.wangyiyunmusic.R;
import com.example.wangyiyunmusic.Utils.HttpToMap;

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

public class PlayListActivity extends BaseActivity {

    private RecyclerView rv;
    private LinearLayoutManager layoutManager;
    private List<Song> songs;
    private TextView name,creator,count;
    ImageView image;
    private static final int COMPLETED = 0;
    private int songcount;
    private LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        name = findViewById(R.id.tv_rv_library_name);
        creator = findViewById(R.id.tv_rv_singer_name);
        count = findViewById(R.id.tv_rv_songcount_year);
        image = findViewById(R.id.btn_rv_image);
        layout = findViewById(R.id.ll_lib);
        layout.setBackgroundColor(0xFFF5F5F5);
        Button button = findViewById(R.id.btn_return);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        long id = bundle.getLong("id");
        name.setText(bundle.getString("name"));
        creator.setText(bundle.getString("creator"));
        songcount = bundle.getInt("n");
        count.setText(String.valueOf(songcount)+"  Tracks");
        getBitmapForHttp(bundle.getString("url"));
        getData(id);
        Log.d("111111", String.valueOf(id));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        rv = findViewById(R.id.rv_songs);
        layoutManager = new LinearLayoutManager(PlayListActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(new MyPlayListAdapter(songs));
    }


    private void getData(long id) {
        MyThread1 myThread1 = new MyThread1(id);
        myThread1.start();

    }
    public class MyThread1 extends Thread{
        private long id;
        public MyThread1(long id) {
            this.id = id;
        }
        public void run(){
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            String u = "http://47.99.165.194/playlist/detail?id="+id;
            Log.d("000000",u);
            try {
                URL url = new URL(u);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream in = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder res = new StringBuilder();
                String line;
                while ((line = reader.readLine())!=null){
                    res.append(line);
                }
                getit(res,songcount);
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
    }

    private void getit(StringBuilder string,int n) {
        Log.d("---------",string.toString());
        try {
            JSONObject jsonObject = new JSONObject(string.toString());
            jsonObject = jsonObject.getJSONObject("playlist");
            JSONArray jsonArray = jsonObject.getJSONArray("tracks");
            songs = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                int id = jsonObject.getInt("id");
                int time = jsonObject.getInt("dt");
                Song song = new Song(name,id,time);
                songs.add(song);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getBitmapForHttp(final String httpUrl) {
        new Thread(new Runnable() {
            String url = httpUrl;
            @Override
            public void run() {
                InputStream input = null;
                try {
                    URL url = new URL(httpUrl);
                    input = url.openStream();
                    Bitmap map = BitmapFactory.decodeStream(input);
                    Message message = new Message();
                    message.what = COMPLETED;
                    handler.sendMessage(handler.obtainMessage(message.what,map));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }
        }).start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == COMPLETED){
                Log.d("==========","jiazai");
                image.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };
}
