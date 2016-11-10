package com.tuotuo.jamlab.pages;


import android.os.Handler;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.pages.base.fragment.ContentFragment;
import com.tuotuo.jamlab.pages.base.fragment.JLFragmentManager;
import com.tuotuo.jamlab.pages.base.mvp.BasePresenter;


/**
 * Created by liuzhenhui on 2016/10/27.
 */
public class SplashFragment extends ContentFragment {
    public static final String TAG = SplashFragment.class.getSimpleName();

    public static final int GO_HOME_DELAY = 700;

    private static Handler mHandler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onInitView() {
    }

    Runnable goHomeRunnable = new Runnable() {
        @Override
        public void run() {
            getJLFragmentManager().showFragment(JLFragmentManager.TYPE_HOME, null, false);
        }
    };

    Runnable initLibrarySdk = new Runnable() {
        @Override
        public void run() {
            //Facebook
            FacebookSdk.sdkInitialize(getMainActivity().getApplicationContext());
            AppEventsLogger.activateApp(getMainActivity().getApplication());

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(goHomeRunnable, GO_HOME_DELAY);
        mHandler.post(initLibrarySdk);
    }
}
