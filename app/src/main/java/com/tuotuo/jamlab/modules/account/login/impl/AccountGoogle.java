package com.tuotuo.jamlab.modules.account.login.impl;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.tuotuo.jamlab.common.utils.MLog;
import com.tuotuo.jamlab.modules.account.login.IAccountLogin;
import com.tuotuo.jamlab.modules.account.login.IAccountLoginListener;

/**
 * Created by liuzhenhui on 2016/11/11.
 * https://developers.google.com/identity/sign-in/android/sign-in
 */
public class AccountGoogle implements IAccountLogin {
    public static final String TAG = AccountGoogle.class.getSimpleName();
    public static final int RC_SIGN_IN = 0x111;

    GoogleApiClient mGoogleApiClient;
    IAccountLoginListener iAccountLoginListener;

    public static final AccountGoogle getInstance() {
        return AccountGoogle.LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static final AccountGoogle INSTANCE = new AccountGoogle();
    }

    @Override
    public boolean hasLogin() {
        return false;
    }

    @Override
    public void logout() {
    }

    @Override
    public void login(Fragment fragment, IAccountLoginListener loginListener) {
        iAccountLoginListener = loginListener;

        if (mGoogleApiClient == null) {
            GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                    .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestProfile()
                    .build();
            mGoogleApiClient = new GoogleApiClient.Builder(fragment.getActivity())
                    .enableAutoManage(fragment.getActivity(), new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                            callbackFailed();
                        }
                    })
                    .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                    .build();
        }
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        fragment.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else if (resultCode == Activity.RESULT_CANCELED) {
            callbackCancle();
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        MLog.d(MLog.TAG_LOGIN, "handleSignInResult:" + result.isSuccess());
        if (result !=null && result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            String name = acct.getDisplayName();
            MLog.d(MLog.TAG_LOGIN,TAG+"->"+"handleSignInResult name = " + name);
            callbackSuccess();
        } else {
            // Signed out, show unauthenticated UI.
            callbackFailed();
        }
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void callbackFailed() {
        if (iAccountLoginListener != null) {
            iAccountLoginListener.fail();
            iAccountLoginListener = null;
        }
    }

    @Override
    public void callbackCancle() {
        if (iAccountLoginListener != null) {
            iAccountLoginListener.cancle();
            iAccountLoginListener = null;
        }
    }

    @Override
    public void callbackSuccess() {
        if (iAccountLoginListener != null) {
            iAccountLoginListener.success();
            iAccountLoginListener = null;
        }
    }

}
