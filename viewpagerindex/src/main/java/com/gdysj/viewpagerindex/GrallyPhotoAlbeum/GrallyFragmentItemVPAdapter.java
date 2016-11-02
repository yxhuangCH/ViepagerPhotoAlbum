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

public class GrallyFragmentItemVPAdapter extends FragmentPagerAdapter {

    private List<String> mImageUrlList;

    public GrallyFragmentItemVPAdapter(FragmentManager fm, List<String> list) {
        super(fm);
        mImageUrlList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return GrallyItemFragment.newInstance(mImageUrlList.get(position));
    }

    @Override
    public int getCount() {
        return mImageUrlList.size();
    }

}
