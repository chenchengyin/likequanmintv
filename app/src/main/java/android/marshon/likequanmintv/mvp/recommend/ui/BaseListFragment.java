package android.marshon.likequanmintv.mvp.recommend.ui;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.base.BaseFragment;
import android.marshon.likequanmintv.librarys.http.delagate.IGetDataDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreCommonAdapter;

import java.util.List;

/**
 * Created by ITMarshon.Chen on 2016/11/25.
 * emal:itmarshon@163.com
 * desc:
 */

public abstract class BaseListFragment<T> extends BaseFragment implements IGetDataDelegate<List<T>>{


    private RecyclerView mRv;
    protected LoadMoreCommonAdapter<T> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_listview;
    }

    @Override
    protected void initView(View rootView) {
        mRv=find(R.id.mRv);
        mRv.setLayoutManager(new GridLayoutManager(mActivity,2));
        adapter= new LoadMoreCommonAdapter<T>(mActivity, this.getListItemLayout()) {
            @Override
            protected void convert(ViewHolder holder, T t, int position) {
                convertItem(holder,t,position);
            }
        };
        mRv.setAdapter(adapter);
    }


    @Override
    public void getDataSuccess(List<T> datas) {
        adapter.refreshDatas(datas);
        adapter.notifyDataSetChanged();
    }

    public abstract int getListItemLayout();

    protected abstract void convertItem(ViewHolder holder, T t, int position);
}
