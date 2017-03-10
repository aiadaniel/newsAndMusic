package com.hotchpotch.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotchpotch.R;
import com.hotchpotch.utils.ChannelUtils;
import com.hotchpotch.widget.FlowLayout;

import java.util.ArrayList;

/**
 * Created by lxm.
 */

public class ChannelManagerActivity extends Activity {
    //todo 实现点击拖拽等事件

    //鉴于目前测试动态添加view无效，先用其他方法
    FlowLayout mFlowLayout;
    FlowLayout mFlowLayoutMore;

//    RecyclerView mRecyclerView;
//    RecyclerView mRecyclerViewMore;
//    LabelAdapter mLabelAdapter;
//    LabelAdapter mLabelAdapterMore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_mgr);
        mFlowLayout = (FlowLayout) findViewById(R.id.flowlayout);
        mFlowLayoutMore = (FlowLayout) findViewById(R.id.flowlayout_more);
//        mRecyclerView = (RecyclerView) findViewById(R.id.flowlayout);
//        mRecyclerViewMore = (RecyclerView) findViewById(R.id.flowlayout_more);
//
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerViewMore.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
//
//        mLabelAdapter = new LabelAdapter();
//        mLabelAdapterMore = new LabelAdapter();
//        mRecyclerView.setAdapter(mLabelAdapter);
//        mRecyclerViewMore.setAdapter(mLabelAdapterMore);

        ArrayList<String> allChannel = new ArrayList<>();
        ArrayList<String> myChannel =  new ArrayList<>();
        ArrayList<String> moreChannel = new ArrayList<>();
        ChannelUtils.getNewsChannel(allChannel);
        myChannel.addAll(allChannel.subList(0,4));
        moreChannel.addAll(allChannel.subList(4,allChannel.size()-1));

        LayoutInflater inflater = LayoutInflater.from(this);
        for (String channel : myChannel) {
            TextView tv = (TextView) inflater.inflate(R.layout.channel_label,mFlowLayout,false);
            //tv.setTextAppearance(this,R.style.news_channel_sort_title);
            tv.setText(channel);
            mFlowLayout.addView(tv);
        }
        for (String channel : moreChannel) {
            TextView tv = (TextView) inflater.inflate(R.layout.channel_label,mFlowLayoutMore,false);
            //tv.setTextAppearance(this,R.style.news_channel_sort_title);
            tv.setText(channel);
            mFlowLayoutMore.addView(tv);
        }
//        mLabelAdapter.setData(myChannel);
//        mLabelAdapterMore.setData(moreChannel);
    }

    static class LabelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private ArrayList<String> labels = new ArrayList<>();

        public void setData(ArrayList<String> datas) {
            labels = datas;
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_label,parent,false);
            ItemViewholder viewholder = new ItemViewholder(rootView);
            return viewholder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            setValues((ItemViewholder) holder,position);
        }

        private void setValues(ItemViewholder viewholder,int position) {
            viewholder.mTv.setText(labels.get(position));
        }

        @Override
        public int getItemCount() {
            return labels.size();
        }

        static class ItemViewholder extends RecyclerView.ViewHolder {
            TextView mTv;
            public ItemViewholder(View view) {
                super(view);
                mTv = (TextView) view.findViewById(R.id.tv_label);
            }
        }
    }
}
