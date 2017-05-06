package cash.juzhongke.com.gestureCollapsing;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import cash.juzhongke.com.gesturedemo.R;

public class CollapsingActivity extends AppCompatActivity {

    private static final String TAG = CollapsingActivity.class.getSimpleName();

    private float mSelfHeight = 0;//用以判断是否得到正确的宽高值
    private float mTitleScale;
    private float mSubScribeScale;
    private float mSubScribeScaleX;
    private float mHeadImgScale;

    ImageView mHeadImage;
    TextView mSubscriptionTitle;
    TextView mSubscribe;
    AppBarLayout mAppBar;
    LinearLayout ll_content;
    CoordinatorLayout coordinator_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing);
        ButterKnife.bind(this);

        mHeadImage = (ImageView) findViewById(R.id.iv_head);
        mSubscriptionTitle = (TextView) findViewById(R.id.subscription_title);
        mSubscribe = (TextView) findViewById(R.id.subscribe);
        mAppBar = (AppBarLayout) findViewById(R.id.app_bar);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        coordinator_layout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

        final float screenW = getResources().getDisplayMetrics().widthPixels;
        final float toolbarHeight = 0;
        final float initHeight = 200;
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (mSelfHeight == 0) {
                    mSelfHeight = mSubscriptionTitle.getHeight();
                    float distanceTitle = mSubscriptionTitle.getTop() + (mSelfHeight - toolbarHeight) / 2.0f;
                    float distanceSubscribe = mSubscribe.getY() + (mSubscribe.getHeight() - toolbarHeight) / 2.0f;
                    float distanceHeadImg = mHeadImage.getY() + (mHeadImage.getHeight() - toolbarHeight) / 2.0f;
                    float distanceSubscribeX = screenW / 2.0f - (mSubscribe.getWidth() / 2.0f + 10);
                    mTitleScale = distanceTitle / (initHeight - toolbarHeight);
                    mSubScribeScale = distanceSubscribe / (initHeight - toolbarHeight);
                    mHeadImgScale = distanceHeadImg / (initHeight - toolbarHeight);
                    mSubScribeScaleX = distanceSubscribeX / (initHeight - toolbarHeight);
                }


                float scale = (1.0f + (-verticalOffset) / (initHeight - toolbarHeight)) * 5f;

                Log.e(TAG, " verticalOffset " + verticalOffset);

                verticalOffset = verticalOffset <= -100 ? -100 : verticalOffset;

                float converScale = converScale(verticalOffset);

                mHeadImage.setScaleX(converScale);
                mHeadImage.setScaleY(converScale);

                mHeadImage.setTranslationY(mHeadImgScale * verticalOffset);
                mSubscriptionTitle.setTranslationY(mTitleScale * verticalOffset);
                mSubscribe.setTranslationY(mSubScribeScale * verticalOffset);
                mSubscribe.setTranslationX(-mSubScribeScaleX * verticalOffset);

                float scaleAlpha = 1.0f - (-verticalOffset) / (initHeight - toolbarHeight);

                Log.i(TAG, " scale " + scale + "  verticalOffset " + verticalOffset + " scaleAlpha: " + scaleAlpha +
                            "  mHeadImgScale: " + mHeadImgScale  + "  mSelfHeight: " + mSelfHeight + "  converScale: " + converScale);

                coordinator_layout.setAlpha(scaleAlpha);

            }
        });

    }

    /**
     *  根据距离换算成 Scale
     * @param verticalOffset
     * @return
     *  距离 [0,  -100]
     *  scale [5.0, 7.0]
     */
    private float converScale(int verticalOffset){
        verticalOffset = Math.abs(verticalOffset) >= 100 ? 100 : verticalOffset;
        return 0.02f * verticalOffset;
    }

}
