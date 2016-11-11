package com.tuotuo.jamlab.pages.login.presenter;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.tuotuo.jamlab.modules.account.login.IAccountLoginListener;
import com.tuotuo.jamlab.modules.account.login.impl.AccountFacebook;
import com.tuotuo.jamlab.modules.account.login.impl.AccountGoogle;
import com.tuotuo.jamlab.pages.login.LoginContract;

/**
 * Created by liuzhenhui on 2016/11/10.
 */
public class LoginPresenter extends LoginContract.Presenter {
    public static final String TAG = LoginPresenter.class.getSimpleName();

    IAccountLoginListener iAccountLoginListener;
    public LoginPresenter(LoginContract.View view) {
        super(view);
        iAccountLoginListener = new IAccountLoginListener() {
            @Override
            public void success() {
                showSuccess();
            }

            @Override
            public void cancle() {
                showCancle();
            }

            @Override
            public void fail() {
                showFailed();
            }
        };
    }

    @Override
    public boolean isLogin() {
        return AccountFacebook.getInstance().hasLogin();
    }

    @Override
    public void logout() {
        AccountFacebook.getInstance().logout();
    }

    @Override
    public void loginWithFacebook(Fragment fragment) {
        AccountFacebook.getInstance().login(fragment,iAccountLoginListener);
    }

    @Override
    public void loginWithGoogle() {
        AccountGoogle.getInstance().login((Fragment) getView(), iAccountLoginListener);
    }

    @Override
    public void loginWithTwtter() {

    }

    @Override
    public void loginWithEmail() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        AccountFacebook.getInstance().onActivityResult(requestCode, resultCode, data);
        AccountGoogle.getInstance().onActivityResult(requestCode, resultCode, data);
    }

    public void showSuccess() {
        if (getView() != null) {
            getView().success();
        }
    }

    public void showFailed() {
        if (getView() != null) {
            getView().failed();
        }
    }

    public void showCancle() {
        if (getView() != null) {
            getView().cancle();
        }
    }
}
