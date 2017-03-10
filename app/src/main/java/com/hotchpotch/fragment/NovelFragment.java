package com.hotchpotch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hotchpotch.utils.OkUtils;

/**
 * Created by lxm.
 */

public class NovelFragment extends Fragment {

    //http://mylance.top/api/getTypeBooks?type=3&pageIndex=0
    public static final String API_SERVER = "http://mylance.top";
    //根据图书类别获得图书列表
//    @GET("api/getTypeBooks")
//    Observable<HttpResult<List<BookListDto>>> getBookList(@Query("type")String type, @Query("pageIndex")int pageIndex);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        OkUtils.getNovelList(1,0,null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
