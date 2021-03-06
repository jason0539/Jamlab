package com.tuotuo.jamlab.pages.demorealm;

import com.tuotuo.jamlab.pages.base.mvp.BasePresenter;
import com.tuotuo.jamlab.pages.base.mvp.BaseView;

/**
 * Created by liuzhenhui on 2016/11/1.
 */
public class RealmDemoContract {
    public interface View extends BaseView<Presenter> {
        void showStatus(String msg);

        void error(String msg);
    }

    public static abstract class Presenter extends BasePresenter<View> {

        public Presenter(View view) {
            super(view);
        }

        protected abstract void simpleRealmWork();

        protected abstract void complexRealmWork();

        protected abstract void rxWork();
    }
}
