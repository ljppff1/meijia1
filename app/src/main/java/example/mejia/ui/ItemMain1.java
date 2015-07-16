package example.mejia.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import example.mejia.fragment.GridViewFragment;
import example.mejia.fragment.ListViewFragment;
import github.chenupt.dragtoplayout.DragTopLayout;
import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import main.ljppff.com.meijia1.R;

/**
 * Created by liujun on 15-7-15.
 */
public class ItemMain1 extends FragmentActivity {

    private ModelPagerAdapter adapter;
    private ViewPager viewPager;
    private PagerSlidingTabStrip tabs;
    private ImageView mIVIMback;
    private Button mTvImWen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.itemmain1);

        viewPager = (ViewPager) findViewById(R.id.view_pager1);
        viewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager()));

        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs1);
        tabs.setViewPager(viewPager);

        mIVIMback =(ImageView)this.findViewById(R.id.mIVIMback);
        mIVIMback.setOnClickListener(listener);
        mTvImWen =(Button)this.findViewById(R.id.mTvImWen);
        mTvImWen.setOnClickListener(listener);

//       PagerModelManager factory = new PagerModelManager();
//        factory.addCommonFragment(getFragments(), getTitles());
//        adapter = new ModelPagerAdapter(getSupportFragmentManager(), factory);
//        viewPager.setAdapter(adapter);
//       pagerSlidingTabStrip.setViewPager(viewPager);


    }
    class myPagerAdapter extends FragmentPagerAdapter {
        String[] title = { "综合排序", "人气优先", "均价排序"};


        public myPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Fragment     fragment1 = new ListViewFragment();
                    return fragment1;
                case 1:
                    Fragment     fragment2 = new ListViewFragment();
                    return fragment2;
                case 2:
                    Fragment    fragment3 = new ListViewFragment();
                    return fragment3;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {

            return title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.mIVIMback:
                    finish();
                    break;
                case R.id.mTvImWen:
                    startActivity(new Intent(ItemMain1.this,ItemMain.class));
                    finish();

                    break;

                default:
                    break;
            }
        }
    };

    private List<String> getTitles(){
        return Lists.newArrayList("综合排序", "人气优先", "均价排序");
    }

    private List<Fragment> getFragments(){
        List<Fragment> list = new ArrayList<>();
        Fragment gridViewFragment1 = new ListViewFragment();
        Fragment gridViewFragment2 = new ListViewFragment();
        Fragment gridViewFragment3 = new ListViewFragment();
        list.add(gridViewFragment1);
        list.add(gridViewFragment2);
        list.add(gridViewFragment3);
        return list;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



}
