package com.tuotuo.jamlab.modules.account.login.impl;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.tuotuo.jamlab.common.utils.MLog;
import com.tuotuo.jamlab.modules.account.login.IAccountLogin;
import com.tuotuo.jamlab.modules.account.login.IAccountLoginListener;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by liuzhenhui on 2016/11/10.
 * https://developers.facebook.com/docs/facebook-login/android
 */

public class AccountFacebook implements IAccountLogin {

    public static final String TAG = AccountFacebook.class.getSimpleName();

    CallbackManager callbackManager;
    IAccountLoginListener iAccountLoginListener;

    public static final AccountFacebook getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static final AccountFacebook INSTANCE = new AccountFacebook();
    }

    @Override
    public boolean hasLogin() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    @Override
    public void logout() {
        LoginManager.getInstance().logOut();
    }


    @Override
    public void login(Fragment fragment, IAccountLoginListener loginListener) {
        iAccountLoginListener = loginListener;
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(fragment, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Set<String> recentlyDeniedPermissions = loginResult.getRecentlyDeniedPermissions();
                StringBuilder sbPermission = new StringBuilder();
                sbPermission.append("Token = " + accessToken.toString());
                sbPermission.append("DeniedPermission:[");
                for (String recentlyDeniedPermission : recentlyDeniedPermissions) {
                    sbPermission.append(recentlyDeniedPermission).append(",");
                }
                sbPermission.append("];GrantedPermission:[");
                Set<String> recentlyGrantedPermissions = loginResult.getRecentlyGrantedPermissions();
                for (String recentlyGrantedPermission : recentlyGrantedPermissions) {
                    sbPermission.append(recentlyGrantedPermission).append(",");
                }
                sbPermission.append("]");
                MLog.d(MLog.TAG_LOGIN, TAG + "->" + "onSuccess :" + sbPermission.toString());
                callbackSuccess();
            }

            @Override
            public void onCancel() {
                MLog.d(MLog.TAG_LOGIN, TAG + "->" + "onCancel ");
                callbackCancle();
            }

            @Override
            public void onError(FacebookException exception) {
                MLog.d(MLog.TAG_LOGIN, TAG + "->" + "onError ");
                callbackFailed();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (callbackManager != null) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public String getName() {
        String name = null;
        if (hasLogin() && Profile.getCurrentProfile() != null) {
            name = Profile.getCurrentProfile().getName();
            return name;
        }
        return "Get Name Failed!";
    }

    @Override
    public void callbackSuccess() {
        if (iAccountLoginListener != null) {
            iAccountLoginListener.success();
        }
    }

    @Override
    public void callbackFailed() {
        if (iAccountLoginListener != null) {
            iAccountLoginListener.fail();
        }
    }

    @Override
    public void callbackCancle() {
        if (iAccountLoginListener != null) {
            iAccountLoginListener.cancle();
        }
    }

}
