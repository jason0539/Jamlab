package com.tuotuo.jamlab.pages.demoglide;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.pages.base.mvp.BasePresenter;
import com.tuotuo.jamlab.pages.base.fragment.ContentFragment;

import butterknife.BindView;

/**
 * Created by liuzhenhui on 2016/11/7.
 */
public class DemoGlideFragment extends ContentFragment {
    public static final String TAG = DemoGlideFragment.class.getSimpleName();

    @BindView(R.id.iv_glide_show)
    ImageView ivShow;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_glide;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onInitView() {
        Glide.with(this).
                load("http://pic3.nipic.com/20090701/2847972_130628068_2.jpg")
                .thumbnail(0.1f)
                .into(ivShow);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
