package android.marshon.likequanmintv.controller;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.Banner;
import android.marshon.likequanmintv.librarys.base.BaseActivity;
import android.marshon.likequanmintv.librarys.utils.LogUtil;
import android.view.View;

import org.json.JSONArray;

import java.util.List;

import arg.marshon.publiclibrary.autoviewpager.AutoScrollViewPager;

/**
 * Created by ITMarshon.Chen on 2016/11/24.
 * emal:itmarshon@163.com
 * desc:
 */

public class BannerHeadViewController {

    private boolean stopAutoScroll;
    private AutoScrollViewPager pager;
    public View headView;
    private Object banner;

    public BannerHeadViewController(BaseActivity mContext){
        headView = View.inflate(mContext, R.layout.widget_bannerview, null);
        pager=(AutoScrollViewPager)headView.findViewById(R.id.autoViewpager);
        if (stopAutoScroll){
            pager.stopAutoScroll();
        }else {
            pager.setScrollFactgor(6);
            pager.setOffscreenPageLimit(4);
            pager.startAutoScroll(3000);
        }
        pager.setOnPageClickListener(new AutoScrollViewPager.OnPageClickListener() {
            @Override
            public void onPageClick(AutoScrollViewPager autoScrollPager, int position) {
//                if (mClickPagerListener!=null){
//                    mClickPagerListener.clickPage(position);
//                }  //// TODO: 2016/11/25 0025  
            }
        });
    }


    public void setBannerData(List<Banner> bannerList){
        LogUtil.i(""+bannerList.toString());




    }

    public Object getBanner() {
        if (banner==null){
            //获取数据


        }
        return banner;
    }
}
