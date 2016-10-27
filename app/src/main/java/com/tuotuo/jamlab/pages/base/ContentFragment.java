package com.tuotuo.jamlab.pages.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.tuotuo.jamlab.common.utils.AnimationFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.ButterKnife;

/**
 * Created by liuzhenhui on 2016/10/27.
 * onAttach-->onCreate-->onCreateView-->onActivityCreated-->onStart-->onResume-->onPause-->onStop-->onDestroyView-->onDestroy-->onDetach
 */
public abstract class ContentFragment extends BaseFragment {
    private static final String TAG = ContentFragment.class.getSimpleName();

    private int mFragmentType = -1;

    protected Bundle mShowBundle; // 展示参数
    protected Bundle mBackBundle; // 回退参数
    protected View mContentView; // 内容视图

    protected boolean mViewCreated = false;
    protected boolean mNeedInitView = false;
    protected boolean mIsDisplayed = false;

    public ContentFragment() {
        mIsDisplayed = false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey(ContentFragmentManager.KEY_FRAGMENT_TYPE)) {// 类型参数
                mFragmentType = bundle.getInt(ContentFragmentManager.KEY_FRAGMENT_TYPE);
            }
            if (bundle.containsKey(ContentFragmentManager.KEY_SHOW_BUNDLE)) {// 显示参数
                mShowBundle = bundle.getBundle(ContentFragmentManager.KEY_SHOW_BUNDLE);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey(ContentFragmentManager.KEY_BACK_BUNDLE))// 回退参数
                mBackBundle = bundle.getBundle(ContentFragmentManager.KEY_BACK_BUNDLE);
        }

        if (mContentView != null) {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null)
                parent.removeView(mContentView);
            mViewCreated = true;
        } else {
            mContentView = inflater.inflate(getLayoutId(), mContainer, false);
            ButterKnife.bind(this, mContentView);
            mViewCreated = true;
        }
        if (mContentView != null) {
            mContentView.setClickable(true);
        }
        onInit();
        return mContentView;
    }

    /**
     * 初始化函数
     */
    protected void onInit() {
        if (mNeedInitView) {
            onInitView();
            mNeedInitView = false;
        }
    }

    protected View findViewById(int id) {
        return mContentView.findViewById(id);
    }

    public void requestInitView() {
        if (mViewCreated) {
            onInitView();
        } else {
            mNeedInitView = true;
        }
    }

    protected abstract int getLayoutId();

    protected abstract void onInitView();

    @Override
    public void onDestroyView() {
        mIsDisplayed = false;
        mViewCreated = false;
        mNeedInitView = false;
        super.onDestroyView();
    }

    public boolean onBackPressed() {
        return false;
    }

    /**
     * 获取退出动画时长
     */
    public long getAnimationOutDuration(int fragmentType, boolean isBack) {
        if (!isAdded()) { // 防御连续多次replaceFragment造成的crash
            return 0;
        }

        Map<View, Animation> animMap = animationOut(fragmentType, isBack);
        if (animMap == null) {
            return 0;
        }

        return getAnimationTotalDuration(animMap.values());
    }

    /**
     * 获取动画的总共时间
     */
    protected long getAnimationTotalDuration(Collection<Animation> animList) {

        long duration = 0;
        if (animList != null) {
            for (Animation anim : animList) {
                duration = Math.max(anim.getStartOffset() + anim.getDuration(), duration);
            }
        }

        return duration;
    }

    /**
     * 构造一个空动画，使其长度等于time
     */
    protected Animation createEmptyAnimation(long duration) {
        Animation animation = new Animation() {
        };
        animation.setDuration(duration);

        return animation;
    }

    /**
     * 开始animMap中的所有动画
     */
    protected void startAnimation(Map<View, Animation> animMap) {
        if (animMap == null)
            return;

        Set<View> viewList = animMap.keySet();
        Animation anim = null;
        for (View view : viewList) {
            anim = animMap.get(view);
            if (anim != null && view != null)
                view.startAnimation(anim);
        }
    }

    /**
     * 进入页面的控件动画安排
     *
     * @param lastDuration 旧页面动画时长
     * @param fragmentType fragment类型
     * @return 返回控件动画映射
     */
    protected Map<View, Animation> animationIn(long lastDuration,
                                               int fragmentType, boolean isBack) {
        Map<View, Animation> animMap = new HashMap<View, Animation>();
        if (jlFragmentManager.isMapContent(fragmentType)) {
            animMap.put(mContentView, AnimationFactory.getAnimation(mContext,
                    AnimationFactory.ANIM_POP_IN, lastDuration, 300));
        } else {
            if (isBack)
                animMap.put(mContentView, AnimationFactory.getAnimation(mContext, AnimationFactory.ANIM_LEFT_IN, -1, 300));
            else
                animMap.put(mContentView, AnimationFactory.getAnimation(mContext, AnimationFactory.ANIM_RIGHT_IN, -1, 300));

        }
        return animMap;
    }

    /**
     * 退出页面的控件动画安排
     *
     * @param fragmentType fragment类型
     * @return 返回控件动画映射
     */
    protected Map<View, Animation> animationOut(int fragmentType, boolean isBack) {
        Map<View, Animation> animMap = new HashMap<View, Animation>();
        if (jlFragmentManager.isMapContent(fragmentType)) {
            animMap.put(mContentView, AnimationFactory.getAnimation(mContext, AnimationFactory.ANIM_POP_OUT, -1, 300));
        } else {
            if (isBack) {
                animMap.put(mContentView, AnimationFactory.getAnimation(mContext, AnimationFactory.ANIM_RIGHT_OUT, -1, 300));
            } else {
                animMap.put(mContentView, AnimationFactory.getAnimation(mContext, AnimationFactory.ANIM_LEFT_OUT, -1, 300));
            }
        }
        return animMap;
    }

    @Override
    public Animation onCreateAnimation(int transit, final boolean enter, int nextAnim) {

		/* 解决内存被回收后进入APP会crash的问题 */
        if (jlFragmentManager == null) {
            mActivity.exitApp();
            return null;
        }

        Map<View, Animation> animMap = null;
        Animation anim = null;

        final int fragmentType = nextAnim; // trick：利用nextAnim代表fragment类型
        boolean isBack = ((transit & 0x00008000) == 0x00008000);

        if (enter) {
            long lastDuration = isBack ? transit & 0x00007fff : transit; // trick：利用transit代表旧页面动画时长
            animMap = animationIn(lastDuration, fragmentType, isBack);
            long duration = lastDuration;
            if (animMap != null && animMap.size() > 0)
                duration = Math.max(
                        getAnimationTotalDuration(animMap.values()), duration);
            anim = createEmptyAnimation(duration);
            anim.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    /* 注意：这里只触发进入新页面的动画结束，不触发退出旧页面的动画结束！ */
                    mActivity.forbidTouch(false);// 若进入动画先于退出动画，且进入动画为0时长动画，则可能引起禁止点击不消失的bug
                    afterAnimationIn(fragmentType);
                }
            });
        } else {
            beforeAnimationOut(fragmentType);
            animMap = animationOut(fragmentType, isBack);
            long duration = 0;
            if (animMap != null && animMap.size() > 0)
                duration = Math.max(
                        getAnimationTotalDuration(animMap.values()), duration);
            anim = createEmptyAnimation(duration);
        }

        mActivity.forbidTouch(true); // 若进入动画先于退出动画，且进入动画为0时长动画，则可能引起禁止点击不消失的bug
        startAnimation(animMap);

        return anim;
    }

    /**
     * 退出动画开始前
     *
     * @param fragmentType 即将进入的fragment类型
     */
    protected void beforeAnimationOut(int fragmentType) {
    }

    /**
     * 进入动画结束后
     *
     * @param fragmentType 上一个fragment类型
     */
    protected void afterAnimationIn(int fragmentType) {

    }

    /**
     * 获取当前fragment类型
     */
    public int getType() {
        return mFragmentType;
    }

    @Override
    public void onResume() {
        mIsDisplayed = true;
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
