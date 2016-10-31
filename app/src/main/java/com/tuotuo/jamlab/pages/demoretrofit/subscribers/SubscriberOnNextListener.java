package com.tuotuo.jamlab.pages.demoretrofit.subscribers;

/**
 * Created by liuzhenhui on 2016/10/27.
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}