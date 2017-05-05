package com.gdysj.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Author: yxhuang
 * Date: 2016/12/9
 * Email: yxhuang@gmail.com
 */

public class HeaderView extends LinearLayout {
    public HeaderView(Context context) {
        super(context);
        initView(context);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        inflate(context, R.layout.header_layout, this);
    }

}
