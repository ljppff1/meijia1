package example.mejia.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import example.mejia.fragment.GridViewFragment;
import example.mejia.fragment.GridViewFragment1;
import main.ljppff.com.meijia1.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.google.common.collect.Lists;
import de.greenrobot.event.EventBus;

import java.util.ArrayList;
import java.util.List;
import github.chenupt.dragtoplayout.DragTopLayout;
import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;

/**
 * Created by liujun on 15-7-15.
 */
public class ItemMain extends FragmentActivity {

    private DragTopLayout dragLayout;
    private ModelPagerAdapter adapter;
    private ViewPager viewPager;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private ImageView topImageView;
    private Button mBtIM1;
    private ImageView mIVIMback;
    private Button mTvImPeo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.itemmain);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dragLayout = (DragTopLayout) findViewById(R.id.drag_layout);
        pagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mBtIM1 =(Button)this.findViewById(R.id.mBtIM1);
        mIVIMback =(ImageView)this.findViewById(R.id.mIVIMback);
        mIVIMback.setOnClickListener(listener);
        mIVIMback =(ImageView)this.findViewById(R.id.mIVIMback);
        mIVIMback.setOnClickListener(listener);
        mTvImPeo =(Button)this.findViewById(R.id.mTvImPeo);
        mTvImPeo.setOnClickListener(listener);
        mBtIM1.setOnClickListener(listener);

       PagerModelManager factory = new PagerModelManager();
        factory.addCommonFragment(getFragments(), getTitles());
        adapter = new ModelPagerAdapter(getSupportFragmentManager(), factory);
        viewPager.setAdapter(adapter);
       pagerSlidingTabStrip.setViewPager(viewPager);


    }
    View.OnClickListener listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.mBtIM1:
                    Toast.makeText(getApplicationContext(),"adsf",Toast.LENGTH_LONG).show();
                    break;
                case R.id.mIVIMback:
                    finish();
                    break;
                case R.id.mTvImPeo:
                    startActivity(new Intent(ItemMain.this,ItemMain1.class));
                    finish();
                    break;

                default:
                    break;
            }
        }
    };

    private List<String> getTitles(){
        return Lists.newArrayList("综合排序", "销量优先", "价格排序");
    }

    private List<Fragment> getFragments(){
        List<Fragment> list = new ArrayList<>();
        Fragment gridViewFragment1 = new GridViewFragment();
        Fragment gridViewFragment2 = new GridViewFragment();
        Fragment gridViewFragment3 = new GridViewFragment();
        list.add(gridViewFragment1);
        list.add(gridViewFragment2);
        list.add(gridViewFragment3);
        return list;
    }

    // Handle scroll event from fragments
    public void onEvent(Boolean b){
        dragLayout.setTouchMode(b);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }



}
