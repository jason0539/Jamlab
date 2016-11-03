package com.tuotuo.jamlab.pages;


import android.os.Handler;

import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.pages.base.BasePresenter;
import com.tuotuo.jamlab.pages.base.ContentFragment;
import com.tuotuo.jamlab.pages.base.JLFragmentManager;


/**
 * Created by liuzhenhui on 2016/10/27.
 */
public class SplashFragment extends ContentFragment {
    public static final String TAG = SplashFragment.class.getSimpleName();

    public static final int GO_HOME_DELAY = 700;

    private Handler mHandler;

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
        mHandler = new Handler();
    }

    Runnable goHomeRunnable = new Runnable() {
        @Override
        public void run() {
            getJLFragmentManager().showFragment(JLFragmentManager.TYPE_HOME, null,false);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(goHomeRunnable, GO_HOME_DELAY);
    }
}
