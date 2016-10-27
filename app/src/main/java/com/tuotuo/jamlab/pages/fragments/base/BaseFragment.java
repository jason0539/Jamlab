package com.tuotuo.jamlab.pages.fragments.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuotuo.jamlab.MainActivity;

/**
 * Created by liuzhenhui on 2016/10/27.
 */
public class BaseFragment extends Fragment {
    private final static String TAG = BaseFragment.class.getSimpleName();

    protected static MainActivity mActivity;
    protected static JLFragmentManager jlFragmentManager;
    protected static Context mContext;
    protected ViewGroup mContainer;
    protected static LayoutInflater mInflater;

    public static void initBeforeAll(MainActivity activity, JLFragmentManager fragmentManager) {
        mActivity = activity;
        mContext = mActivity.getApplication();
        jlFragmentManager = fragmentManager;
        mInflater = mActivity.getLayoutInflater();
    }

    public static JLFragmentManager getJLFragmentManager() {
        return jlFragmentManager;
    }

    public static LayoutInflater getInflater() {
        return mInflater;
    }

    public boolean canProcessUI() {
        return isAdded();
    }

    public static MainActivity getMainActivity() {
        return mActivity;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // 允许fragment修改menu
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContainer = container;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
