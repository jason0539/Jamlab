package com.tuotuo.jamlab;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by liuzhenhui on 2016/10/26.
 */
public class JamApplication extends Application {
    public static final String TAG = JamApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        initThirdPartLibrary();
    }

    private void initThirdPartLibrary() {
        Realm.init(this);
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }
}
