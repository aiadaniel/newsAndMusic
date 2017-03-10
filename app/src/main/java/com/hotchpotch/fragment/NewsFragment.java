package com.hotchpotch.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hotchpotch.R;
import com.hotchpotch.activities.ChannelManagerActivity;
import com.hotchpotch.utils.ChannelUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxm.
 */

public class NewsFragment extends Fragment implements View.OnClickListener{

    private static final String tag = "11111";

    ViewPager mViewPager;
    TabLayout mTablayout;
    ImageView mIvAdd;

    ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(tag,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        mTablayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mIvAdd = (ImageView) view.findViewById(R.id.iv_add_channel);
        mIvAdd.setOnClickListener(this);

        mViewPager = (ViewPager) view.findViewById(R.id.news_viewpager);

        ArrayList<String> channels = new ArrayList<String>();
        ArrayList<String> channelIds = new ArrayList<String>();
        ChannelUtils.getNewsChannel(channels,channelIds);
        createNewFragment(channels,channelIds);

        NewsAdapter newsAdapter = new NewsAdapter(getChildFragmentManager());
        newsAdapter.setFragments(mFragments,channels);
        mViewPager.setAdapter(newsAdapter);

        mTablayout.setupWithViewPager(mViewPager);
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        return view;
    }

    private void createNewFragment(List<String> channels,List<String> ids) {
        if (channels.size() == 0 || ids.size() == 0)
            return;
        for (int i = 0;i < 5; i++) {//先定5个
            NewsChannelFragment fragment = new NewsChannelFragment();
            Bundle bundle = new Bundle();
            bundle.putString(NewsChannelFragment.NEWS_ID, ids.get(i));
            bundle.putString(NewsChannelFragment.NEWS_TYPE,ChannelUtils.getChannelType(ids.get(i)));
            fragment.setArguments(bundle);
            mFragments.add(fragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add_channel:
                Intent i = new Intent(getActivity(), ChannelManagerActivity.class);
                startActivity(i);
                break;
            default:break;
        }
    }

    class NewsAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
        private ArrayList<String> mTitles = new ArrayList<String>();

        public NewsAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment f,String title) {
            mFragments.add(f);
            mTitles.add(title);
        }

        public void setFragments(ArrayList<Fragment> fs,ArrayList<String> titles) {
            mFragments = fs;
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }
}
