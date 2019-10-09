package com.example.weibo5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.icu.util.Measure;
import android.os.Bundle;

import com.example.weibo5.Adapter.PenAdapter;
import com.example.weibo5.Fragment.FindFragment;
import com.example.weibo5.Fragment.HomeFragment;
import com.example.weibo5.Fragment.LetterFragment;
import com.example.weibo5.Fragment.MeFragment;
import com.example.weibo5.Fragment.PenFragment;
import com.example.weibo5.Tools.MyAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class MainPage extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> fragments;
    List<String> name;
    List<Integer> pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        initTab();
        initFragment();
        viewPager.setAdapter(new PenAdapter(getSupportFragmentManager(),fragments,name,pic));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new FindFragment());
        fragments.add(new PenFragment());
        fragments.add(new LetterFragment());
        fragments.add(new MeFragment());
    }




    private void initTab() {
        name = new ArrayList<>();
        pic = new ArrayList<>();
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewPager);

        name.add(getResources().getString(R.string.home));
        name.add(getResources().getString(R.string.find));
        name.add(getResources().getString(R.string.pen));
        name.add(getResources().getString(R.string.letter));
        name.add(getResources().getString(R.string.me));


        pic.add(R.mipmap.icon_home);
        pic.add(R.mipmap.icon_find);
        pic.add(R.mipmap.icon_pen);
        pic.add(R.mipmap.icon_letter);
        pic.add(R.mipmap.icon_me);

        for (int i = 0;i < 5;i++){
            String na = name.get(i);
            int mipmap = pic.get(i);
            tabLayout.addTab(tabLayout.newTab().setText(na).setIcon(mipmap));
        }
    }
}
