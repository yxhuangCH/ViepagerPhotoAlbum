package cash.juzhongke.com.gesturedemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/**
 * Author: yxhuang
 * Date: 2017/4/20
 * Email: yxhuang@gmail.com
 */

public class CustomView extends View{

    private static final String TAG = "CustomView";
    private int lastX;
    private int lastY;

    private int mScrolLimit;

    private static final int mMaxWeithAndHeight = 500;
    private static final int mMinWeithAndHeitht = 200;



    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        iniView(context);
    }
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        iniView(context);
    }

    public CustomView(Context context) {
        super(context);
        iniView(context);
    }

    private void iniView(Context context){
        mScrolLimit = ViewConfiguration.get(context).getScaledTouchSlop();
    }
    public boolean onTouchEvent(MotionEvent event) {
        //获取到手指处的横坐标和纵坐标
        int x = (int) event.getX();
        int y = (int) event.getY();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                //计算移动的距离
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                //调用layout方法来重新放置它的位置
//                layout(getLeft()+offsetX, getTop()+offsetY,
//                        getRight()+offsetX , getBottom()+offsetY);

//                if (offsetY > 10){
//                    scaView(-30);
//                } else if (offsetY < 10){
//                    scaView(30);
//                }

                break;
        }

        return true;
    }

    public void scaView(int factor){
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        Log.i(TAG, "scaView with " + layoutParams.width );

        if (layoutParams.width <= mMaxWeithAndHeight){
            layoutParams.width = layoutParams.width + factor;
            layoutParams.height = layoutParams.height + factor * 2 ;
            setLayoutParams(layoutParams);
            requestLayout();
            Log.i(TAG, "scaView requestLayout ");
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}