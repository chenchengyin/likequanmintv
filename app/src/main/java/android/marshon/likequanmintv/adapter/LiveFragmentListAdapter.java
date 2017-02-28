package android.marshon.likequanmintv.adapter;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.librarys.base.BaseActivity;
import android.marshon.likequanmintv.mvp.live.ui.CommonLiveUI;
import android.marshon.likequanmintv.mvp.live.ui.VerFullLiveUI;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.glide.glide.GlideCircleTransform;
import com.zhy.adapter.recyclerview.glide.glide.GlideRoundTransform;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreCommonAdapter;

import java.util.List;

/**
 * Created by It.Marshon on 2016/11/22 0022 11:01
 */

public class LiveFragmentListAdapter extends LoadMoreCommonAdapter<PlayBean> {


    private BaseActivity mActivity;
    private int mLastPosition=-1;

    public LiveFragmentListAdapter(BaseActivity context, int layoutId, List<PlayBean> datas) {
        super(context, layoutId, datas);
        this.mActivity=context;
    }

    private void initAnim(View itemView) {
        ObjectAnimator alphaAnim=ObjectAnimator.ofFloat(itemView,"alpha",0f,1f);
        alphaAnim.setInterpolator(new LinearInterpolator());
        alphaAnim.setDuration(450);
        alphaAnim.start();

        ObjectAnimator scaleXAnim=ObjectAnimator.ofFloat(itemView,"scaleX",0.1f, 1f);
        scaleXAnim.setInterpolator(new LinearInterpolator());
        scaleXAnim.setDuration(450);
        scaleXAnim.start();

        ObjectAnimator scaleYAnim=ObjectAnimator.ofFloat(itemView,"scaleY",0.1f,1.0f);
        scaleYAnim.setInterpolator(new LinearInterpolator());
        scaleYAnim.setDuration(450);
        scaleYAnim.start();

        ObjectAnimator translationYAnim=ObjectAnimator.ofFloat(itemView,"translationY",itemView.getMeasuredHeight(), 0);
        translationYAnim.setInterpolator(new LinearInterpolator());
        translationYAnim.setDuration(450);
        translationYAnim.start();

    }
    boolean isFirstOnly=true;

    private void reset(View v){
        ViewCompat.setAlpha(v, 1);
        ViewCompat.setScaleY(v, 1);
        ViewCompat.setScaleX(v, 1);
        ViewCompat.setTranslationY(v, 0);
        ViewCompat.setTranslationX(v, 0);
        ViewCompat.setRotation(v, 0);
        ViewCompat.setRotationY(v, 0);
        ViewCompat.setRotationX(v, 0);
        ViewCompat.setPivotY(v, v.getMeasuredHeight() / 2);
        ViewCompat.setPivotX(v, v.getMeasuredWidth() / 2);
        ViewCompat.animate(v).setInterpolator(null).setStartDelay(0);
    }

    @Override
    protected void convert(ViewHolder holder, final PlayBean playBean, int position) {

        holder.setImageUrl(R.id.thumnails,playBean.thumb,new GlideRoundTransform(mContext,5));
        holder.setText(R.id.title,playBean.title);
        holder.setText(R.id.tv_viewnum,playBean.view);
        holder.setText(R.id.nickName,playBean.nick);
        holder.setImageUrl(R.id.ic_head,playBean.avatar,new GlideCircleTransform(mContext));
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (playBean.category_slug.equals("love")){
                    Intent intent =new Intent(mActivity, VerFullLiveUI.class);
                    intent.putExtra("playBean",playBean);
                    mActivity.startActivity(intent);
                }else {
                    Intent intent =new Intent(mActivity, CommonLiveUI.class);
                    intent.putExtra("playBean",playBean);
                    mActivity.startActivity(intent);
                }

            }
        });
        int adapterPosition = holder.getAdapterPosition();
        if (!isFirstOnly || adapterPosition > mLastPosition) {
            initAnim(holder.itemView);
            mLastPosition = adapterPosition;
        } else {
            reset(holder.itemView);
        }
    }

}
