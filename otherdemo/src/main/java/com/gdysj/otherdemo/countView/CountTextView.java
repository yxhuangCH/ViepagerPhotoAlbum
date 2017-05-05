package com.gdysj.otherdemo.countView;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 * Author: yxhuang
 * Date: 2016/11/17
 * Email: yxhuang@gmail.com
 */

public class CountTextView extends TextSwitcher implements ViewSwitcher.ViewFactory {

    private Context mContext;
    private TextView mTextView;

    private float mTextSize = 16 ;
    private int mTextColor = Color.BLACK;

    private int mPaddingLeft = 5;
    private int mPaddingTop = 5;
    private int mPaddingRight = 5;
    private int mPaddingBottom = 5;

    /**
     * @param textSize 字号
     * @param textColor 字体颜色
     */
    public void setText(float textSize, int textColor) {
        mTextSize = textSize;
        mTextColor = textColor;
    }

    public void setTextPadding(int padding){
        setTextPadding(padding, padding, padding, padding);
    }

    public void setTextPadding(int left, int top, int right, int bottom){
        mPaddingLeft = left;
        mPaddingTop = top;
        mPaddingRight = right;
        mPaddingBottom = bottom;
    }

    public CountTextView(Context context) {
        super(context);
        mContext = context;
    }

    public CountTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public TextView getTextView(){
        return mTextView;
    }

    @Override
    public View makeView() {
        mTextView = new TextView(mContext);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setMaxLines(1);
        mTextView.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
        mTextView.setTextColor(mTextColor);
        mTextView.setTextSize(mTextSize);
        mTextView.setText("0");  // 初始值
        return mTextView;
    }

    public void setCountText(@NonNull String text){
        setText(text);
    }

    public void setAnimTime(@NonNull long animDuration) {
        setFactory(this);
        Animation in = new TranslateAnimation(0, 0, animDuration, 0);
        in.setDuration(animDuration);
        in.setInterpolator(new AccelerateInterpolator());
        Animation out = new TranslateAnimation(0, 0, 0, -animDuration);
        out.setDuration(animDuration);
        out.setInterpolator(new AccelerateInterpolator());
        setInAnimation(in);
        setOutAnimation(out);
    }
}
