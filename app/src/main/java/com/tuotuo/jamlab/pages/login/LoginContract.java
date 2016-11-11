package com.tuotuo.jamlab.pages.login;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.tuotuo.jamlab.pages.base.mvp.BasePresenter;
import com.tuotuo.jamlab.pages.base.mvp.BaseView;

/**
 * Created by liuzhenhui on 2016/11/10.
 */
public class LoginContract {
    public static final String TAG = LoginContract.class.getSimpleName();

    public interface View extends BaseView<Presenter> {
        void success();

        void failed();

        void cancle();
    }

    public static abstract class Presenter extends BasePresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract boolean isLogin();

        public abstract void logout();

        public abstract void loginWithFacebook(Fragment fragment);

        public abstract void loginWithGoogle();

        public abstract void loginWithTwtter();

        public abstract void loginWithEmail();

        public abstract void onActivityResult(int requestCode, int resultCode, Intent data) ;
    }
}
