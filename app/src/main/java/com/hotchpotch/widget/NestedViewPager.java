package com.hotchpotch.widget;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by lxm.
 */

public class NestedViewPager extends ViewPager {
    private static final String tag = "11111";
    PointF downPoint = new PointF();

    public NestedViewPager(Context context) {
        super(context);
    }

    public NestedViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        switch (evt.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录按下时候的坐标
                downPoint.x = evt.getX();
                downPoint.y = evt.getY();
                if (this.getChildCount() > 1) { //有内容，多于1个时
                    // 通知其父控件，现在进行的是本控件的操作，不允许拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (this.getChildCount() > 1) { //有内容，多于1个时
                    // 通知其父控件，现在进行的是本控件的操作，不允许拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                // 在up时判断是否按下和松手的坐标为一个点
                if (PointF.length(evt.getX() - downPoint.x, evt.getY()
                        - downPoint.y) < (float) 5.0) {
                    return true;
                }
                break;
        }
        return super.onTouchEvent(evt);
    }
}
