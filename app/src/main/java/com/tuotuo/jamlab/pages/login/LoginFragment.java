package com.tuotuo.jamlab.pages.login;

import android.content.Intent;
import android.widget.Button;

import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.common.utils.MToast;
import com.tuotuo.jamlab.modules.account.login.impl.AccountFacebook;
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
    Button mBtnFacebookLogin;

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

    @Override
    public void onResume() {
        super.onResume();
        updateLoginState();
    }

    void updateLoginState() {
        if (mLoginPresenter.isLogin()) {
            mBtnFacebookLogin.setText("Login : " + AccountFacebook.getInstance().getName());
        } else {
            mBtnFacebookLogin.setText(getResources().getString(R.string.login_with_facebook));
        }
    }

    @OnClick(R.id.btn_login_facebook)
    public void loginWithFacebook() {
        if (mLoginPresenter.isLogin()) {
            mLoginPresenter.logout();
            updateLoginState();
        } else {
            mLoginPresenter.loginWithFacebook(this);
        }
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
