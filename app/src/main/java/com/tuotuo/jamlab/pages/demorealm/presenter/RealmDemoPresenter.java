package com.tuotuo.jamlab.pages.demorealm.presenter;

import com.tuotuo.jamlab.pages.demorealm.RealmDemoContract;
import com.tuotuo.jamlab.pages.demorealm.model.RealmSimpleModel;

import rx.Observable;
import rx.Subscriber;
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

    public RealmDemoPresenter(RealmDemoContract.View realmView) {
        mRealmView = realmView;
        mRealmView.setPresenter(this);
        mReamlModel = new RealmSimpleModel();
    }

    @Override
    public void start() {
        String status = simpleRealmWork();
        mRealmView.showStatus(status);
    }

    @Override
    public String simpleRealmWork() {
        // These operations are small enough that we can generally safely run them on the UI thread.
        return mReamlModel.basicCRUD() + mReamlModel.basicQuery() + mReamlModel.basicLinkQuery();
    }

    @Override
    public void complexRealmWork() {
        //复杂操作
        Observable.create(new Observable.OnSubscribe<String>() {
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
                        mRealmView.showStatus(s);
                    }
                });
    }

}
