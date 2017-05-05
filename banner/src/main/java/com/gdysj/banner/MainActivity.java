package com.gdysj.banner;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gdysj.banner.main.BannerPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int START_SCROLL = 1;
    private static final int SCROLL = 2;
    private static final int SCROLL_INTERVAL = 1500;

    private ViewPager mViewPager;
    private BannerPagerAdapter mPagerAdapter;

    private List<View> mViewList;
    private List<String> mImageViewList;

    private int mCurrentItem = 0;

    private boolean mIsAutoPlay = true;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case START_SCROLL:
                    mHandler.sendEmptyMessageDelayed(SCROLL, 400);
                    break;

                case SCROLL:
                    mCurrentItem = (mCurrentItem + 1) % mPagerAdapter.getCount();
                    mViewPager.setCurrentItem(mCurrentItem);
                    mHandler.sendEmptyMessageDelayed(SCROLL, 3000);
                    break;

            }

        }
    };

    private final Runnable mScrollTask = new Runnable() {
        @Override
        public void run() {
            if (mImageViewList.size() > 1 && mIsAutoPlay) {
                mCurrentItem = (mCurrentItem + 1) % mPagerAdapter.getCount();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mViewPager = (ViewPager) findViewById(R.id.view_pager);

//        initDate();
//        initViewList();


//        mPagerAdapter = new BannerPagerAdapter(mViewList);
//        mViewPager.setAdapter(mPagerAdapter);
//
//        mViewPager.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startAutoPlay();
//            }
//        }, 150);


    }

    private void initDate(){
        mImageViewList = new ArrayList<>();
        mImageViewList.add("http://img1.3lian.com/img013/v4/96/d/45.jpg");
        mImageViewList.add("http://img3.fengniao.com/forum/attachpics/765/112/30582218_600.jpg");
        mImageViewList.add("http://img1.3lian.com/2015/w7/87/d/28.jpg");
        mImageViewList.add("http://img2.3lian.com/2014/f2/159/d/48.jpg");
    }

    private void initViewList(){
        mViewList = new ArrayList<>();
        for (int i = 0; i < mImageViewList.size(); i++){
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(this).load(mImageViewList.get(i)).into(imageView);
            mViewList.add(imageView);
        }

    }

    public void startAutoPlay() {
        mHandler.removeCallbacks(mScrollTask);
        mHandler.postDelayed(mScrollTask, SCROLL_INTERVAL);
    }

    public void stopAutoPlay() {
        mHandler.removeCallbacks(mScrollTask);
    }



}
