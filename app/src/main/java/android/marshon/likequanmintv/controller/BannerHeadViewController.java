package android.marshon.likequanmintv.controller;

import android.content.Intent;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.adapter.BannerPagerAadapter;
import android.marshon.likequanmintv.bean.Banner;
import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.librarys.base.BaseActivity;
import android.marshon.likequanmintv.librarys.utils.LogUtil;
import android.marshon.likequanmintv.mvp.live.ui.CommonLiveUI;
import android.marshon.likequanmintv.mvp.live.ui.VerFullLiveUI;
import android.marshon.likequanmintv.mvp.recommend.ui.RecommendRecommendFragment;
import android.marshon.likequanmintv.utils.WebContainerActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import arg.marshon.publiclibrary.autoviewpager.AutoScrollViewPager;

/**
 * Created by ITMarshon.Chen on 2016/11/24.
 * emal:itmarshon@163.com
 * desc:
 */

public class BannerHeadViewController {

    private TextView title;
    private RecommendRecommendFragment mContext;
    private BannerPagerAadapter bannerAdapter;
//    private AutoScrollPagerAdapter mAdapter;
    private boolean stopAutoScroll;
    private AutoScrollViewPager pager;
    public View headView;
    private Object banner;
    private List<Banner> mBannerList;

    public BannerHeadViewController(final RecommendRecommendFragment mContext){
        headView = View.inflate(mContext.getActivity(), R.layout.widget_bannerview, null);
        title= (TextView) headView.findViewById(R.id.title);
        this.mContext=mContext;
        pager=(AutoScrollViewPager)headView.findViewById(R.id.autoViewpager);
        if (stopAutoScroll){
            pager.stopAutoScroll();
        }else {
            pager.setScrollFactgor(6);
            pager.setOffscreenPageLimit(5);
            pager.startAutoScroll(3000);
        }

        pager.setOnPageClickListener(new AutoScrollViewPager.OnPageClickListener() {
            @Override
            public void onPageClick(AutoScrollViewPager autoScrollPager, int position) {
                Banner item = bannerAdapter.getItem(position);

                if (item.ext.type.equals("play")){
                    PlayBean playBean = item.link_object;
                    LogUtil.e(playBean.toString());
                    if (playBean.category_slug!=null&&playBean.category_slug.equals("love")){
                        Intent intent =new Intent(mContext.getActivity(), VerFullLiveUI.class);
                        intent.putExtra("playBean",playBean);
                        BaseActivity activity= (BaseActivity) mContext.getActivity();
                        activity.startActivity(intent);
                    }else {
                        Intent intent =new Intent(mContext.getActivity(), CommonLiveUI.class);
                        intent.putExtra("playBean",playBean);
                        BaseActivity activity= (BaseActivity) mContext.getActivity();
                        activity.startActivity(intent);
                    }


                }else if (item.ext.type.equals("ad")){
                    Intent intent=new Intent(mContext.getActivity(), WebContainerActivity.class);
                    intent.putExtra(Intent.EXTRA_TITLE,""+item.title);
                    intent.putExtra(Intent.EXTRA_TEXT,""+item.link);
                    BaseActivity activity= (BaseActivity) mContext.getActivity();
                    activity.startActivity(intent);

                }
            }
        });

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (mBannerList!=null){
                    Banner banner = mBannerList.get(position);
                    title.setText(""+banner.title);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


//        mAdapter=new AutoScrollPagerAdapter(bannerAdapter);

    }

    public void setBannerData(List<Banner> bannerList){
        LogUtil.i(""+bannerList.toString());
        mBannerList = bannerList;
        if (bannerAdapter==null){
            bannerAdapter=new BannerPagerAadapter(mContext.getActivity(),bannerList);
            pager.setAdapter(bannerAdapter);
            if(this.stopAutoScroll) {
                this.pager.stopAutoScroll();
            } else {
                this.pager.setScrollFactgor(6.0D);
                this.pager.setOffscreenPageLimit(4);
                this.pager.startAutoScroll(3000);
            }

        }
    }

    public  void onResume(){
        pager.startAutoScroll();

    }

    public void onPaused(){
        pager.stopAutoScroll();
    }


}
