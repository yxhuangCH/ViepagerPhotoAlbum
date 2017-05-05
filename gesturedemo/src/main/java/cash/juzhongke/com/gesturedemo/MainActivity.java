package cash.juzhongke.com.gesturedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;

public class MainActivity extends AppCompatActivity {

    private ScanView mScanView;
    private SqView mSqView;

    private GestureDetector mGestureDetector;

    private CustomGroupView custom_group_view;

    private float mFactor = 0.1f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        custom_group_view = (CustomGroupView) findViewById(R.id.custom_group_view);

    }
}
