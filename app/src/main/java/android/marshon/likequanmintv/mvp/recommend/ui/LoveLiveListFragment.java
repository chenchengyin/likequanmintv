package android.marshon.likequanmintv.mvp.recommend.ui;

import android.marshon.likequanmintv.bean.PlayBean;

import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by ITMarshon.Chen on 2016/11/25.
 * emal:itmarshon@163.com
 * desc: 颜值控
 */

public class LoveLiveListFragment extends BaseLiveListFragment<PlayBean> {


    @Override
    public int getListItemLayout() {
        return 0;
    }

    @Override
    protected void convertItem(ViewHolder holder, PlayBean playBean, int position) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void getDataError(String errmsg) {
        showToast("获取颜值控数据失败");

    }
}
