package com.gdysj.banner.main;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Author: yxhuang
 * Date: 2017/1/3
 * Email: yxhuang@gmail.com
 */

public class BannerScroller extends Scroller {

    private int mDuration = 1000;

    public BannerScroller(Context context) {
        super(context);
    }

    public BannerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public BannerScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    /**
     *  设置滑动时间
     * @param time
     */
    public void setDuration(int time) {
        mDuration = time;
    }

}
