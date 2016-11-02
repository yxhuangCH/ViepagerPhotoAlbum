package com.gdysj.viewpagerindex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.gdysj.viewpagerindex.GrallyPhotoAlbeum.GrallyActivity;
import com.viewpagerindicator.IconPageIndicator;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static rx.schedulers.Schedulers.start;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private IconPageIndicator icon_page_indicator;
    private TestFragmentAdapter mFragmentAdapter;

    private Subscription mViewPagerSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        icon_page_indicator = (IconPageIndicator) findViewById(R.id.icon_page_indicator);

        mFragmentAdapter = new TestFragmentAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mFragmentAdapter);
        icon_page_indicator.setViewPager(viewpager);


        mViewPagerSubscribe = Observable.interval(2, 2, java.util.concurrent.TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        int currentIndex = viewpager.getCurrentItem();
                        if (++currentIndex == mFragmentAdapter.getCount()) {
                            viewpager.setCurrentItem(0);
                        } else {
                            viewpager.setCurrentItem(currentIndex, true);
                        }

                    }
                });

        viewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        stop();
                        break;
                    case MotionEvent.ACTION_UP:
                        start();
                        break;
                }
                return false;
            }
        });

        Button button = (Button) findViewById(R.id.btn_jump);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GrallyActivity.class);
                startActivity(intent);
            }
        });

    }

    public void stop() {
        if(mViewPagerSubscribe.isUnsubscribed()) {
            mViewPagerSubscribe.unsubscribe();
        }
    }
}
