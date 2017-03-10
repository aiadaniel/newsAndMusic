package com.hotchpotch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hotchpotch.adapter.MainViewPagerAdapter;
import com.hotchpotch.fragment.MusicFragment;
import com.hotchpotch.fragment.NewsFragment;
import com.hotchpotch.fragment.NovelFragment;
import com.hotchpotch.widget.NestedViewPager;

public class MainActivity extends AppCompatActivity {

    NestedViewPager mNestedViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNestedViewPager = (NestedViewPager) findViewById(R.id.main_viewpager);
        MusicFragment musicFragment = new MusicFragment();
        NewsFragment newsFragment = new NewsFragment();
        NovelFragment novelFragment = new NovelFragment();
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(musicFragment);
        mainViewPagerAdapter.addFragment(newsFragment);
        mainViewPagerAdapter.addFragment(novelFragment);
        mNestedViewPager.setAdapter(mainViewPagerAdapter);

//        UpdateDialog dialog = new UpdateDialog(this,R.style.dialog_base);
//        dialog.show();

    }

}
