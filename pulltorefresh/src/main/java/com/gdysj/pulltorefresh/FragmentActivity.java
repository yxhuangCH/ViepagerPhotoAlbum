package com.gdysj.pulltorefresh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        MainFragment mainFragment = new MainFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, mainFragment).commit();
    }
}
