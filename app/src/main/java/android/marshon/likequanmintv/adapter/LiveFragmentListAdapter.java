package android.marshon.likequanmintv.adapter;

import android.content.Context;
import android.marshon.likequanmintv.bean.PlayBeanListHolder;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by It.Marshon on 2016/11/22 0022 11:01
 */

public class LiveFragmentListAdapter extends CommonAdapter<PlayBeanListHolder> {

    private CommonAdapter<PlayBeanListHolder> adapter;

    public LiveFragmentListAdapter(Context context, int layoutId, List<PlayBeanListHolder> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, PlayBeanListHolder playBeanListHolder, int position) {

    }
}
