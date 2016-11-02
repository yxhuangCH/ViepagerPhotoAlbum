package com.gdysj.viewpagerindex.GrallyPhotoAlbeum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gdysj.viewpagerindex.R;

import java.util.List;

/**
 * Author: yxhuang
 * Date: 2016/11/2
 * Email: yxhuang@gmail.com
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.GrallyViewHolder>{

    private Context mContext;
    private List<GrallyItemBean> mList;

    private RecyclerItemClickListener mItemClickListener;

    public RecyclerAdapter(Context context, List<GrallyItemBean> list) {
        mContext = context;
        update(list);
    }

    public void update(List<GrallyItemBean> list){
        mList = list;
        notifyDataSetChanged();
    }

    public void setItemClickListener(RecyclerItemClickListener listener){
        mItemClickListener = listener;
    }

    @Override
    public GrallyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_grally, parent, false);
        return new GrallyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GrallyViewHolder holder, int position) {
        final GrallyItemBean bean = mList.get(position);
        Glide.with(mContext).load(bean.getImageUrl()).into(holder.imageView);
        holder.textView.setText(bean.getItemContent());
        Log.i("Grally", "onBindViewHolder " + bean.getItemContent() + "  " + bean.getImageUrl());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onClickListener(bean);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    class GrallyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;

        public GrallyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_grally_item);
            textView = (TextView) itemView.findViewById(R.id.tv_grally_item);
        }
    }

    interface RecyclerItemClickListener{
        void onClickListener(GrallyItemBean bean);
    }
}
