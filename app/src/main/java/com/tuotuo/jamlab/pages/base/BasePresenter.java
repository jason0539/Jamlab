package com.tuotuo.jamlab.pages.base;

/**
 * Created by liuzhenhui on 2016/11/1.
 */
public interface BasePresenter<T> {
    /**
     * UI页面销毁时，调用该方法，释放Presenter中持有的View引用，防止内存泄露
     * 注意释放view后，之前发出的耗时操作回调中操作view引起空指针
     * 同时，尽量在该方法中反注册、取消一切耗时监听器（view销毁了，数据取回来已经无意义）
     */
    void detachView();
}
