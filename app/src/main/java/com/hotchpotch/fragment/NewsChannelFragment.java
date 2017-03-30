package com.hotchpotch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hotchpotch.R;
import com.hotchpotch.adapter.NewsListAdapter;
import com.hotchpotch.entity.NewsEntity;
import com.hotchpotch.utils.OkUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by lxm.
 */

public class NewsChannelFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    private static final String tag = "11111";

    public static final String NEWS_ID = "newsid";
    public static final String NEWS_TYPE = "newstype";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private NewsListAdapter mNewsListAdapter;

    private String mChannelId;
    private String mChannelType;
    private int mStartPage = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mChannelId = getArguments().getString(NEWS_ID);
            mChannelType = getArguments().getString(NEWS_TYPE);
            Log.d(tag,"NewsChannelFragment get argument " + mChannelId + " " + mChannelType);
        }
    }

    @Override
    public void loadData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(tag,"NewsChannelFragment onCreateView " + this);
        View rootview = inflater.inflate(R.layout.fragment_news_list,container,false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootview.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.rv_news);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerListener(linearLayoutManager) {
            @Override
           public void onLoadMore(int currentPage) {
                Log.d(tag,"onLoadMore page " + currentPage);
                mStartPage = currentPage;
                getNewsList();
            }
        });
        mNewsListAdapter = new NewsListAdapter();
        mRecyclerView.setAdapter(mNewsListAdapter);
        getNewsList();
        return rootview;
    }

    private void getNewsList() {
        OkUtils.getNewsAsync(mChannelId,mChannelType,mStartPage,new OkUtils.OnNewsData() {
            @Override
            public void onNewsDataRes(int res, final Map<String,ArrayList<NewsEntity>> entities) {
                if (res == OkUtils.RES_SUCCESS) {
                    Log.d(tag,"on newsdata size " + entities.size());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mNewsListAdapter.setData(entities.get(mChannelId));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        //下拉刷新，头部添加数据。目前可以不用，最主要是上拉加载更多
        mSwipeRefreshLayout.setRefreshing(false);
    }

    abstract class EndlessRecyclerListener extends  RecyclerView.OnScrollListener {
        private int previousTotal = 0;
        private boolean loading = true;
        int firstVisibleItem, visibleItemCount, totalItemCount;

        private int currentPage = 1;

        private LinearLayoutManager mLinearLayoutManager;

        public EndlessRecyclerListener(LinearLayoutManager linearLayoutManager) {
            this.mLinearLayoutManager = linearLayoutManager;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            visibleItemCount = recyclerView.getChildCount();
            totalItemCount = mLinearLayoutManager.getItemCount();
            firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }
            if (!loading
                    && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
                currentPage++;
                onLoadMore(currentPage);
                loading = true;
            }
        }

        public abstract void onLoadMore(int currentPage);
    }
}
