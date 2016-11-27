package android.marshon.likequanmintv.mvp.main;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.adapter.RecommendPagerAdapter;
import android.marshon.likequanmintv.bean.LiveCategory;
import android.marshon.likequanmintv.librarys.mvpbase.BaseMvpFragment;
import android.marshon.likequanmintv.mvp.recommend.RecommendFragmentPresenter;
import android.marshon.likequanmintv.mvp.recommend.RecommendFragmentPresenterImpl;
import android.marshon.likequanmintv.mvp.recommend.RecommendFragmentView;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/11/21.
 */

public class RecommendFragment extends BaseMvpFragment<RecommendFragmentPresenter> implements RecommendFragmentView{


    private ImageView logo;
    private SlidingTabLayout mTablayout;
    private ImageView searchView;
    private ImageView tabManager;
    private ViewPager mViewPager;



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
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

//        mViewPager.setAdapter(new Frag);
//        mTablayout.setViewPager(mViewPager);
//        mTablayout.setCurrentTab(0);

    }

    @Override
    protected void initData() {
        mRecommendFragmentPresenter.getAllCategories();
    }

    @Override
    public void onGetAllCategories(List<LiveCategory> liveCategoryList) {
        mViewPager.setAdapter(new RecommendPagerAdapter(getChildFragmentManager(),liveCategoryList));
        mTablayout.setViewPager(mViewPager);
    }


}
