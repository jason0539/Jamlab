package com.tuotuo.jamlab.modules.account.login.impl;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.tuotuo.jamlab.modules.account.login.IAccountLogin;
import com.tuotuo.jamlab.modules.account.login.IAccountLoginListener;

/**
 * Created by liuzhenhui on 2016/11/11.
 */
public class AccountGoogle implements IAccountLogin{
    public static final String TAG = AccountGoogle.class.getSimpleName();

    @Override
    public boolean hasLogin() {
        return false;
    }

    @Override
    public void logout() {

    }

    @Override
    public void login(Fragment fragment, IAccountLoginListener loginListener) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public String getName() {
        return null;
    }
}
