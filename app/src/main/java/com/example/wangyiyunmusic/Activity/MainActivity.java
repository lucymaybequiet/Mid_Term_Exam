package com.example.wangyiyunmusic.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wangyiyunmusic.Prestener.LibraryPrestener;
import com.example.wangyiyunmusic.Prestener.MinePrestener;
import com.example.wangyiyunmusic.R;

public class MainActivity extends BaseActivity {

    Button btnForget;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnForget = findViewById(R.id.btn_forget);
        btnForget.setBackgroundColor(0);
        login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
                Dialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setTitle("请稍后");
                dialog.show();
            }
        });
    }
}
