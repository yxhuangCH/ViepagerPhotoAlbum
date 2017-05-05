package com.gdysj.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Author: yxhuang
 * Date: 2016/12/20
 * Email: yxhuang@gmail.com
 */

public class ListAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    private ViewHolder mViewHolder;


    public ListAdapter(Context context, List<String> list) {
        Log.e("yxh", " _______________内容__________________ ");
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.normal_item, null);

            mViewHolder.textView = (TextView) convertView.findViewById(R.id.tv_normal);

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        Log.e("yxh", " _______________内容 " + mList.get(position));
        mViewHolder.textView.setText(mList.get(position));

        return convertView;
    }

    class ViewHolder{
        TextView textView;
    }
}
