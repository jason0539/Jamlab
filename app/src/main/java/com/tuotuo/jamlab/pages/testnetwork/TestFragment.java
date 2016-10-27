package com.tuotuo.jamlab.pages.testnetwork;

import android.widget.Button;
import android.widget.TextView;

import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.pages.base.ContentFragment;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liuzhenhui on 2016/10/27.
 */
public class TestFragment extends ContentFragment {
    public static final String TAG = TestFragment.class.getSimpleName();

    @BindView(R.id.btn_test_click)
    Button btnClick;
    @BindView(R.id.tv_test_result)
    TextView tvResult;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void onInitView() {

    }

    @OnClick(R.id.btn_test_click)
    public void clickTestNet() {
        getMovie();
    }

    //进行网络请求
    private void getMovie() {
        String baseUrl = "https://api.douban.com/v2/movie/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();




    }
}
