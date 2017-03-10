package com.hotchpotch.dialog;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.hotchpotch.R;

/**
 * Created by lxm.
 */

public class UpdateDialog extends Dialog {
    private static final String tag = "11111";

    LinearLayout mContainerLl;

    public UpdateDialog(Context context) {
        super(context);
        init(context);
    }

    public UpdateDialog(Context context,int theme) {
        super(context,theme);
        init(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag,"update dialog oncreate");
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;//设成match_parent时窗口全屏
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    private void init(Context context) {
        View rootView = View.inflate(context, R.layout.update_dialog,null);
        mContainerLl = (LinearLayout) rootView.findViewById(R.id.ll_containor);
        setContentView(rootView);

        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Log.d(tag,"update dialog show");
                long duration = 1000;
                //AnimationSet animationSet = new AnimationSet(true);
                ObjectAnimator animator = new ObjectAnimator().ofFloat(mContainerLl,"translationY",-300,0).setDuration(duration);
                animator.start();//这次尝试显示这个container虽然是根布局，但不是整个窗口的。所以动画效果只有窗口内容部分，而不是整个窗口。
            }
        });
    }
}
