package com.tuotuo.jamlab.modules.account.login;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;

/**
 * Created by liuzhenhui on 2016/11/10.
 */
public class AccountFacebook {
    public static final String TAG = AccountFacebook.class.getSimpleName();

    public static final AccountFacebook getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static final AccountFacebook INSTANCE = new AccountFacebook();
    }

    public boolean isLogin() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    public String getName() {
        String name = null;
        if (isLogin()) {
            name = Profile.getCurrentProfile().getName();
            return name;
        }
        return "Not Login";
    }

    public void logOut() {
        LoginManager.getInstance().logOut();
    }
}
