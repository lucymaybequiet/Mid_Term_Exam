package com.example.wangyiyunmusic.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.wangyiyunmusic.Adapter.AdapterFragmengt;
import com.example.wangyiyunmusic.Fragment.HomeFragmengt;
import com.example.wangyiyunmusic.Fragment.LibraryFragmengt;
import com.example.wangyiyunmusic.Fragment.MineFragmengt;
import com.example.wangyiyunmusic.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private ViewPager vp;
    private List<Fragment> fragments;
    private FragmentManager manager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tabLayout = findViewById(R.id.tb);
        vp = findViewById(R.id.vp);

        manager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        fragments.add(new HomeFragmengt());
        fragments.add(new LibraryFragmengt());
        fragments.add(new MineFragmengt());
        vp.setAdapter(new AdapterFragmengt(manager,fragments));
        vp.setCurrentItem(0);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setupWithViewPager(vp);
        tabLayout.getTabAt(0).setIcon(R.mipmap.flame);
        tabLayout.getTabAt(1).setIcon(R.mipmap.library);
        tabLayout.getTabAt(2).setIcon(R.mipmap.account);

    }
}
