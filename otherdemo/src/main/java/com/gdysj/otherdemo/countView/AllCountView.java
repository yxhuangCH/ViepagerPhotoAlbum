package com.gdysj.otherdemo.countView;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.gdysj.otherdemo.R;

/**
 * Author: yxhuang
 * Date: 2016/11/17
 * Email: yxhuang@gmail.com
 */

public class AllCountView extends LinearLayout {
    private Context mContext;

    private int mOrientation = HORIZONTAL;
    private int mCountViewNumber = 6;
    private float mTextSize = 16 ;
    private int mTextColor = Color.BLACK;

    private int mPaddingLeft = 25;
    private int mPaddingTop = 10;
    private int mPaddingRight = 25;
    private int mPaddingBottom = 10;

    private int mViewGap = 10;
    private int mAnimationTime = 500;


    public AllCountView(Context context) {
        super(context);
        initView(context);
    }

    public AllCountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AllCountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        mContext = context;
        this.setOrientation(mOrientation);

        for (int i = 0; i < mCountViewNumber; i++){
            CountTextView countTextView = new CountTextView(mContext);
            countTextView.setText(mTextSize, Color.BLACK);
            countTextView.setTextPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
            countTextView.setAnimTime(mAnimationTime);
            countTextView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.count_view_shape));

            LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(mViewGap, 0, 0, 0);
            addView(countTextView, params);
        }
    }

    public void setOrientation(int orientation){
        mOrientation = orientation;
    }

    public void setCountViewNumber(int number){
        mCountViewNumber = number;
    }

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

    public void setContent(@NonNull String content){
        if (TextUtils.isEmpty(content)){
            return;
        }

        int contentLength = content.length();
        int childViewCount = getChildCount() - 1;

        if (contentLength > 6){ // 长度大于 6 位，变成 999999
            content = "999999";
            contentLength = content.length();

        } else if (contentLength < 6){  // 长度小于 6 位，前面补 0， 变成 6 位；
            do{
                content = "0" + content;
                contentLength = content.length();

            } while (contentLength < 6);
        }

        for (int i = contentLength - 1; i >= 0; i--){
            char c = content.charAt(i); // 数组

             CountTextView countTextView = (CountTextView) getChildAt(childViewCount); // 视图

             String charString = String.valueOf(c);
             String textString = countTextView.getTextView().getText().toString();

            if (!TextUtils.equals(charString, textString)){
                 countTextView.setCountText(charString);
            }

            childViewCount--;
        }

    }
}
