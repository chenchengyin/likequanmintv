package android.marshon.likequanmintv.mvp.recommend.ui;

import android.content.Intent;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.mvp.live.interactor.LiveInteractor;
import android.marshon.likequanmintv.mvp.live.ui.CommonLiveUI;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.glide.glide.GlideCircleTransform;
import com.zhy.adapter.recyclerview.glide.glide.GlideRoundTransform;

/**
 * Created by It.Marshon on 2016/11/25 0025 10:11
 * desc:直播列表
 */

public class BaseLiveWraperFragment extends BaseListFragment<PlayBean> {

    protected String mUrl;
    private LiveInteractor mLiveInteractor;
    private String mTag;

    public static BaseLiveWraperFragment newInstance(Bundle args) {
        BaseLiveWraperFragment fragment = new BaseLiveWraperFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void convertItem(ViewHolder holder, final PlayBean playBean, int position) {
        holder.setImageUrl(R.id.thumnails,playBean.thumb,new GlideRoundTransform(mActivity,5));
        holder.setText(R.id.title,playBean.title);
        holder.setText(R.id.tv_viewnum,playBean.view);
        holder.setText(R.id.nickName,playBean.nick);
        holder.setImageUrl(R.id.ic_head,playBean.avatar,new GlideCircleTransform(mActivity));
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mActivity, CommonLiveUI.class);
                intent.putExtra("playBean",playBean);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
            }
        });
    }

    @Override
    public int getListItemLayout() {
        return R.layout.listitem_live;
    }

    @Override
    public void initData() {
        Bundle arguments = getArguments();
        mUrl = arguments.getString("url", "");
        mTag = arguments.getString("tag", "");
        mLiveInteractor = new LiveInteractor();
        mSubscriptions.add(mLiveInteractor.loadPlayList(this, mUrl));
    }
    @Override
    public void getDataError(String errmsg) {
        showToast("获取"+mTag+"数据失败");
    }


    @Override
    public void onResume() {
        super.onResume();
        Glide.with(this).resumeRequests();
    }

    @Override
    public void onPause() {
        super.onPause();
        Glide.with(this).pauseRequests();

    }
}
