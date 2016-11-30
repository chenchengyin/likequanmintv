package android.marshon.likequanmintv.mvp.main;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.librarys.base.BaseActivity;
import android.marshon.likequanmintv.librarys.utils.screen.ScreenUtils;
import android.marshon.likequanmintv.listener.UpDownRvScrollListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/21.
 */

public class MainActivity extends BaseActivity implements UpDownRvScrollListener.UpdownScroll {

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
    private boolean isClikced;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTablayout =(CommonTabLayout)findViewById(R.id.tablayout);
        iniTab();
        initData();
    }

    private void initData() {
        liveFragment.setUpdownScroll(this);
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
                if (position!=2){
                    mTablayout.animate().translationY(0).setDuration(300).start();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mViewPager.setCurrentItem(0);
    }

    @Override
    public boolean isTranslateStatusBar() {
        return true;
    }

    @Override
    public void onShouldDown(boolean shouldDown) {
        if (shouldDown){
            mTablayout.animate().translationY(ScreenUtils.dp2px(54)).setDuration(300).start();
        }else {
            mTablayout.animate().translationY(0).setDuration(300).start();
        }

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

    private Handler handler = new Handler();

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (isClikced) {
//                    AppManager.getAppManager().AppExit(this);
                    finish();
//                    System.exit(0);
                    return super.onKeyUp(keyCode, event);
                }
                isClikced = true;
                showToast("再按一次退出");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isClikced = false;
                    }
                }, 3000);
                break;

        }

        return false;

    }
}
