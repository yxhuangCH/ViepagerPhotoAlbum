package com.gdysj.banner.main;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Author: yxhuang
 * Date: 2017/1/3
 * Email: yxhuang@gmail.com
 */

public class BannerViewPager extends ViewPager {

    private boolean mScrollable = true;

    public BannerViewPager(Context context) {
        super(context);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mScrollable && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mScrollable && super.onInterceptTouchEvent(ev);
    }

    /**
     *  set ViewPager scroll flag
     * @param scrollable
     */
    public void setScrollable(boolean scrollable) {
        mScrollable = scrollable;
    }
}
