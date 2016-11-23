package android.marshon.likequanmintv.adapter;

import android.content.Context;
import android.marshon.likequanmintv.bean.PlayBean;

import com.zhy.adapter.recyclerview.base.ViewHolder;
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


    }
}
