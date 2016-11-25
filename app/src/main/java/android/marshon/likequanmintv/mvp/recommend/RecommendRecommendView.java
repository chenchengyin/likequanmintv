package android.marshon.likequanmintv.mvp.recommend;

import android.marshon.likequanmintv.bean.Banner;
import android.marshon.likequanmintv.bean.LiveCategory;
import android.marshon.likequanmintv.librarys.mvpbase.BaseView;

import java.util.List;

/**
 * Created by ITMarshon.Chen on 2016/11/24.
 * emal:itmarshon@163.com
 * desc:
 */

public interface RecommendRecommendView extends BaseView {


    void onGetRecommendCategories(List<LiveCategory> liveCategoryList);

    void onGetBanners(List<Banner> bannerList);
}
