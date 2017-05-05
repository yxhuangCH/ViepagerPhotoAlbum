package com.gdysj.pulltorefresh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdysj.pulltorefresh.R;

/**
 * Author: yxhuang
 * Date: 2017/2/7
 * Email: yxhuang@gmail.com
 */

public class MyFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment_layout, null);
        return view;
    }
}
