package com.gdysj.viewpagerindex.GrallyPhotoAlbeum;

import java.util.ArrayList;

/**
 * Author: yxhuang
 * Date: 2016/11/2
 * Email: yxhuang@gmail.com
 */

public class GrallyItemBean {
    private String mItemContent;
    private String mImageUrl;
    private ArrayList<String> mContentImageUrlsList;

    public ArrayList<String> getContentImageUrlsList() {
        return mContentImageUrlsList;
    }

    public void setContentImageUrlsList(ArrayList<String> contentImageUrlsList) {
        mContentImageUrlsList = contentImageUrlsList;
    }

    public String getItemContent() {
        return mItemContent;
    }

    public void setItemContent(String itemContent) {
        mItemContent = itemContent;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
