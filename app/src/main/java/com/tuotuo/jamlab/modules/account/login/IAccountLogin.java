package com.tuotuo.jamlab.modules.account.login;

import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by liuzhenhui on 2016/11/11.
 */
public interface IAccountLogin {
    public static final String TAG = IAccountLogin.class.getSimpleName();

    void login(Fragment fragment, IAccountLoginListener loginListener);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    boolean hasLogin();

    String getName();

    void logout();

    void callbackSuccess();

    void callbackFailed();

    void callbackCancle();
}
