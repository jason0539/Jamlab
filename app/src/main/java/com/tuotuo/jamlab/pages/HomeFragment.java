package com.tuotuo.jamlab.pages;

import android.widget.Button;

import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.pages.base.ContentFragment;
import com.tuotuo.jamlab.pages.base.JLFragmentManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liuzhenhui on 2016/10/27.
 */
public class HomeFragment extends ContentFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();

    public static final String ATTRIBUTE_CHANNEL = "ATTRIBUTE_CHANNEL";

    @BindView(R.id.btn_home_network)
    Button btnNetwork;
    @BindView(R.id.btn_home_other)
    Button btnOther;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInitView() {

    }

    @OnClick(R.id.btn_home_network)
    public void goTestPage() {
        getJLFragmentManager().showFragment(JLFragmentManager.TYPE_TEST);
    }

    @OnClick(R.id.btn_home_other)
    public void goOtherPage() {
    }

}
