package android.marshon.likequanmintv.adapter;

import android.marshon.likequanmintv.bean.LiveCategory;
import android.marshon.likequanmintv.mvp.main.TestFragment;
import android.marshon.likequanmintv.mvp.recommend.ui.RecommendRecommendFragment;
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
        return TestFragment.newInstance();
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
