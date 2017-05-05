package com.gdysj.pulltorefresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Author: yxhuang
 * Date: 2016/12/22
 * Email: yxhuang@gmail.com
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.StringViewHolder> {

    private Context mContext;
    private List<String> mStringList;


    public RecyclerAdapter(Context context, List<String> stringList) {
        mContext = context;
        mStringList = stringList;
    }

    @Override
    public StringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, parent, false);
        return new StringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StringViewHolder holder, int position) {
        holder.textView.setText(mStringList.get(position));
    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }



    class StringViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public StringViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
