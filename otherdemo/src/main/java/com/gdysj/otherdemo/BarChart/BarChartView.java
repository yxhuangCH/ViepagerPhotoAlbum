package com.gdysj.otherdemo.BarChart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: yxhuang
 * Date: 2017/3/20
 * Email: yxhuang@gmail.com
 */

public class BarChartView extends View{
    private static final String TAG = BarChartView.class.getSimpleName();

    private int mRectCount = 3;


    public BarChartView(Context context) {
        super(context);
    }

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BarChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
