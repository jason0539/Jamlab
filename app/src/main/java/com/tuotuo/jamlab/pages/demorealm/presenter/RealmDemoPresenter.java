package com.tuotuo.jamlab.pages.demorealm.presenter;

import com.tuotuo.jamlab.pages.demorealm.RealmDemoContract;
import com.tuotuo.jamlab.pages.demorealm.model.RealmSimpleModel;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by liuzhenhui on 2016/11/1.
 */
public class RealmDemoPresenter implements RealmDemoContract.Presenter {
    public static final String TAG = RealmDemoPresenter.class.getSimpleName();

    RealmDemoContract.View mRealmView;
    RealmSimpleModel mReamlModel;
    Subscription mComplexSubscription;

    public RealmDemoPresenter(RealmDemoContract.View realmView) {
        mRealmView = realmView;
        mReamlModel = new RealmSimpleModel();
    }

    public void work() {
        String status = simpleRealmWork();
        if (mRealmView != null) {
            mRealmView.showStatus(status);
        }
    }

    @Override
    public String simpleRealmWork() {
        // These operations are small enough that we can generally safely run them on the UI thread.
        return mReamlModel.basicCRUD() + mReamlModel.basicQuery() + mReamlModel.basicLinkQuery();
    }

    @Override
    public void complexRealmWork() {
        //复杂操作
        mComplexSubscription = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String info;
                info = mReamlModel.complexReadWrite();
                info += mReamlModel.complexQuery();
                subscriber.onNext(info);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if (mRealmView != null) {
                            mRealmView.showStatus(s);
                        }
                    }
                });
    }

    @Override
    public void detachView() {
        // 页面销毁时，注意反注册，防止内存泄露
        if (mComplexSubscription.isUnsubscribed()) {
            mComplexSubscription.unsubscribe();
        }
        mReamlModel.destroy();
        mRealmView = null;
    }
}
