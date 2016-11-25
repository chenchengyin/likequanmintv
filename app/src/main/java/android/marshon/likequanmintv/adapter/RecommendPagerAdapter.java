package android.marshon.likequanmintv.adapter;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.LiveCategory;
import android.marshon.likequanmintv.mvp.main.TestFragment;
import android.marshon.likequanmintv.mvp.recommend.ui.BaseLiveWraperFragment;
import android.marshon.likequanmintv.mvp.recommend.ui.LoveLiveListFragment;
import android.marshon.likequanmintv.mvp.recommend.ui.RecommendRecommendFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by ITMarshon.Chen on 2016/11/24.
 * emal:itmarshon@163.com
 * desc:
 */

public class RecommendPagerAdapter extends FragmentStatePagerAdapter {


    private final List<LiveCategory> mLiveCategoryList;

    public RecommendPagerAdapter(FragmentManager fm, List<LiveCategory> liveCategoryList) {
        super(fm);
        mLiveCategoryList = liveCategoryList;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return RecommendRecommendFragment.newInstance();
        }
//        if (getPageTitle(position).equals("颜值控")){
//            return LoveLiveListFragment.newInstance();
//        }

        LiveCategory liveCategory = mLiveCategoryList.get(position);
        String mUrl="json/categories/"+liveCategory.getSlug()+"/list.json";
        Bundle bundle=new Bundle();
        bundle.putString("url",mUrl);
        bundle.putString("tag",liveCategory.getName());
        if (liveCategory.getName().equals("颜值控")){
            return LoveLiveListFragment.newInstance(bundle);
        }
        return BaseLiveWraperFragment.newInstance(bundle);
    }

    @Override
    public int getCount() {
        return mLiveCategoryList==null?0:mLiveCategoryList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        LiveCategory liveCategory = mLiveCategoryList.get(position);
        return liveCategory.getName();
    }

}
