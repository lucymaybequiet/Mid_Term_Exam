package com.example.wangyiyunmusic.Fragment;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wangyiyunmusic.Contract.MineContract;
import com.example.wangyiyunmusic.Prestener.MinePrestener;
import com.example.wangyiyunmusic.R;
import com.example.wangyiyunmusic.Utils.YoubianToCity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MineFragmengt extends Fragment implements MineContract.View {

    private static final String uid = "318082831";
    private MinePrestener prestener = new MinePrestener(this);
    private TextView tvName;
    private TextView tvCity, tvFollower, tvPosts, tvFollowing;
    private ImageView image;
    private static final int COMPLETED = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        prestener.getData();
    }

    @Override
    public void onDataGotten(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            Log.d("=======", jsonObject.toString());
            jsonObject = jsonObject.getJSONObject("profile");
            Log.d("=======", jsonObject.toString());
            String name = jsonObject.getString("nickname");
            Log.d("=======", name);
            int youbian = jsonObject.getInt("province");
            Log.d("=======", String.valueOf(youbian));
            int followeds = jsonObject.getInt("followeds");
            int events = jsonObject.getInt("eventCount");
            int follows = jsonObject.getInt("follows");
            String httpUrl = jsonObject.getString("avatarUrl");
            Log.d("=======", httpUrl);
            //更新UI
            tvName.setText(name);
            tvCity.setText(YoubianToCity.getCity(youbian));
            tvFollower.setText(String.valueOf(followeds));
            tvPosts.setText(String.valueOf(events));
            tvFollowing.setText(String.valueOf(follows));
            getBitmapForHttp(httpUrl);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initView(View view) {
        tvName = view.findViewById(R.id.tv_mine_name);
        tvCity = view.findViewById(R.id.tv_mine_location);
        tvFollower = view.findViewById(R.id.tv_mine_followers);
        tvPosts = view.findViewById(R.id.tv_mine_Posts);
        tvFollowing = view.findViewById(R.id.tv_mine_following);
        image = view.findViewById(R.id.image_mine_bg);
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
