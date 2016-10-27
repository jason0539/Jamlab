package com.tuotuo.jamlab.pages.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.pages.fragments.base.ContentFragment;
import com.tuotuo.jamlab.pages.fragments.base.JLFragmentManager;

/**
 * Created by liuzhenhui on 2016/10/27.
 */
public class SplashFragment extends ContentFragment {
    public static final String TAG = SplashFragment.class.getSimpleName();

    private Button btnGoHome;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void onInitView() {
        btnGoHome = (Button) findViewById(R.id.btn_splash_goto);
        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(HomeFragment.ATTRIBUTE_CHANNEL, "hello");
                getJLFragmentManager().showFragment(JLFragmentManager.TYPE_HOME, bundle);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBackBundle != null) {
            String name = mBackBundle.getString(HomeFragment.ATTRIBUTE_CHANNEL);
            btnGoHome.setText(name);
        }
    }
}
