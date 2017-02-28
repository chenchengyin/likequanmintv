package android.marshon.likequanmintv.mvp.main;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.adapter.LiveFragmentListAdapter;
import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.librarys.mvpbase.BaseMvpFragment;
import android.marshon.likequanmintv.listener.UpDownRvScrollListener;
import android.marshon.likequanmintv.mvp.live.LiveFragmentPresenter;
import android.marshon.likequanmintv.mvp.live.LiveFragmentPresenterImpl;
import android.marshon.likequanmintv.mvp.live.LiveFragmentView;
import android.marshon.likequanmintv.view.lazyvp.LazyFragmentPagerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.adapter.recyclerview.wrapper.LoadMoreCommonAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/11/21.
 */

public class LiveFragment extends BaseMvpFragment<LiveFragmentPresenter> implements
        LiveFragmentView, LoadMoreCommonAdapter.OnLoadMoreListener ,LazyFragmentPagerAdapter.Laziable{


    private RecyclerView mRv;
    private List<PlayBean> mPlayBeanListHolderList =new ArrayList<PlayBean>();
    private LiveFragmentListAdapter mAdapter;
    private UpDownRvScrollListener.UpdownScroll mUpdownScroll;

    public static LiveFragment newInstance() {
        
        Bundle args = new Bundle();
        LiveFragment fragment = new LiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    LiveFragmentPresenterImpl liveFragmentPresenter;

    @Override
    protected LiveFragmentPresenterImpl initInjector() {
         mFragmentComponent.inject(this); //初始化
        return liveFragmentPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_live;
    }

    @Override
    protected void initView(View rootView) {
        mRv=(RecyclerView)rootView.findViewById(R.id.mRv);
        if (mUpdownScroll!=null){
            mRv.addOnScrollListener(new UpDownRvScrollListener(mUpdownScroll));
        }
        mRv.setLayoutManager(new GridLayoutManager(mActivity,2));
//        mRv.setAnimationCacheEnabled(true);
//        mRv.setItemAnimator(new DefaultItemAnimator());
        mAdapter=new LiveFragmentListAdapter(mActivity,R.layout.listitem_live, mPlayBeanListHolderList);
        mAdapter.setOnLoadMoreListener(this);
        mRv.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void initData() {
        //loadata
        liveFragmentPresenter.loadPlayList();
    }


    @Override
    public void onPlayBeanList(List<PlayBean> playBeanListHolderList) {
        mAdapter.refreshDatas(playBeanListHolderList);
    }

    @Override
    public void onLoadMoreRequested() {
        liveFragmentPresenter.addMorePlayList();
    }

    public void setUpdownScroll(UpDownRvScrollListener.UpdownScroll updownScroll){
        mUpdownScroll = updownScroll;

    }
}
