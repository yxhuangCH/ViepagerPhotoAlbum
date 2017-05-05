package com.gdysj.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Author: yxhuang
 * Date: 2016/10/28
 * Email: yxhuang@gmail.com
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;

    private Context mContext;
    private List<String> mList;

    public RecyclerAdapter(Context context, List<String> list){
        mContext = context;
        update(list);
    }

    public void update(List<String> list){
        mList = list;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER){
            View headerView = LayoutInflater.from(mContext).inflate(R.layout.header_item, parent, false);
            return new HeaderHolder(headerView);
        } else if (viewType == TYPE_NORMAL){
            View normalView = LayoutInflater.from(mContext).inflate(R.layout.normal_item, parent, false);
            return new NormalHolder(normalView);
        } else if (viewType == TYPE_FOOTER){
            View footerView = LayoutInflater.from(mContext).inflate(R.layout.footer_item, parent, false);
            return new FooterHolder(footerView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderHolder){
            HeaderHolder headerHolder = (HeaderHolder) holder;
            headerHolder.mHeaderText.setText("Header View");
            headerHolder.mHeaderText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "header view", Toast.LENGTH_SHORT).show();

                    EventBus.getDefault().post(1);
                    Intent intent = new Intent(mContext, KeyStoreActivity.class);
                    mContext.startActivity(intent);
                }
            });
        } else if (holder instanceof NormalHolder){
            NormalHolder normalHolder = (NormalHolder) holder;
            normalHolder.mNormalText.setText(mList.get(position -1 ));  // 记得 -1 ,否则会越界
            normalHolder.mNormalText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "normal " + mList.get(position), Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof FooterHolder){
            FooterHolder footerHolder = (FooterHolder) holder;
            footerHolder.mFooterText.setText("Footer View");
            footerHolder.mFooterText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "footer view", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size() + 2;
    }

    // 需要重写这个方法
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)){
            return TYPE_HEADER;
        } else if (isPositionFooter(position)){
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    private boolean isPositionHeader(int position){
        return position == 0;
    }

    private boolean isPositionFooter(int position){
        return position == mList.size() + 1;
    }


    class HeaderHolder extends RecyclerView.ViewHolder{
        TextView mHeaderText;

        public HeaderHolder(View itemView) {
            super(itemView);
            mHeaderText = (TextView) itemView.findViewById(R.id.tv_header);
        }
    }

    class NormalHolder extends RecyclerView.ViewHolder{
        TextView mNormalText;

        public NormalHolder(View itemView) {
            super(itemView);
            mNormalText = (TextView) itemView.findViewById(R.id.tv_normal);
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder{
        TextView mFooterText;

        public FooterHolder(View itemView) {
            super(itemView);
            mFooterText = (TextView) itemView.findViewById(R.id.tv_footer);
        }
    }

}
