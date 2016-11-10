package com.tuotuo.jamlab.pages.login.presenter;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.tuotuo.jamlab.common.utils.MLog;
import com.tuotuo.jamlab.pages.login.LoginContract;

import java.util.Set;

/**
 * Created by liuzhenhui on 2016/11/10.
 */
public class LoginPresenter extends LoginContract.Presenter {
    public static final String TAG = LoginPresenter.class.getSimpleName();
    CallbackManager callbackManager;

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void loginWithFacebook(LoginButton loginButton, Fragment fragment) {
        callbackManager = CallbackManager.Factory.create();
        loginButton.setFragment(fragment);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
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
                success();
            }

            @Override
            public void onCancel() {
                MLog.d(MLog.TAG_LOGIN, TAG + "->" + "onCancel ");
                cancle();
            }

            @Override
            public void onError(FacebookException exception) {
                MLog.d(MLog.TAG_LOGIN, TAG + "->" + "onError ");
                fail();
            }
        });
    }

    @Override
    public void loginWithGoogle() {

    }

    @Override
    public void loginWithTwtter() {

    }

    @Override
    public void loginWithEmail() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void success() {
        if (getView() != null) {
            getView().success();
        }
    }

    public void fail() {
        if (getView() != null) {
            getView().failed();
        }
    }

    public void cancle() {
        if (getView() != null) {
            getView().cancle();
        }
    }
}
