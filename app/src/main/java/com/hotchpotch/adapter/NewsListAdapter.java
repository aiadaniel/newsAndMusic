package com.hotchpotch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hotchpotch.R;
import com.hotchpotch.entity.NewsEntity;

import java.util.ArrayList;

/**
 * Created by lxm.
 */

public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<NewsEntity> mNewsEntities = new ArrayList<NewsEntity>();

    public NewsListAdapter() {

    }

    public void setData(ArrayList<NewsEntity> entities) {
        mNewsEntities = entities;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        ItemViewHolder holder = new ItemViewHolder(rootView);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mNewsEntities.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setView((ItemViewHolder) holder,position);
    }

    private void setView(ItemViewHolder holder,int position) {
        NewsEntity entity = mNewsEntities.get(position);
        if (entity != null) {
            holder.mTvNewsTitle.setText(entity.getLtitle()==null ? entity.getTitle():entity.getLtitle());
            holder.mTvNewsDigest.setText(entity.getDigest());
            holder.mTvNewsPtime.setText(entity.getPtime());
            holder.mIvNewsPhoto.setImageURI(entity.getImgsrc());
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView mIvNewsPhoto;
        TextView mTvNewsTitle;
        TextView mTvNewsDigest;
        TextView mTvNewsPtime;

        public ItemViewHolder(View view) {
            super(view);
            mIvNewsPhoto = (SimpleDraweeView) view.findViewById(R.id.iv_news_summary_photo);
            mTvNewsTitle = (TextView) view.findViewById(R.id.tv_news_summary_title);
            mTvNewsDigest = (TextView) view.findViewById(R.id.tv_news_summary_digest);
            mTvNewsPtime = (TextView) view.findViewById(R.id.tv_news_summary_ptime);
        }
    }
}
