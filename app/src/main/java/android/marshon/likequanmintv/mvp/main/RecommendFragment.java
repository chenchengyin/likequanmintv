package android.marshon.likequanmintv.mvp.main;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.adapter.RecommendPagerAdapter;
import android.marshon.likequanmintv.bean.LiveCategory;
import android.marshon.likequanmintv.librarys.mvpbase.BaseMvpFragment;
import android.marshon.likequanmintv.mvp.recommend.RecommendFragmentPresenter;
import android.marshon.likequanmintv.mvp.recommend.RecommendFragmentPresenterImpl;
import android.marshon.likequanmintv.mvp.recommend.RecommendFragmentView;
import android.marshon.likequanmintv.view.lazyvp.LazyFragmentPagerAdapter;
import android.marshon.likequanmintv.view.lazyvp.LazyViewPager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/11/21.
 */

public class RecommendFragment extends BaseMvpFragment<RecommendFragmentPresenter> implements RecommendFragmentView,LazyFragmentPagerAdapter.Laziable{


    private ImageView logo;
    private SlidingTabLayout mTablayout;
    private ImageView searchView;
    private ImageView tabManager;
    private LazyViewPager mViewPager;
    private RecommendPagerAdapter recommendPagerAdapter;


    public static RecommendFragment newInstance() {
        Bundle args = new Bundle();
        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    RecommendFragmentPresenterImpl mRecommendFragmentPresenter;
    @Override
    protected RecommendFragmentPresenter initInjector() {
         mFragmentComponent.inject(this);
        return mRecommendFragmentPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_recommend;
    }

    @Override
    protected void initView(View rootView) {
        logo = (ImageView) findViewById(R.id.logo);
        searchView = (ImageView) findViewById(R.id.searchView);
        mTablayout = (SlidingTabLayout) findViewById(R.id.mTablayout);
        tabManager = (ImageView) findViewById(R.id.tabManager);
        mViewPager = (LazyViewPager) findViewById(R.id.mViewPager);

//        mViewPager.setAdapter(new Frag);
//        mTablayout.setViewPager(mViewPager);
//        mTablayout.setCurrentTab(0);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void initData() {
        mRecommendFragmentPresenter.getAllCategories();
    }

    @Override
    public void onGetAllCategories(List<LiveCategory> liveCategoryList) {
        recommendPagerAdapter = new RecommendPagerAdapter(getChildFragmentManager(), liveCategoryList);
        mViewPager.setAdapter(recommendPagerAdapter);
        mTablayout.setViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                recommendPagerAdapter.fragmentSparseArray.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
//        mViewPager.setCurrentItem(0);
//        recommendPagerAdapter.fragmentSparseArray.get(0).initData();

    }


    @Override
    public void stopNetWork() {
        super.stopNetWork();
        if (recommendPagerAdapter!=null){
            recommendPagerAdapter.stopNetWork();
        }
    }
}
