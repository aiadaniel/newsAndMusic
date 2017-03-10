package com.hotchpotch.utils;

import com.hotchpotch.MyApplication;
import com.hotchpotch.R;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by lxm.
 */

public class ChannelUtils {
    public static final String CHANNEL_HEADLINE = "headline";
    public static final String ID_HEADLINE = "T1348647909107";
    public static final String CHANNEL_HOUSE = "house";
    public static final String ID_HOUSE = "5YyX5Lqs";
    public static final String CHANNEL_OTHER = "list";//对应多种id

    public static boolean getNewsChannel(List<String> channels) {
        channels.addAll(asList(MyApplication.getContext().getResources().getStringArray(R.array.channel_name)));
        return true;
    }

    public static boolean getNewsChannel(List<String> channels,List<String> chanIds) {
        channels.addAll(asList(MyApplication.getContext().getResources().getStringArray(R.array.channel_name)));
        chanIds.addAll(Arrays.asList(MyApplication.getContext().getResources().getStringArray(R.array.channel_id)));
        return true;
    }

    public static String getChannelType(String id) {
        switch (id) {
            case ID_HEADLINE:
                return CHANNEL_HEADLINE;
            case ID_HOUSE:
                return CHANNEL_HOUSE;
            default:
                break;
        }
        return CHANNEL_OTHER;
    }
}
