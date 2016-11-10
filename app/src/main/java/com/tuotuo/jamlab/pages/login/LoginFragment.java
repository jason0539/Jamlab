package com.tuotuo.jamlab.pages.login;

import android.content.Intent;

import com.facebook.login.widget.LoginButton;
import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.common.utils.MToast;
import com.tuotuo.jamlab.pages.base.fragment.ContentFragment;
import com.tuotuo.jamlab.pages.base.mvp.BasePresenter;
import com.tuotuo.jamlab.pages.login.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liuzhenhui on 2016/11/10.
 */
public class LoginFragment extends ContentFragment implements LoginContract.View {
    public static final String TAG = LoginFragment.class.getSimpleName();

    @BindView(R.id.btn_login_facebook)
    LoginButton mBtnFacebookLogin;

    private LoginPresenter mLoginPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected BasePresenter createPresenter() {
        mLoginPresenter = new LoginPresenter(this);
        return mLoginPresenter;
    }

    @Override
    protected void onInitView() {
    }

    @OnClick(R.id.btn_login_facebook)
    public void loginWithFacebook() {
        mLoginPresenter.loginWithFacebook(mBtnFacebookLogin, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mLoginPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void success() {
        MToast.show("Login Success");
    }

    @Override
    public void failed() {
        MToast.show("Login Failed");
    }

    @Override
    public void cancle() {
        MToast.show("Login Cancle");
    }
}
