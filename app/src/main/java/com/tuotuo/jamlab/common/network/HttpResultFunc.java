package com.tuotuo.jamlab.common.network;

import rx.functions.Func1;

/**
 * Created by liuzhenhui on 2016/11/9.
 *
 * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
 *
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */
public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
    public static final String TAG = HttpResultFunc.class.getSimpleName();

    @Override
    public T call(HttpResult<T> httpResult) {
        if (httpResult.getCount() == 0) {
            throw new HttpException(HttpException.USER_NOT_EXIST);
        }
        return httpResult.getSubjects();
    }
}

