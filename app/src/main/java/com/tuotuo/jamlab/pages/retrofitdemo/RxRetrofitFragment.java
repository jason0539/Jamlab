package com.tuotuo.jamlab.pages.retrofitdemo;

import android.widget.Button;
import android.widget.TextView;

import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.pages.base.ContentFragment;
import com.tuotuo.jamlab.pages.retrofitdemo.entity.Subject;
import com.tuotuo.jamlab.pages.retrofitdemo.http.HttpMethods;
import com.tuotuo.jamlab.pages.retrofitdemo.subscribers.ProgressSubscriber;
import com.tuotuo.jamlab.pages.retrofitdemo.subscribers.SubscriberOnNextListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liuzhenhui on 2016/10/27.
 */
public class RxRetrofitFragment extends ContentFragment {
    public static final String TAG = RxRetrofitFragment.class.getSimpleName();

    @BindView(R.id.btn_rxretrofit_click)
    Button btnClick;
    @BindView(R.id.tv_rxretrofit_result)
    TextView tvResult;

    private SubscriberOnNextListener getTopMovieOnNext;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rxretrofit;
    }

    @Override
    protected void onInitView() {
        getTopMovieOnNext = new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                tvResult.setText(subjects.toString());
            }
        };
    }

    @OnClick(R.id.btn_rxretrofit_click)
    public void clickTestNet() {
        getMovie();
    }

    //进行网络请求
    private void getMovie() {
        HttpMethods.getInstance().getTopMovie(new ProgressSubscriber(getTopMovieOnNext, getActivity()), 0, 10);
    }
}
