package android.marshon.likequanmintv.mvp.main;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.librarys.base.BaseActivity;
import android.marshon.likequanmintv.librarys.utils.screen.ScreenUtils;
import android.marshon.likequanmintv.listener.UpDownRvScrollListener;
import android.marshon.likequanmintv.view.lazyvp.LazyViewPager;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/21.
 */

public class MainActivity extends BaseActivity implements UpDownRvScrollListener.UpdownScroll {

    private LazyViewPager mViewPager;
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



    private RelativeLayout mRl_recommend;
    private TextView mTv_recommend;
    private RelativeLayout mRl_column;
    private TextView mTv_column;
    private RelativeLayout mRl_live;
    private TextView mTv_live;
    private RelativeLayout mRl_personal;
    private TextView mTv_personal;
    private int mCurrentIndex;
    private FragmentManager mFragmentManager;
    private LinearLayout bottomBar;

    // End Of Content View Elements

    private void bindViews() {

        bottomBar = (LinearLayout) findViewById(R.id.bottomBar);
        mRl_recommend = (RelativeLayout) findViewById(R.id.rl_recommend);
        mTv_recommend = (TextView) findViewById(R.id.tv_recommend);
        mRl_column = (RelativeLayout) findViewById(R.id.rl_column);
        mTv_column = (TextView) findViewById(R.id.tv_column);
        mRl_live = (RelativeLayout) findViewById(R.id.rl_live);
        mTv_live = (TextView) findViewById(R.id.tv_live);
        mRl_personal = (RelativeLayout) findViewById(R.id.rl_personal);
        mTv_personal = (TextView) findViewById(R.id.tv_personal);

        mRl_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToFragment(0);
            }
        });
        mRl_column.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToFragment(1);
            }
        });
        mRl_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToFragment(2);
            }
        });
        mRl_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToFragment(3);
            }
        });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Debug.startMethodTracing("main");
        setContentView(R.layout.act_main);
        bindViews();
        initData();
        initFragments();
    }


    private void initFragments() {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction ts = mFragmentManager.beginTransaction();
        ts.add(R.id.contentLayout,recommendFragment);
        ts.add(R.id.contentLayout,columnFragment);
        ts.add(R.id.contentLayout,liveFragment);
        ts.add(R.id.contentLayout,profileFragment);
        ts.commitAllowingStateLoss();
        switchToFragment(mCurrentIndex);
    }


    /**
     * 执行切换fragment 的操作
     * 注意：
     * 1. 切换页面的时候，还要调用showBottomBar来保证底部导航栏的显示
     *
     * @param index
     */
    private void switchToFragment(int index) {
        mCurrentIndex = index;
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        clearSelect();
        hideAllExculdeFragments(transaction,index);
        switch (index) {
            case 0:
                mRl_recommend.setSelected(true);
                transaction.show(recommendFragment);

                break;
            case 1:
                mRl_column.setSelected(true);
                transaction.show(columnFragment);
                break;
            case 2:
                mRl_live.setSelected(true);
                transaction.show(liveFragment);
                break;
            case 3:
                mRl_personal.setSelected(true);
                transaction.show(profileFragment);
                break;
        }
        transaction.commit();
    }

    private void clearSelect() {
        mRl_recommend.setSelected(false);
        mRl_column.setSelected(false);
        mRl_live.setSelected(false);
        mRl_personal.setSelected(false);

    }

    /**
     * 隐藏所有的fragment，并且取消所有的底部导航栏的icon的高亮状态
     *
     */
    private void hideAllExculdeFragments(FragmentTransaction transaction, int index) {
        transaction.hide(recommendFragment);
        transaction.hide(columnFragment);
        transaction.hide(liveFragment);
        transaction.hide(profileFragment);

        //停止网络回调
        switch (index){
            case 0:
                columnFragment.stopNetWork();
                liveFragment.stopNetWork();
                profileFragment.stopNetWork();
                break;
            case 1:
                recommendFragment.stopNetWork();
                liveFragment.stopNetWork();
                profileFragment.stopNetWork();

                break;
            case 2:
                recommendFragment.stopNetWork();
                columnFragment.stopNetWork();
                profileFragment.stopNetWork();
                break;
            case 3:
                recommendFragment.stopNetWork();
                columnFragment.stopNetWork();
                liveFragment.stopNetWork();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(closeRunnable);
        Debug.stopMethodTracing();
    }

    private void initData() {
        liveFragment.setUpdownScroll(this);
    }


    @Override
    public boolean isTranslateStatusBar() {
        return true;
    }

    @Override
    public void onShouldDown(boolean shouldDown) {
        if (shouldDown){
            bottomBar.animate().translationY(ScreenUtils.dp2px(54)).setDuration(300).start();
        }else {
            bottomBar.animate().translationY(0).setDuration(300).start();
        }

    }

//    private class MainPagerAdapter extends LazyFragmentPagerAdapter {
//        public MainPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public int getCount() {
//            return 4;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mTitles[position];
//        }
//
//        @Override
//        public Fragment getItem(ViewGroup container,int position) {
//            switch (position){
//                case 0:
//                    return recommendFragment;
//                case 1:
//                    return columnFragment;
//                case 2:
//                    return liveFragment;
//                default:
//                    return profileFragment;
//            }
//        }
//
//    }

    private Handler handler = new Handler();

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (isClikced) {
                    finish();
                    return super.onKeyUp(keyCode, event);
                }
                isClikced = true;
                showToast("再按一次退出");
                handler.postDelayed(closeRunnable, 3000);
                break;
        }
        return false;
    }

    Runnable closeRunnable = new Runnable() {
        @Override
        public void run() {
            isClikced = false;
        }
    };


}
