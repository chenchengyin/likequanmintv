package android.marshon.likequanmintv.mvp.recommend;

import android.marshon.likequanmintv.bean.LiveCategory;
import android.marshon.likequanmintv.librarys.http.delagate.IGetDataDelegate;
import android.marshon.likequanmintv.librarys.mvpbase.BasePresenterImpl;
import android.marshon.likequanmintv.mvp.recommend.interactor.RecommendFragmentInteractorImpl;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by It.Marshon on 2016/11/24 0024 17:55
 */

public class RecommendFragmentPresenterImpl extends BasePresenterImpl<RecommendFragmentView> implements RecommendFragmentPresenter{


    @Inject
    public RecommendFragmentInteractorImpl mInteractor;


    @Inject
    public RecommendFragmentPresenterImpl(){
    }



    @Override
    public void getAllCategories() {
        Subscription subscription = mInteractor.getAllCategories(new IGetDataDelegate<List<LiveCategory>>() {

            @Override
            public void getDataSuccess(List<LiveCategory> liveCategories) {
                mPresenterView.onGetAllCategories(liveCategories);
            }

            @Override
            public void getDataError(String errmsg) {

            }
        });
        mSubscriptions.add(subscription);
    }
}
