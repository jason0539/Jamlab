package com.tuotuo.jamlab.pages.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tuotuo.jamlab.R;
import com.tuotuo.jamlab.pages.fragments.base.ContentFragment;

/**
 * Created by liuzhenhui on 2016/10/27.
 */
public class HomeFragment extends ContentFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();

    public static final String ATTRIBUTE_CHANNEL = "ATTRIBUTE_CHANNEL";

    private TextView tvShow;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInitView() {
        tvShow = (TextView) findViewById(R.id.tv_home_show);
        if (mShowBundle != null) {
            String string = mShowBundle.getString(ATTRIBUTE_CHANNEL);
            tvShow.setText(string);
        }
        tvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(ATTRIBUTE_CHANNEL, "success");
                getJLFragmentManager().back(bundle);
            }
        });
    }
}
