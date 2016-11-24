package android.marshon.likequanmintv.mvp.recommend;

import android.marshon.likequanmintv.bean.LiveCategory;
import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.librarys.mvpbase.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public interface RecommendFragmentView extends BaseView {

    void onGetCategories(List<LiveCategory> liveCategoryList);

}
