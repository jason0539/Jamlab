package com.tuotuo.jamlab.pages.rxdemo;

import android.util.Log;
import android.widget.Button;

import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.pages.base.ContentFragment;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by liuzhenhui on 2016/10/28.
 */
public class RxJavaDemoFragment extends ContentFragment {
    public static final String TAG = RxJavaDemoFragment.class.getSimpleName();

    @BindView(R.id.btn_rxjava_print)
    Button btnPrint;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rxjava;
    }

    @Override
    protected void onInitView() {

    }

    @OnClick(R.id.btn_rxjava_print)
    public void print() {
        String[] names = new String[4];
        names[0] = "jason";
        names[1] = "jason1";
        names[2] = "jason2";
        names[3] = "jason3";
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("print", s);
                    }
                });
    }
}
