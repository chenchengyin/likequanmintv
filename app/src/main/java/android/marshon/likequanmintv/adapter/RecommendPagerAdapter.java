package android.marshon.likequanmintv.adapter;

import android.marshon.likequanmintv.base.BaseFragment;
import android.marshon.likequanmintv.bean.LiveCategory;
import android.marshon.likequanmintv.mvp.recommend.ui.BaseLiveWraperFragment;
import android.marshon.likequanmintv.mvp.recommend.ui.LoveLiveListFragment;
import android.marshon.likequanmintv.mvp.recommend.ui.RecommendRecommendFragment;
import android.marshon.likequanmintv.view.lazyvp.LazyFragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ITMarshon.Chen on 2016/11/24.
 * emal:itmarshon@163.com
 * desc:
 */

public class RecommendPagerAdapter extends LazyFragmentPagerAdapter {


    private List<LiveCategory> mLiveCategoryList;

    public SparseArray<BaseFragment> fragmentSparseArray=new SparseArray<>();

    public RecommendPagerAdapter(FragmentManager fm, List<LiveCategory> liveCategoryList) {
        super(fm);
        mLiveCategoryList = liveCategoryList;

        for (int position=0;position<mLiveCategoryList.size();position++){
            if (position==0){
                fragmentSparseArray.put(position,RecommendRecommendFragment.newInstance());
                continue;
            }
            LiveCategory liveCategory = mLiveCategoryList.get(position);
            String mUrl="json/categories/"+liveCategory.getSlug()+"/list.json";
            Bundle bundle=new Bundle();
            bundle.putString("url",mUrl);
            bundle.putString("tag",liveCategory.getName());
            if (liveCategory.getSlug().equals("love")){
                fragmentSparseArray.put(position,LoveLiveListFragment.newInstance(bundle));
                continue;
            }
            fragmentSparseArray.put(position,BaseLiveWraperFragment.newInstance(bundle));
        }
    }

//    @Override
//    public Fragment getItem(int position) {

//    }

    @Override
    public int getCount() {
        return mLiveCategoryList==null?0:mLiveCategoryList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        LiveCategory liveCategory = mLiveCategoryList.get(position);
        return liveCategory.getName();
    }

    @Override
    protected Fragment getItem(ViewGroup container, int position) {
        return fragmentSparseArray.get(position);
    }

    public void stopNetWork() {
        int size = fragmentSparseArray.size();
        for (int i = 0; i <size ; i++) {
            fragmentSparseArray.get(i).stopNetWork();
        }
    }

}
