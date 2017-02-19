package android.marshon.likequanmintv.mvp.recommend.ui;

import android.content.Intent;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.mvp.live.interactor.LiveInteractor;
import android.marshon.likequanmintv.mvp.live.ui.VerFullLiveUI;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.glide.glide.GlideRoundTransform;



/**
 * Created by ITMarshon.Chen on 2016/11/25.
 * emal:itmarshon@163.com
 * desc: 颜值控
 */

public class LoveLiveListFragment extends BaseLiveWraperFragment {

    private LiveInteractor mLiveInteractor;
    private int mScreenWidth;

    public static LoveLiveListFragment newInstance(Bundle args) {
        LoveLiveListFragment fragment = new LoveLiveListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getListItemLayout() {
        return R.layout.listitem_love;
    }

    @Override
    protected void convertItem(ViewHolder holder, final PlayBean playBean, int position) {
        holder.setImageUrl(R.id.thumnails,playBean.thumb,new GlideRoundTransform(mActivity,5));
        holder.setText(R.id.tv_viewnum,playBean.view);
        holder.setText(R.id.intro,playBean.title);
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mActivity, VerFullLiveUI.class);
                intent.putExtra("playBean",playBean);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
            }
        });
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

    @Override
    public void getDataError(String errmsg) {
        showToast("获取颜值控数据失败");
    }

}
