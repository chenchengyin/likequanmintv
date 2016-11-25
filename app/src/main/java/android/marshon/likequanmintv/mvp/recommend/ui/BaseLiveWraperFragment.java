package android.marshon.likequanmintv.mvp.recommend.ui;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.mvp.live.interactor.LiveFragmentInteractor;
import android.os.Bundle;

import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.glide.glide.GlideCircleTransform;
import com.zhy.adapter.recyclerview.glide.glide.GlideRoundTransform;

import static android.R.attr.tag;

/**
 * Created by It.Marshon on 2016/11/25 0025 10:11
 */

public class BaseLiveWraperFragment extends BaseListFragment<PlayBean> {

    protected String mUrl;
    private LiveFragmentInteractor mLiveFragmentInteractor;
    private String mTag;

    public static BaseLiveWraperFragment newInstance(Bundle args) {
        BaseLiveWraperFragment fragment = new BaseLiveWraperFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void convertItem(ViewHolder holder, PlayBean playBean, int position) {
        holder.setImageUrl(R.id.thumnails,playBean.thumb,new GlideRoundTransform(mActivity,5));
        holder.setText(R.id.title,playBean.title);
        holder.setText(R.id.tv_viewnum,playBean.view);
        holder.setText(R.id.nickName,playBean.nick);
        holder.setImageUrl(R.id.ic_head,playBean.avatar,new GlideCircleTransform(mActivity));
    }

    @Override
    public int getListItemLayout() {

        return R.layout.listitem_live;
    }

    @Override
    protected void initData() {

        Bundle arguments = getArguments();
        mUrl = arguments.getString("url", "");
        mTag = arguments.getString("tag", "");
        mLiveFragmentInteractor = new LiveFragmentInteractor();
        mLiveFragmentInteractor.loadPlayList(this,mUrl);
    }

    @Override
    public void getDataError(String errmsg) {
        showToast("获取"+mTag+"数据失败");
    }
}
