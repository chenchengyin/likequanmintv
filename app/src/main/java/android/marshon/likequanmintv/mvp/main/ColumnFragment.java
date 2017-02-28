package android.marshon.likequanmintv.mvp.main;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.adapter.ColumnFragmentListAdapter;
import android.marshon.likequanmintv.base.BaseFragment;
import android.marshon.likequanmintv.bean.ItemColumn;
import android.marshon.likequanmintv.librarys.http.delagate.IGetDataDelegate;
import android.marshon.likequanmintv.listener.UpDownRvScrollListener;
import android.marshon.likequanmintv.mvp.column.interactor.ColumnFragmentInteractorImpl;
import android.marshon.likequanmintv.view.lazyvp.LazyFragmentPagerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.adapter.recyclerview.wrapper.LoadMoreCommonAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by Administrator on 2016/11/21.
 */

public class ColumnFragment extends BaseFragment implements LoadMoreCommonAdapter.OnLoadMoreListener, IGetDataDelegate<List<ItemColumn>>,LazyFragmentPagerAdapter.Laziable{


    private RecyclerView mRv;
    private List<ItemColumn> mColumnList=new ArrayList<>();
    private ColumnFragmentListAdapter mAdapter;
    private ColumnFragmentInteractorImpl mColumnFragmentInteractor;
    private UpDownRvScrollListener.UpdownScroll mUpdownScroll;
    private Subscription mSubscription;


    public static ColumnFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ColumnFragment fragment = new ColumnFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.frg_column;
    }

    @Override
    protected void initView(View rootView) {
        mRv=(RecyclerView)rootView.findViewById(R.id.mRv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 3);
        mRv.setLayoutManager(gridLayoutManager);
        mAdapter=new ColumnFragmentListAdapter(mActivity,R.layout.listitem_column, mColumnList);
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
        if (mAdapter.getDatas()==null||mAdapter.getDatas().isEmpty()){
            mColumnFragmentInteractor = new ColumnFragmentInteractorImpl();
            mSubscription = mColumnFragmentInteractor.loadColumnList(this);
        }
    }


    @Override
    public void onLoadMoreRequested() {


    }

    @Override
    public void getDataSuccess(List<ItemColumn> itemColumns) {
        mAdapter.refreshDatas(itemColumns);

    }

    @Override
    public void getDataError(String errmsg) {

    }

    @Override
    public void onDestroy() {
        if (mSubscription!=null&&mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
        super.onDestroy();

    }
}
