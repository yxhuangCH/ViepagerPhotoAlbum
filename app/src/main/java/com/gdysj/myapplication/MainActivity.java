package com.gdysj.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private List<String> mStringList;

    private ListView mListView;

    private boolean mIsEnd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

//        mListView = (ListView) findViewById(R.id.list_view);

        mStringList = new ArrayList<>();
        mStringList.add("1");
        mStringList.add("2");
        mStringList.add("3");
        mStringList.add("4");
        mStringList.add("5");
        mStringList.add("6");
        mStringList.add("7");
        mStringList.add("8");
        mStringList.add("9");
        mStringList.add("10");
        mStringList.add("11");
        mStringList.add("12");
        mStringList.add("13");
        mStringList.add("14");
        mStringList.add("15");
        mStringList.add("16");
        mStringList.add("17");
        mStringList.add("18");
        mStringList.add("19");
        mStringList.add("20");
        mStringList.add("21");
        mStringList.add("22");
        mStringList.add("23");
        mStringList.add("24");
        mStringList.add("25");
        mStringList.add("26");
        mStringList.add("27");
        mStringList.add("28");
        mStringList.add("29");
        mStringList.add("30");
        mStringList.add("31");


        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        RecyclerAdapter adapter = new RecyclerAdapter(this, mStringList);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

                Log.i("yxh", "visibleItemCount: " + visibleItemCount + "  totalItemCount: " + totalItemCount
                        + "   lastVisibleItemPosition: " + lastVisibleItemPosition);

                if (visibleItemCount > 0 && linearLayoutManager.findLastVisibleItemPosition() == totalItemCount - 1) {
                    mIsEnd = true;
                } else {
                    mIsEnd = false;
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (mIsEnd) {
                        Toast.makeText(MainActivity.this, "滑到底", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        EventBus.getDefault().register(this);


//        ListAdapter listAdapter = new ListAdapter(this, mStringList);
//        mListView.setAdapter(listAdapter);



    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void startKeyStoreActivity(int isStart){
        Log.i("keystore", "startKeyStoreActivity " + isStart);
        if (isStart == 1){
            Intent intent = new Intent(this, KeyStoreActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
}
