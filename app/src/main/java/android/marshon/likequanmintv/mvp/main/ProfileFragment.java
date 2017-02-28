package android.marshon.likequanmintv.mvp.main;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.base.BaseFragment;
import android.marshon.likequanmintv.view.lazyvp.LazyFragmentPagerAdapter;
import android.marshon.likequanmintv.view.pulltozoomview.PullToZoomScrollViewEx;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


/**
 * Created by Administrator on 2016/11/21.
 */

public class ProfileFragment extends BaseFragment implements LazyFragmentPagerAdapter.Laziable{


    private PullToZoomScrollViewEx scrollView;

    public static ProfileFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.frg_profilecenter;
    }

    @Override
    protected void initView(View rootview) {


        scrollView=(PullToZoomScrollViewEx)rootview.findViewById(R.id.scrollView);
        View headerview=View.inflate(mActivity,R.layout.widget_profile_headview,null);

        View zoomview=View.inflate(mActivity,R.layout.widget_zoomview,null);
        View contentview=View.inflate(mActivity,R.layout.widget_profile_contentview,null);

        scrollView.setHeaderView(headerview);
        scrollView.setZoomView(zoomview);
        scrollView.setScrollContentView(contentview);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void initData() {

    }
}
