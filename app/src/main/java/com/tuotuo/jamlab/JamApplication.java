package com.tuotuo.jamlab;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

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
        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                            .build());
        }
        Realm.init(this);
    }
}
