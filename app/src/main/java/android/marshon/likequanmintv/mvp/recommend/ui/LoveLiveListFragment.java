package android.marshon.likequanmintv.mvp.recommend.ui;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.librarys.utils.screen.ScreenUtils;
import android.marshon.likequanmintv.mvp.live.interactor.LiveFragmentInteractor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.glide.glide.GlideCircleTransform;
import com.zhy.adapter.recyclerview.glide.glide.GlideRoundTransform;



/**
 * Created by ITMarshon.Chen on 2016/11/25.
 * emal:itmarshon@163.com
 * desc: 颜值控
 */

public class LoveLiveListFragment extends BaseLiveWraperFragment {

    private LiveFragmentInteractor mLiveFragmentInteractor;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mScreenWidth = ScreenUtils.getScreenWidth(mActivity);
    }

    @Override
    protected void convertItem(ViewHolder holder, PlayBean playBean, int position) {
//        ImageView imageView=holder.getView(R.id.thumnails);
//        imageView.setMaxWidth(mScreenWidth/2);
//        imageView.setAdjustViewBounds(true);

        holder.setImageUrl(R.id.thumnails,playBean.thumb,new GlideRoundTransform(mActivity,5));
        holder.setText(R.id.tv_viewnum,playBean.view);
//        holder.setText(R.id.nickName,playBean.nick);
        holder.setText(R.id.intro,playBean.title);
//        holder.setImageUrl(R.id.ic_head,playBean.avatar,new GlideCircleTransform(mActivity));
    }

    @Override
    public void getDataError(String errmsg) {
        showToast("获取颜值控数据失败");
    }
}
