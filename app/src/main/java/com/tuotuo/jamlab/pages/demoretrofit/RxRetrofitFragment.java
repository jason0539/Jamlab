package com.tuotuo.jamlab.pages.demoretrofit;

import android.widget.Button;
import android.widget.TextView;

import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.pages.base.fragment.ContentFragment;
import com.tuotuo.jamlab.pages.base.mvp.BasePresenter;
import com.tuotuo.jamlab.pages.demoretrofit.presenter.RxRetrofitDemoPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liuzhenhui on 2016/10/27.
 */
public class RxRetrofitFragment extends ContentFragment implements RxRetrofitContract.View {
    public static final String TAG = RxRetrofitFragment.class.getSimpleName();

    @BindView(R.id.btn_rxretrofit_click)
    Button btnClick;
    @BindView(R.id.tv_rxretrofit_result)
    TextView tvResult;

    private RxRetrofitDemoPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rxretrofit;
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new RxRetrofitDemoPresenter(this);
        return mPresenter;
    }

    @Override
    protected void onInitView() {

    }

    @OnClick(R.id.btn_rxretrofit_click)
    public void clickTestNet() {
        mPresenter.getTopMovies(0, 10);
    }

    @Override
    public void showMovies(String movie) {
        tvResult.setText(movie);
    }
}
