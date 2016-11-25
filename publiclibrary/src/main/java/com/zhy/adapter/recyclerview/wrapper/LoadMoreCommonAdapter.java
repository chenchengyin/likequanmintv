package com.zhy.adapter.recyclerview.wrapper;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhy on 16/6/23.
 */
public abstract class LoadMoreCommonAdapter<T> extends MultiItemTypeAdapter<T>
{
    public static final int ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2;

    private View mLoadMoreView;
    private int mLoadMoreLayoutId;

    public LoadMoreCommonAdapter(final Context context, int listItemLayout) {
        this(context, listItemLayout,new ArrayList<T>());
    }


    public void refreshDatas(List<T> mList){
        mDatas=mList;
        notifyDataSetChanged();
    }

    public void addMoreDatas(List<T> mList){
        mDatas.addAll(mList);
//        notifyDataSetChanged();
        notifyItemRangeChanged(getItemCount()-1,mList.size());
    }

    protected Context mContext;
    protected int mLayoutId;
    protected LayoutInflater mInflater;



    public LoadMoreCommonAdapter(final Context context, final int layoutId, List<T> datas)
    {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<T>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType( T item, int position)
            {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position)
            {
                LoadMoreCommonAdapter.this.convert(holder, t, position);
            }
        });
    }
    protected abstract void convert(ViewHolder holder, T t, int position);

    private boolean hasLoadMore()
    {
        return mLoadMoreView != null || mLoadMoreLayoutId != 0;
    }


    private boolean isShowLoadMore(int position)
    {
        return hasLoadMore() && (position >= getItemCount());
    }

    @Override
    public int getItemViewType(int position)
    {
        if (isShowLoadMore(position))
        {
            return ITEM_TYPE_LOAD_MORE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
//        Log.d("LoadMoreCommonAdapter","onCreateViewHolder");
        if (viewType == ITEM_TYPE_LOAD_MORE)
        {
            ViewHolder holder;
            if (mLoadMoreView != null)
            {
                holder = ViewHolder.createViewHolder(parent.getContext(), mLoadMoreView);
            } else
            {
                holder = ViewHolder.createViewHolder(parent.getContext(), parent, mLoadMoreLayoutId);
            }
            return holder;
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
//        Log.d("LoadMoreCommonAdapter","onBindViewHolder");
        if (isShowLoadMore(position))
        {
            if (mOnLoadMoreListener != null)
            {
                mOnLoadMoreListener.onLoadMoreRequested();
            }
            return;
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
//        Log.d("LoadMoreCommonAdapter","onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
            {
                @Override
                public int getSpanSize(int position)
                {
                    if (isShowLoadMore(position))
                    {
                        return gridLayoutManager.getSpanCount();
                    }
                    if (spanSizeLookup != null)
                    {
                        return spanSizeLookup.getSpanSize(position);
                    }
                    return 1;
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }


    @Override
    public void onViewAttachedToWindow(ViewHolder holder)
    {
//        Log.d("LoadMoreCommonAdapter","onViewAttachedToWindow");

        if (isShowLoadMore(holder.getLayoutPosition()))
        {
            setFullSpan(holder);
        }
    }

    private void setFullSpan(RecyclerView.ViewHolder holder)
    {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams)
        {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;

            p.setFullSpan(true);
        }
    }

    @Override
    public int getItemCount()
    {
        return super.getItemCount() + (hasLoadMore() ? 1 : 0);
    }


    public interface OnLoadMoreListener
    {
        void onLoadMoreRequested();
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public LoadMoreCommonAdapter setOnLoadMoreListener(OnLoadMoreListener loadMoreListener)
    {
        if (loadMoreListener != null)
        {
            mOnLoadMoreListener = loadMoreListener;
        }
        return this;
    }

    public LoadMoreCommonAdapter setLoadMoreView(View loadMoreView)
    {
        mLoadMoreView = loadMoreView;
        return this;
    }

    public LoadMoreCommonAdapter setLoadMoreView(int layoutId)
    {
        mLoadMoreLayoutId = layoutId;
        return this;
    }



}
