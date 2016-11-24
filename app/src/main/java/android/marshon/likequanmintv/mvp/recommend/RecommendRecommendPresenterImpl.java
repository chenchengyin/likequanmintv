package android.marshon.likequanmintv.mvp.recommend;

import android.marshon.likequanmintv.bean.LiveCategory;
import android.marshon.likequanmintv.librarys.http.delagate.IGetDataDelegate;
import android.marshon.likequanmintv.librarys.mvpbase.BasePresenterImpl;
import android.marshon.likequanmintv.mvp.recommend.interactor.RecommendFragmentInteractorImpl;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ITMarshon.Chen on 2016/11/24.
 * emal:itmarshon@163.com
 * desc:
 */

public class RecommendRecommendPresenterImpl extends BasePresenterImpl<RecommendRecommendView> implements RecommendRecommendPresenter{

    @Inject
    public RecommendRecommendPresenterImpl(){}

    @Inject
    public RecommendFragmentInteractorImpl mInteractor;

    @Override
    public void getRecommendCategories() {
        mInteractor.getRecommendCategories(new IGetDataDelegate<List<LiveCategory>>(){

            @Override
            public void getDataSuccess(List<LiveCategory> liveCategories) {
                mPresenterView.onGetRecommendCategories(liveCategories);
            }

            @Override
            public void getDataError(String errmsg) {

            }
        });
    }
}
