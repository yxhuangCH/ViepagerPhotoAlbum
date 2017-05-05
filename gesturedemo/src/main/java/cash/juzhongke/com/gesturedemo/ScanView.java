package cash.juzhongke.com.gesturedemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Author: yxhuang
 * Date: 2017/4/19
 * Email: yxhuang@gmail.com
 */

public class ScanView extends RelativeLayout {

    public ScanView(Context context) {
        super(context);

        initView(context);
    }

    public ScanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ScanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        inflate(context, R.layout.scan_layout, this);
    }


}
