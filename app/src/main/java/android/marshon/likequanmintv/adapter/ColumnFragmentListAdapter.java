package android.marshon.likequanmintv.adapter;

import android.content.Intent;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.ItemColumn;
import android.marshon.likequanmintv.librarys.base.BaseActivity;
import android.marshon.likequanmintv.mvp.column.ColumnListActivity;
import android.view.View;

import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.glide.glide.GlideRoundTransform;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreCommonAdapter;

import java.util.List;

/**
 * Created by It.Marshon on 2016/11/22 0022 11:01
 */

public class ColumnFragmentListAdapter extends LoadMoreCommonAdapter<ItemColumn> {

    private final BaseActivity mActivity;

    public ColumnFragmentListAdapter(BaseActivity context, int layoutId, List<ItemColumn> datas) {
        super(context, layoutId, datas);
        mActivity = context;
    }

    @Override
    protected void convert(ViewHolder holder, final ItemColumn itemColumn, int position) {
//
        holder.setText(R.id.name,itemColumn.name);
        holder.setImageUrl(R.id.thumnails,itemColumn.image,new GlideRoundTransform(mContext,1.5f));

        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, ColumnListActivity.class);
                intent.putExtra("itemColumn",itemColumn);
                mActivity.startActivity(intent);
            }
        });
    }
}
