package com.tuotuo.jamlab.pages.base.fragment;

import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.pages.base.mvp.BasePresenter;

/**
 * Created by liuzhenhui on 2016/11/7.
 */
public class ErrorFragment extends ContentFragment{
    public static final String TAG = ErrorFragment.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_error;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onInitView() {

    }
}
