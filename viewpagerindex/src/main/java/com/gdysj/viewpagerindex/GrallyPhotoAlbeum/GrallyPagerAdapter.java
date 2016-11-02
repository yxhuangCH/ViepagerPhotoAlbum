package com.gdysj.viewpagerindex.GrallyPhotoAlbeum;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Author: yxhuang
 * Date: 2016/11/2
 * Email: yxhuang@gmail.com
 */

public class GrallyPagerAdapter extends FragmentPagerAdapter {

    private List<GrallyItemBean> mList;

    public GrallyPagerAdapter(FragmentManager fm, List<GrallyItemBean> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return GrallyFragment.newInstance(mList.get(position));
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
