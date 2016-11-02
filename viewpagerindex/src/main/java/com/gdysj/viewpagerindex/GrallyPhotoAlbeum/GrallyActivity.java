package com.gdysj.viewpagerindex.GrallyPhotoAlbeum;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gdysj.viewpagerindex.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class GrallyActivity extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClickListener {

    private ViewPager mViewPager;
    private GrallyPagerAdapter mPagerAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    private List<GrallyItemBean> mList;

    private CirclePageIndicator circle_pager_indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grally);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        circle_pager_indicator = (CirclePageIndicator) findViewById(R.id.circle_pager_indicator);


        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);

        mList = new ArrayList<>(0);
        mAdapter = new RecyclerAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);

        initData();
        mAdapter.update(mList);
        mAdapter.setItemClickListener(this);

        mPagerAdapter = new GrallyPagerAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mPagerAdapter);

        circle_pager_indicator.setFillColor(getResources().getColor(R.color.colorPrimary));
        circle_pager_indicator.setStrokeColor(getResources().getColor(R.color.vpi__background_holo_dark));
        circle_pager_indicator.setViewPager(mViewPager);

    }

    private void initData(){
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("http://img1.3lian.com/2015/w7/85/d/101.jpg");
        list1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1478674748&di=c520df630dda6a950bc2205d8630842a&imgtype=jpg&src=http%3A%2F%2Fpic38.nipic.com%2F20140227%2F9320645_161155511164_2.jpg");
        list1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1478674771&di=c0d6064c7de8924b852da0e167c1510b&imgtype=jpg&src=http%3A%2F%2Fpic32.nipic.com%2F20130813%2F9422601_092422903000_2.jpg");
        list1.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1478674812&di=a9a2cfab6f840ed1bae7a679d5c9e5d6&imgtype=jpg&src=http%3A%2F%2Fres.img.ifeng.com%2F448a15488f7d9a51%2F2011%2F0620%2Frdn_4dfea29122f70.jpg");
        GrallyItemBean bean1 = new GrallyItemBean();
        bean1.setImageUrl("http://img1.3lian.com/2015/w7/85/d/101.jpg");
        bean1.setItemContent("兔子");
        bean1.setPosition(0);
        bean1.setContentImageUrlsList(list1);
        mList.add(bean1);

        ArrayList<String> list2 = new ArrayList<>();
        list2.add("http://pic54.nipic.com/file/20141126/17961491_093029437000_2.jpg");
        list2.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1478674878&di=f97e863ea4964888a6b27f320d435c78&imgtype=jpg&src=http%3A%2F%2Fpic74.nipic.com%2Ffile%2F20150803%2F9885883_103255410001_2.jpg");
        list2.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1478674901&di=9c04a1583c74427978b6bc5f93929018&imgtype=jpg&src=http%3A%2F%2Fimgq.duitang.com%2Fuploads%2Fitem%2F201112%2F22%2F20111222163012_jYK4N.thumb.700_0.jpg");

        GrallyItemBean bean2 = new GrallyItemBean();
        bean2.setImageUrl("http://pic54.nipic.com/file/20141126/17961491_093029437000_2.jpg");
        bean2.setItemContent("铁路");
        bean2.setPosition(2);
        bean2.setContentImageUrlsList(list2);
        mList.add(bean2);

        ArrayList<String> list3 = new ArrayList<>();
        list3.add("http://img5.duitang.com/uploads/item/201512/19/20151219231445_e8VFP.jpeg");
        list3.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1478674953&di=6dabb1e1b570b873b3d63d73ae11a74a&imgtype=jpg&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F71cf3bc79f3df8dc39cb6295cf11728b461028c4.jpg");
        list3.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1478674976&di=948467930b35fbade8e629855c77a10f&imgtype=jpg&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fe850352ac65c1038cf7938c4b0119313b07e89b5.jpg");
        list3.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1478675019&di=15694bc823572df1ab194e45b8ef503e&imgtype=jpg&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fe824b899a9014c08838032ab087b02087bf4f401.jpg");
        GrallyItemBean bean3 = new GrallyItemBean();
        bean3.setImageUrl("http://img5.duitang.com/uploads/item/201512/19/20151219231445_e8VFP.jpeg");
        bean3.setItemContent("美女");
        bean3.setPosition(3);
        bean3.setContentImageUrlsList(list3);
        mList.add(bean3);

        ArrayList<String> list4 = new ArrayList<>();
        list4.add("http://p2.wmpic.me/article/2015/12/30/1451446087_VEZlIbVy.jpg");
        list4.add("http://pic34.nipic.com/20131022/4072674_103722612149_2.jpg");
        list4.add("http://pic6.nipic.com/20100423/1412106_172212057869_2.jpg");
        GrallyItemBean bean4 = new GrallyItemBean();
        bean4.setImageUrl("http://p2.wmpic.me/article/2015/12/30/1451446087_VEZlIbVy.jpg");
        bean4.setItemContent("沙漠");
        bean4.setPosition(4);
        bean4.setContentImageUrlsList(list4);
        mList.add(bean4);

        ArrayList<String> list5 = new ArrayList<>();
        list5.add("http://www.people.com.cn/mediafile/pic/20141230/51/6443510296760649455.jpg");
        list5.add("http://img15.3lian.com/2015/h1/296/d/63.jpg");
        list5.add("http://rescdn.qqmail.com/dyimg/20131108/7A1C476F9196.jpg");
        GrallyItemBean bean5 = new GrallyItemBean();
        bean5.setImageUrl("http://www.people.com.cn/mediafile/pic/20141230/51/6443510296760649455.jpg");
        bean5.setItemContent("宠物");
        bean5.setPosition(5);
        bean5.setContentImageUrlsList(list5);
        mList.add(bean5);
    }

    @Override
    public void onClickListener(GrallyItemBean bean) {
        int position = mList.indexOf(bean);
        mViewPager.setCurrentItem(position);
    }
}
