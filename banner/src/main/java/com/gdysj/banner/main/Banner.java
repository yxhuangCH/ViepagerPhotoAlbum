package com.gdysj.banner.main;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gdysj.banner.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: yxhuang
 * Date: 2017/1/3
 * Email: yxhuang@gmail.com
 */

public class Banner extends FrameLayout  {

    private static final int SCROLL_INTERVAL = 1500;
    private static final int SCROLL_TIME = 800;

    private Context mContext;
    private WeakHandler mHandler;

    private BannerViewPager mViewPager;
    private BannerPagerAdapter mPagerAdapter;

    private List<View> mViewList;
    private List<String> mImageUrlList;

    private boolean mIsAutoPlay = true;
    private int mCurrentItem = 0;

    private final Runnable mScrollTask = new Runnable() {
        @Override
        public void run() {
            if (mImageUrlList.size() > 1 && mIsAutoPlay) {
                mCurrentItem = (mCurrentItem + 1) % mPagerAdapter.getCount();
//                mCurrentItem = mCurrentItem % (mPagerAdapter.getCount() + 1) + 1;
                if (mCurrentItem == 1) {
                    mViewPager.setCurrentItem(mCurrentItem, false);
                    mHandler.post(mScrollTask);
                } else {
                    mViewPager.setCurrentItem(mCurrentItem);
                    mHandler.postDelayed(mScrollTask, SCROLL_INTERVAL);
                }
            }
        }
    };


    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        initView();
        initViewPagerScroll();
        initDate();
        initViewList();
        startAutoPlay();
    }

    private void initView(){
        setWillNotDraw(false);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View rootView = inflater.inflate(R.layout.banner, this);
        mViewPager = (BannerViewPager) rootView.findViewById(R.id.banner_viewpager);

    }

    private void initDate(){
        mImageUrlList = new ArrayList<>();
        mImageUrlList.add("http://img1.3lian.com/img013/v4/96/d/45.jpg");
        mImageUrlList.add("http://img3.fengniao.com/forum/attachpics/765/112/30582218_600.jpg");
        mImageUrlList.add("http://img1.3lian.com/2015/w7/87/d/28.jpg");
        mImageUrlList.add("http://img2.3lian.com/2014/f2/159/d/48.jpg");

        mHandler = new WeakHandler();
    }

    private void initViewList(){
        mViewList = new ArrayList<>();
        for (int i = 0; i < mImageUrlList.size(); i++){
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext).load(mImageUrlList.get(i)).into(imageView);
            mViewList.add(imageView);
        }

        mPagerAdapter = new BannerPagerAdapter(mViewList);
        mViewPager.setAdapter(mPagerAdapter);
    }

    // 使用反射，控制滑动时间
    private void initViewPagerScroll() {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            BannerScroller scroller = new BannerScroller(mViewPager.getContext());
            scroller.setDuration(SCROLL_TIME);
            field.set(mViewPager, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *  启动自动轮播
     */
    public void startAutoPlay() {
        mHandler.removeCallbacks(mScrollTask);
        mHandler.postDelayed(mScrollTask, SCROLL_INTERVAL);
    }

    /**
     *  停止自动轮播
     */
    public void stopAutoPlay() {
        mHandler.removeCallbacks(mScrollTask);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mIsAutoPlay) {
            int action = ev.getAction();
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL
                    || action == MotionEvent.ACTION_OUTSIDE) {
                startAutoPlay();
            } else if (action == MotionEvent.ACTION_DOWN) {
                stopAutoPlay();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
