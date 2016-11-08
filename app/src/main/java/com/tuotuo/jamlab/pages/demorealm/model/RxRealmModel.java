package com.tuotuo.jamlab.pages.demorealm.model;

import com.tuotuo.jamlab.pages.base.mvp.BaseModel;
import com.tuotuo.jamlab.pages.demorealm.bean.Person;

import io.realm.Realm;
import rx.Observable;

/**
 * Created by liuzhenhui on 2016/11/3.
 */
public class RxRealmModel extends BaseModel {
    public static final String TAG = RxRealmModel.class.getSimpleName();
    private Realm realm;

    public RxRealmModel() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void destroy() {
        realm.close();
    }

    public Observable testAsync() {
        return realm.where(Person.class)
                .findAllSortedAsync("name")
                .get(0)
                .<Person>asObservable();
    }
}