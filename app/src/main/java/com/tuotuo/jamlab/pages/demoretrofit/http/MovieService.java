package com.tuotuo.jamlab.pages.demoretrofit.http;

import com.tuotuo.jamlab.pages.demoretrofit.entity.HttpResult;
import com.tuotuo.jamlab.pages.demoretrofit.entity.Subject;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liuzhenhui on 2016/10/27.
 */
public interface MovieService {
    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
