package com.tuotuo.jamlab.pages.base.mvp;

/**
 * Created by liuzhenhui on 2016/11/4.
 * Model基类，提供基础接口，方便Presenter销毁时统一销毁处理
 */
public abstract class BaseModel {
    public static final String TAG = BaseModel.class.getSimpleName();

    public abstract void destroy();
}
