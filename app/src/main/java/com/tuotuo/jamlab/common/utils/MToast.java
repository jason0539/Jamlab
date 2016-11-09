package com.tuotuo.jamlab.common.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by liuzhenhui on 2016/10/31.
 */
public class MToast {
    public static final long SHOW_NET_ERROR_TOAST_SPACE = 5000L;
    public static Context mContext;
    private static long mShowToastLastTime = 0;
    private static String mMsg = null;

    public static final void init(Context context) {
        mContext = context;
    }

    public static void show(String message) {
        if (mContext != null) {
            show(mContext, message);
        }
    }

    public static void show(Context context, String message) {
        show(context, message, 0, false);
    }

    private static void show(Context context, String message, int gravity, boolean autoHide) {
        if (context == null || TextUtils.isEmpty(message)) {
            return;
        }
        if (autoHide) {
            if (!canShowToast(message)) {
                return;
            }
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> infos = am.getRunningTasks(1);
        if (infos != null && infos.get(0) != null && context.getPackageName().equals(infos.get(0).baseActivity.getPackageName())) {

            int duration = Toast.LENGTH_SHORT;
            if (message.length() > 15) {
                duration = Toast.LENGTH_LONG;// 如果字符串比较长，那么显示的时间也长一些。
            }
            Toast toast = Toast.makeText(context, message, duration);
            if (gravity != 0) {
                toast.setGravity(gravity, 0, 0);
            }
            toast.show();
        }
    }

    public static boolean canShowToast(String content) {
        boolean can = false;
        long nowTime = System.currentTimeMillis();
        if (content != null && mMsg != null && content.equals(mMsg)) {
            if (Math.abs(nowTime - mShowToastLastTime) > SHOW_NET_ERROR_TOAST_SPACE) {
                mShowToastLastTime = nowTime;
                can = true;
            } else {
                can = false;
            }
        } else {
            can = true;
        }
        if (can) {
            mMsg = content;
        }
        return can;
    }

    public static void showInCentre(Context context, String message, boolean autoHide) {
        show(context, message, Gravity.CENTER, autoHide);
    }

    public static void show(Context context, int resId) {
        if (context == null) {
            return;
        }
        show(context, context.getString(resId));
    }

    public static void showTip(Context context, String message) {
        if (context == null) {
            return;
        }
        Toast mToast = new Toast(context);
        TextView textView = new TextView(context);
        textView.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        textView.setText(message);
        textView.setTextColor(context.getResources().getColor(android.R.color.white));
        textView.setTextSize(16);
        mToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, ScreenUtils.dpToPxInt(context, 150));
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(textView);
        mToast.show();
    }

}