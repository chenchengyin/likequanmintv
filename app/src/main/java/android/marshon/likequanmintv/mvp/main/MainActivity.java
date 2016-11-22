package android.marshon.likequanmintv.mvp.main;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.librarys.base.BaseActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/21.
 */

public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private CommonTabLayout mTablayout;
    private ColumnFragment columnFragment=ColumnFragment.newInstance();
    private LiveFragment liveFragment=LiveFragment.newInstance();
    private RecommendFragment recommendFragment=RecommendFragment.newInstance();
    private ProfileFragment profileFragment=ProfileFragment.newInstance();
    private String[] mTitles=new String[]{"推荐","栏目","直播","我的"};
    private int[] mIconSelectIds=new int[]{R.drawable.btn_tabbar_home_selected,R.drawable.btn_tabbar_lanmu_selected,
            R.drawable.btn_tabbar_zhibo_selected,R.drawable.btn_tabbar_wode_selected};
    private int[] mIconUnselectIds=new int[]{R.drawable.btn_tabbar_home_normal,R.drawable.btn_tabbar_lanmu_normal,
            R.drawable.btn_tabbar_zhibo_normal,R.drawable.btn_tabbar_wode_normal};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTablayout =(CommonTabLayout)findViewById(R.id.tablayout);
        iniTab();
    }

    private void iniTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTablayout.setTabData(mTabEntities);
        mTablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    mTablayout.showMsg(0, 100 + 1);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });
        mViewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mTablayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mViewPager.setCurrentItem(0);
    }

    private class MainPagerAdapter extends FragmentStatePagerAdapter {
        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return recommendFragment;
                case 1:
                    return columnFragment;
                case 2:
                    return liveFragment;
                default:
                    return profileFragment;
            }
        }
    }

    public class TabEntity implements CustomTabEntity {
        public String title;
        public int selectedIcon;
        public int unSelectedIcon;

        public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }
    }

}
