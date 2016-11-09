package com.tuotuo.jamlab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;

import com.tuotuo.jamlab.common.utils.MToast;
import com.tuotuo.jamlab.pages.base.fragment.BaseFragment;
import com.tuotuo.jamlab.pages.base.fragment.ContentFragment;
import com.tuotuo.jamlab.pages.base.fragment.JLFragmentManager;
import com.tuotuo.jamlab.pages.base.progress.ProgressDialogHandler;

public class MainActivity extends FragmentActivity {

    private JLFragmentManager mJlFragmentManager;
    private View mForbidTouchView;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mForbidTouchView = findViewById(R.id.view_main_forbid_touch);

        mJlFragmentManager = new JLFragmentManager(this);
        BaseFragment.initBeforeAll(this, mJlFragmentManager);
        MToast.init(getApplicationContext());
        ProgressDialogHandler.init(this);

        mJlFragmentManager.showFragment(JLFragmentManager.TYPE_SPLASH);
    }

    public void forbidTouch(boolean forbid) {
        if (forbid) {
            mForbidTouchView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            mForbidTouchView.setVisibility(View.VISIBLE);
        } else {
            mForbidTouchView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        ContentFragment fragment = mJlFragmentManager.getCurrentFragment();
        if (fragment != null && fragment.onBackPressed())
            return;
        if (mJlFragmentManager.getFragmentStackSize() > 0)
            mJlFragmentManager.back(null);
    }

    public void exitApp() {
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
