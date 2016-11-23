package android.marshon.likequanmintv.mvp.main;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.adapter.LiveFragmentListAdapter;
import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.librarys.mvpbase.BaseMvpFragment;
import android.marshon.likequanmintv.mvp.recommend.LiveFragmentPresenter;
import android.marshon.likequanmintv.mvp.recommend.LiveFragmentPresenterImpl;
import android.marshon.likequanmintv.mvp.recommend.LiveFragmentView;
import android.os.Bundle;
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

public class LiveFragment extends BaseMvpFragment<LiveFragmentPresenter> implements LiveFragmentView, LoadMoreCommonAdapter.OnLoadMoreListener {


    private RecyclerView mRv;
    private List<PlayBean> mPlayBeanListHolderList =new ArrayList<PlayBean>();
    private LiveFragmentListAdapter mAdapter;

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
        mRv.setLayoutManager(new GridLayoutManager(mActivity,2));
        mAdapter=new LiveFragmentListAdapter(mActivity,R.layout.listitem_live, mPlayBeanListHolderList);
        mAdapter.setOnLoadMoreListener(this);
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        //loadata
        liveFragmentPresenter.onCreate();
    }


    @Override
    public void onPlayBeanList(List<PlayBean> playBeanListHolderList) {
        mAdapter.refreshDatas(playBeanListHolderList);
    }

    @Override
    public void onLoadMoreRequested() {
        liveFragmentPresenter.addMorePlayList();
    }
}
