package android.marshon.likequanmintv.mvp.recommend;

import android.marshon.likequanmintv.librarys.mvpbase.BasePresenter;

import java.util.List;

/**
 * Created by It.Marshon on 2016/11/24 0024 17:53
 */

public interface RecommendFragmentPresenter extends BasePresenter<RecommendFragmentView> {

    void getRecommendCategories();
}
