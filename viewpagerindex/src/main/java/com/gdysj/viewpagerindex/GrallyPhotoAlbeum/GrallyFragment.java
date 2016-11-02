package com.gdysj.viewpagerindex.GrallyPhotoAlbeum;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gdysj.viewpagerindex.R;

import java.util.ArrayList;

/**
 * Author: yxhuang
 * Date: 2016/11/2
 * Email: yxhuang@gmail.com
 */

public class GrallyFragment extends Fragment {

    private static final String IMAGE_URL_LIST = "image_url_list";

    public static GrallyFragment newInstance(@NonNull GrallyItemBean bean){
        GrallyFragment fragment = new GrallyFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(IMAGE_URL_LIST, bean.getContentImageUrlsList());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null){
            return null;
        }

        ArrayList<String> imageUrlList = getArguments().getStringArrayList(IMAGE_URL_LIST);
        if (imageUrlList == null || imageUrlList.size() == 0){
            Log.i("Grally", "GrallyFragment imageUrlList is null");
            return null;
        }

        LinearLayout.LayoutParams viewPagerLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ViewPager viewPager = new ViewPager(getActivity());
        viewPager.setId(R.id.custom_viewpager);
        viewPager.setLayoutParams(viewPagerLayoutParams);

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layout.addView(viewPager);

        GrallyFragmentItemVPAdapter grallyFragmentItemVPAdapter = new GrallyFragmentItemVPAdapter(getChildFragmentManager(), imageUrlList);
        viewPager.setAdapter(grallyFragmentItemVPAdapter);

        return layout;
    }



}
