package com.hotchpotch.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hotchpotch.entity.NewsEntity;
import com.hotchpotch.entity.NovelEntity;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lxm.
 */

public class OkUtils {
    private static final String tag = "11111";
    public static final String URL_SEPARATOR = "/";
    public static final String URL_QUERY = "?";
    public static final String URL_AND = "&";
    //=================================================================================================
    public static final String NETEASE_NEW_HOST = "http://c.m.163.com/";// headline/T1348647909107/0-20.html";
    public static final String NETEASE_NEWS_ARTICLE = "nc/article/";

    // ================================================================================================
    public static final String NOVEL_API_HOST = "http://mylance.top";
    public static final String NOVEL_API_BOOK = "api/getTypeBooks";

    //=================================================================================================

    public static final int RES_SUCCESS = 0;
    public static final int RES_ERR = -1;

    public static interface OnNewsData {
        public void onNewsDataRes(int res,Map<String,ArrayList<NewsEntity>> entities);
    }

    public static interface OnNovelData {
        public void onNovelDataRes(int res,List<NovelEntity> entities);
    }

    private static final OkHttpClient mClient = new OkHttpClient();

    public static void getNewsAsync(String channelid,String channelType,int startPage,final OnNewsData listener) {
        StringBuilder realUrl = new StringBuilder(NETEASE_NEW_HOST).append(NETEASE_NEWS_ARTICLE)
                .append(channelType).append(URL_SEPARATOR).append(channelid).append(URL_SEPARATOR)
                .append(startPage).append("-20.html");
        Request request = new Request.Builder().url(realUrl.toString()).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                listener.onNewsDataRes(RES_ERR,null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(tag,"getnews " + response.code());
                if (response.isSuccessful()) {
//                    Log.d(tag,response.body().string());
                    Gson gson = new Gson();
                    Type type = new TypeToken<Map<String,ArrayList<NewsEntity>>>(){}.getType();
                    Map<String,ArrayList<NewsEntity>> entitys = gson.fromJson(response.body().string(),type);
                    listener.onNewsDataRes(RES_SUCCESS,entitys);
                    //Log.d(tag,"getnews " + entitys.size());
                }
            }
        });
    }

    public static void getNovelList(int type,int startPage,OnNovelData listner) {
        StringBuilder realurl = new StringBuilder(NOVEL_API_HOST).append(URL_SEPARATOR).append(NOVEL_API_BOOK)
                .append(URL_QUERY).append("type=").append(type)
                .append(URL_AND).append("pageIndex=").append(startPage);
        Request request = new Request.Builder().url(realurl.toString()).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(tag,"getnovel " + response.code());
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    List<NovelEntity.NovelItem> entities = gson.fromJson(response.body().string(), NovelEntity.class).getData();
                    Log.d(tag,"getnovel " + entities.size());
                }
            }
        });
    }
}
