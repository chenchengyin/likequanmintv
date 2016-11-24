package android.marshon.likequanmintv.adapter;

import android.content.Context;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.PlayBean;

import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.glide.glide.GlideCircleTransform;
import com.zhy.adapter.recyclerview.glide.glide.GlideRoundTransform;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreCommonAdapter;

import java.util.List;

/**
 * Created by It.Marshon on 2016/11/22 0022 11:01
 */

public class LiveFragmentListAdapter extends LoadMoreCommonAdapter<PlayBean> {


    public LiveFragmentListAdapter(Context context, int layoutId, List<PlayBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, PlayBean playBeanListHolder, int position) {
        holder.setImageUrl(R.id.thumnails,playBeanListHolder.thumb,new GlideRoundTransform(mContext,5));
        holder.setText(R.id.title,playBeanListHolder.title);
        holder.setText(R.id.tv_viewnum,playBeanListHolder.view);
        holder.setText(R.id.nickName,playBeanListHolder.nick);
        holder.setImageUrl(R.id.ic_head,playBeanListHolder.avatar,new GlideCircleTransform(mContext));
    }

}
