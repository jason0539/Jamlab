package com.tuotuo.jamlab.pages;

import android.widget.Button;

import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.common.utils.MLog;
import com.tuotuo.jamlab.pages.base.BasePresenter;
import com.tuotuo.jamlab.pages.base.ContentFragment;
import com.tuotuo.jamlab.pages.base.JLFragmentManager;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tuotuo.jamlab.R.id.btn_home_glide;
import static com.tuotuo.jamlab.R.id.btn_home_realm;
import static com.tuotuo.jamlab.R.id.btn_home_rxjava;

/**
 * Created by liuzhenhui on 2016/10/27.
 */
public class HomeFragment extends ContentFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();

    public static final String ATTRIBUTE_CHANNEL = "ATTRIBUTE_CHANNEL";

    @BindView(R.id.btn_home_network)
    Button btnNetwork;
    @BindView(R.id.btn_home_rxjava)
    Button btnRxJava;
    @BindView(btn_home_realm)
    Button btnRealm;
    @BindView(R.id.btn_home_glide)
    Button btnGlide;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onInitView() {
        MLog.d(MLog.TAG_FRAGMENT, TAG + "->" + "onInitView ");
    }

    @OnClick(R.id.btn_home_network)
    public void goTestPage() {
        getJLFragmentManager().showFragment(JLFragmentManager.TYPE_RXRTROFIT);
    }

    @OnClick(btn_home_rxjava)
    public void goRxJava() {
        getJLFragmentManager().showFragment(JLFragmentManager.TYPE_RXJAVA);
    }

    @OnClick(btn_home_realm)
    public void goRealm() {
        getJLFragmentManager().showFragment(JLFragmentManager.TYPE_REALM);
    }

    @OnClick(btn_home_glide)
    public void goGlide() {
        getJLFragmentManager().showFragment(JLFragmentManager.TYPE_GLIDE);
    }

}
