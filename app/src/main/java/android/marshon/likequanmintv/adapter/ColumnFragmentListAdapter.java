package android.marshon.likequanmintv.adapter;

import android.content.Context;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.ItemColumn;

import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.glide.glide.GlideRoundTransform;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreCommonAdapter;

import java.util.List;

/**
 * Created by It.Marshon on 2016/11/22 0022 11:01
 */

public class ColumnFragmentListAdapter extends LoadMoreCommonAdapter<ItemColumn> {

    public ColumnFragmentListAdapter(Context context, int layoutId, List<ItemColumn> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ItemColumn itemColumn, int position) {
//
        holder.setText(R.id.name,itemColumn.name);
        holder.setImageUrl(R.id.thumnails,itemColumn.image,new GlideRoundTransform(mContext,1.5f));
    }



}
