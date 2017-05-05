package cash.juzhongke.com.gesturedemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Author: yxhuang
 * Date: 2017/4/19
 * Email: yxhuang@gmail.com
 */

public class SqView extends RelativeLayout{

    public SqView(Context context) {
        super(context);
        initView(context);
    }

    public SqView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SqView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        inflate(context, R.layout.sq_layout, this);
    }
}
