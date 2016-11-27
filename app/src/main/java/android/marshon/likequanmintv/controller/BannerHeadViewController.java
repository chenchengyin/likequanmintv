package android.marshon.likequanmintv.controller;

import android.content.Intent;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.adapter.BannerPagerAadapter;
import android.marshon.likequanmintv.bean.Banner;
import android.marshon.likequanmintv.librarys.utils.LogUtil;
import android.marshon.likequanmintv.mvp.recommend.ui.RecommendRecommendFragment;
import android.marshon.likequanmintv.utils.WebContainerActivity;
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

                    //// TODO: 2016/11/25 开启直播

                }else if (item.ext.type.equals("ad")){
                    Intent intent=new Intent(mContext.getActivity(), WebContainerActivity.class);
                    intent.putExtra(Intent.EXTRA_TITLE,""+item.title);
                    intent.putExtra(Intent.EXTRA_TEXT,""+item.link);
                    mContext.getActivity().startActivity(intent);

                }
            }
        });

//        mAdapter=new AutoScrollPagerAdapter(bannerAdapter);

    }

    public void setBannerData(List<Banner> bannerList){
        LogUtil.i(""+bannerList.toString());
        if (bannerAdapter==null){
            bannerAdapter=new BannerPagerAadapter(mContext.getActivity(),bannerList,title);
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
