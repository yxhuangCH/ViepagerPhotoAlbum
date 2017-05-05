package com.gdysj.pulltorefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "PtrFrameLayout";


    private PtrFrameLayout ptr_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ptr_frame = (PtrFrameLayout) findViewById(R.id.ptr_frame);

        HeaderView headerView = new HeaderView(this);

        ptr_frame.setResistance(1.7f);
        ptr_frame.setRatioOfHeaderHeightToRefresh(1.2f);
        ptr_frame.setDurationToClose(200);
        ptr_frame.setDurationToCloseHeader(1000);
        // default is false
        ptr_frame.setPullToRefresh(false);
        // default is true
        ptr_frame.setKeepHeaderWhenRefresh(true);

        ptr_frame.setHeaderView(headerView);


        ptr_frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptr_frame.autoRefresh(true);
                Log.i(TAG, "自动刷新");
            }
        }, 150);

        ptr_frame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptr_frame.refreshComplete();
                        Log.i(TAG, "下拉刷新");

                        Toast.makeText(MainActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();
                    }
                }, 1800);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }
        });

    }
}
