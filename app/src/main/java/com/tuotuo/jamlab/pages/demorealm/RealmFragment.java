package com.tuotuo.jamlab.pages.demorealm;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.common.utils.MLog;
import com.tuotuo.jamlab.common.utils.MToast;
import com.tuotuo.jamlab.pages.base.BasePresenter;
import com.tuotuo.jamlab.pages.base.ContentFragment;
import com.tuotuo.jamlab.pages.demorealm.presenter.RealmDemoPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liuzhenhui on 2016/10/31.
 * more examples:
 * https://github.com/realm/realm-java/tree/master/examples
 */
public class RealmFragment extends ContentFragment implements RealmDemoContract.View {
    public static final String TAG = RealmFragment.class.getSimpleName();

    @BindView(R.id.ll_realm_container)
    LinearLayout llRealmRoot;

    @BindView(R.id.btn_realm_start)
    Button btnRealmStart;

    RealmDemoPresenter mRealmDemoPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_realm;
    }

    @Override
    protected BasePresenter createPresenter() {
        mRealmDemoPresenter = new RealmDemoPresenter(this);
        return mRealmDemoPresenter;
    }

    @Override
    protected void onInitView() {
        MLog.d(MLog.TAG_FRAGMENT, TAG + "->" + "onInitView ");
        llRealmRoot.removeAllViews();
    }

    @Override
    public void showStatus(String txt) {
        TextView textView = new TextView(getMainActivity());
        textView.setText(txt);
        llRealmRoot.addView(textView);
    }

    @Override
    public void error(String msg) {
        MToast.show(getMainActivity(), msg);
    }

    @OnClick(R.id.btn_realm_start)
    public void start() {
        mRealmDemoPresenter.simpleRealmWork();
        mRealmDemoPresenter.complexRealmWork();
        mRealmDemoPresenter.rxWork();
    }

}
