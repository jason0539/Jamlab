package com.tuotuo.jamlab;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.okhttp.OkHttpClient;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.io.InputStream;

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
        //Realm
        Realm.init(this);
        //Glide
        Glide.get(this).register(GlideUrl.class, InputStream.class,new OkHttpUrlLoader.Factory(new OkHttpClient()));
    }
}
