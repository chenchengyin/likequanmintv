package android.marshon.likequanmintv.mvp.recommend;

import android.marshon.likequanmintv.librarys.mvpbase.BasePresenter;

/**
 * Created by ITMarshon.Chen on 2016/11/24.
 * emal:itmarshon@163.com
 * desc:
 */

public interface RecommendRecommendPresenter extends BasePresenter<RecommendRecommendView> {
    void getRecommendCategories();

    void getAppStartInfo();

}
