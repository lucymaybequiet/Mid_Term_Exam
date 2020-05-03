package com.example.wangyiyunmusic.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.wangyiyunmusic.Class.Library;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class HttpToMap {

    public static void getBitmapForHttp(final String httpUrl , final Library library) {
        final List<Bitmap> bitmaps = null;
        new Thread(new Runnable() {
            String url = httpUrl;
            @Override
            public void run() {
                InputStream input = null;
                try {
                    URL url = new URL(httpUrl);
                    input = url.openStream();
                    Bitmap map = BitmapFactory.decodeStream(input);
                    library.setMap(map);
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

}
