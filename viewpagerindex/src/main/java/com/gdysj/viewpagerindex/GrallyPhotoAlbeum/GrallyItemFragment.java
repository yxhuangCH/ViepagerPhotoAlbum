package com.gdysj.viewpagerindex.GrallyPhotoAlbeum;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

/**
 * Author: yxhuang
 * Date: 2016/11/2
 * Email: yxhuang@gmail.com
 */

public class GrallyItemFragment extends Fragment{

    private static final String IMAGE_URL = "image_url";

    public static GrallyItemFragment newInstance(@NonNull String imageUrl){
        GrallyItemFragment fragment = new GrallyItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMAGE_URL, imageUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String imageUrl = (String) getArguments().get(IMAGE_URL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ImageView imageView = new ImageView(getActivity());
        imageView.setLayoutParams(params);
        imageView.setPadding(20, 20, 20, 20);

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.addView(imageView);

        Glide.with(getActivity()).load(imageUrl).into(imageView);

        return layout;

    }
}
