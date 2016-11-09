package com.tuotuo.jamlab.pages.demoretrofit.model;

import com.tuotuo.jamlab.common.network.HttpEngine;
import com.tuotuo.jamlab.common.network.HttpResultFunc;
import com.tuotuo.jamlab.pages.base.mvp.BaseModel;
import com.tuotuo.jamlab.pages.base.progress.SubscriberOnNextListener;
import com.tuotuo.jamlab.pages.demoretrofit.entity.Subject;
import com.tuotuo.jamlab.pages.demoretrofit.http.MovieService;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by liuzhenhui on 2016/11/8.
 */
public class GetMovieModel extends BaseModel {
    public static final String TAG = GetMovieModel.class.getSimpleName();
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private Retrofit retrofit;
    private MovieService movieService;

    public GetMovieModel() {
        retrofit = HttpEngine.getInstance().getRetrofit(BASE_URL);
    }

    public void getMovies(int start, int count, SubscriberOnNextListener listener) {
        Observable observable = getMovieService()
                .getTopMovie(start, count)
                .map(new HttpResultFunc<List<Subject>>());
        toSubscribeWithProgress(observable, listener);
    }

    public void getMovies(int start, int count, Subscriber subscriber) {
        Observable observable = getMovieService()
                .getTopMovie(start, count)
                .map(new HttpResultFunc<List<Subject>>());
        toSubscribe(observable, subscriber);
    }

    public void getMovies(int start, int count, Action1 action1) {
        Observable observable = getMovieService()
                .getTopMovie(start, count)
                .map(new HttpResultFunc<List<Subject>>());
        toSubscribe(observable, action1);
    }

    /**
     * 使用原生回掉
     */
    public Observable getMovies(int start, int count) {
        Observable observable = getMovieService()
                .getTopMovie(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new HttpResultFunc<List<Subject>>());
        return observable;
    }

    @Override
    public void destroy() {
        retrofit = null;
    }

    public MovieService getMovieService() {
        if (movieService == null) {
            movieService = retrofit.create(MovieService.class);
        }
        return movieService;
    }

}
