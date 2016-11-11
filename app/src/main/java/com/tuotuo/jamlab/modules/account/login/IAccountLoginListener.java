package com.tuotuo.jamlab.modules.account.login;

/**
 * Created by liuzhenhui on 2016/11/11.
 */
public interface IAccountLoginListener {
    public static final String TAG = IAccountLoginListener.class.getSimpleName();

    void success();

    void cancle();

    void fail();
}
