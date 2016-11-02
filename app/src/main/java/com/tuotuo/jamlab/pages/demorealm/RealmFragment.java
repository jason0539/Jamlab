package com.tuotuo.jamlab.pages.demorealm;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.pages.base.ContentFragment;
import com.tuotuo.jamlab.pages.demorealm.presenter.RealmDemoPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liuzhenhui on 2016/10/31.
 */
public class RealmFragment extends ContentFragment implements RealmDemoContract.View {
    public static final String TAG = RealmFragment.class.getSimpleName();

    @BindView(R.id.ll_realm_container)
    LinearLayout llRealmRoot;

    @BindView(R.id.btn_realm_start)
    Button btnRealmStart;

    RealmDemoContract.Presenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_realm;
    }

    @Override
    protected void onInitView() {
        llRealmRoot.removeAllViews();
        mPresenter = new RealmDemoPresenter(this);
    }

    @Override
    public void setPresenter(RealmDemoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showStatus(String txt) {
        TextView textView = new TextView(getMainActivity());
        textView.setText(txt);
        llRealmRoot.addView(textView);
    }

    @OnClick(R.id.btn_realm_start)
    public void start() {
        mPresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
