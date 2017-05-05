package cash.juzhongke.com.gesturedemo;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Author: yxhuang
 * Date: 2017/4/20
 * Email: yxhuang@gmail.com
 */

public class CustomGroupView extends FrameLayout {


    private float mLastX;
    private float mLastY;

    private CustomView mCustomView;

    private static final int sDistanct = 30;

    private int mActiviePointerId;

    public CustomGroupView(Context context) {
        super(context);
        initView(context);
    }

    public CustomGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        inflate(context, R.layout.custom_group_layout, this);

        mCustomView = (CustomView) findViewById(R.id.custom_view);

    }

    public boolean onTouchEvent(MotionEvent event) {
        //获取到手指处的横坐标和纵坐标
       int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                final int pointerIndex = MotionEventCompat.getActionIndex(event);
                final float x = MotionEventCompat.getX(event, pointerIndex);
                final float y = MotionEventCompat.getY(event, pointerIndex);


                mLastX = x;
                mLastY = y;

                // 保存手指的 ID
                mActiviePointerId = MotionEventCompat.getPointerId(event, 0);
                break;

            case MotionEvent.ACTION_MOVE:
                final int pointer = MotionEventCompat.findPointerIndex(event, mActiviePointerId);
                final float xm = MotionEventCompat.getX(event, pointer);
                final float ym = MotionEventCompat.getY(event, pointer);

                final float dx = xm - mLastX;
                final float dy = ym - mLastX;


                if (dy > 0){
                    tranformView(sDistanct);
                } else if (dy < 0){
                    tranformView(-sDistanct);
                }

                Log.i("CustomView", "offsetY " + dy);
                break;
        }

        return true;
    }

    private void tranformView(int distant){

        mCustomView.scaView(distant);

    }

}
