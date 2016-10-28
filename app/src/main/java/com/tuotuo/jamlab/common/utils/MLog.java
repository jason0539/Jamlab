package com.tuotuo.jamlab.common.utils;

import android.util.Log;

import com.tuotuo.jamlab.BuildConfig;

/**
 * Created by liuzhenhui on 2016/10/28.
 */
public class MLog {
    public static final String TAG = MLog.class.getSimpleName();
    private static boolean DEBUG = BuildConfig.DEBUG;

    public static final String TAG_TEST_RXJAVA = "TAG_TEST_RXJAVA";

    public static final void d(String subTag, String msg) {
        if (DEBUG) {
            Log.d(TAG, subTag + "-->" + msg);
        }
    }

    public static final void e(String subTag, String msg) {
        if (DEBUG) {
            Log.e(TAG, subTag + "-->" + msg);
        }
    }

}
