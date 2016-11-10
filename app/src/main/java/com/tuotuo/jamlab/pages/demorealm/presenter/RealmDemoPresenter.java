package com.tuotuo.jamlab.pages.demorealm.presenter;

import com.tuotuo.jamlab.pages.demorealm.RealmDemoContract;
import com.tuotuo.jamlab.pages.demorealm.bean.Person;
import com.tuotuo.jamlab.pages.demorealm.model.RealmSimpleModel;
import com.tuotuo.jamlab.pages.demorealm.model.RxRealmModel;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by liuzhenhui on 2016/11/1.
 */
public class RealmDemoPresenter extends RealmDemoContract.Presenter {
    public static final String TAG = RealmDemoPresenter.class.getSimpleName();

    RealmSimpleModel mReamlModel;
    RxRealmModel mRxRealmModel;

    Subscription complexSubscription;
    Subscription rxSubscription;

    public RealmDemoPresenter(RealmDemoContract.View realmView) {
        super(realmView);
        mReamlModel = new RealmSimpleModel();
        mRxRealmModel = new RxRealmModel();
        addModel(mReamlModel);
        addModel(mRxRealmModel);
    }

    @Override
    public void simpleRealmWork() {
        // These operations are small enough that we can generally safely run them on the UI thread.
        String status = mReamlModel.basicCRUD() + mReamlModel.basicQuery() + mReamlModel.basicLinkQuery();
        showStatus(status);
    }

    @Override
    public void complexRealmWork() {
        removeSubscription(complexSubscription);
        complexSubscription = mReamlModel.workOnComplex()
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        showStatus(s);
                    }
                });
        addSubscription(complexSubscription);
    }

    @Override
    public void rxWork() {
        removeSubscription(rxSubscription);
        rxSubscription = mRxRealmModel.testAsync().subscribe(new Action1<Person>() {
            @Override
            public void call(Person person) {
                showStatus("subscribeOn/async: " + person.getName() + ":" + person.getAge());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                showError("subscribeOn/async: " + throwable.toString());
            }
        });
        addSubscription(rxSubscription);
    }


    public void showStatus(String msg) {
        if (getView() != null) {
            getView().showStatus(msg);
        }
    }

    public void showError(String msg) {
        if (getView() != null) {
            getView().error(msg);
        }
    }
}
