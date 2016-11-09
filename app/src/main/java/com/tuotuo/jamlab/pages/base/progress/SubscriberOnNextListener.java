package com.tuotuo.jamlab.pages.base.progress;

/**
 * Created by liuzhenhui on 2016/10/27.
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}