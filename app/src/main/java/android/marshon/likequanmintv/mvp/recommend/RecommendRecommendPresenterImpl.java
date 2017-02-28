package android.marshon.likequanmintv.mvp.recommend;

import android.marshon.likequanmintv.bean.Banner;
import android.marshon.likequanmintv.bean.LiveCategory;
import android.marshon.likequanmintv.librarys.http.delagate.IGetDataDelegate;
import android.marshon.likequanmintv.librarys.http.rxjava.MSubscriber;
import android.marshon.likequanmintv.librarys.mvpbase.BasePresenterImpl;
import android.marshon.likequanmintv.mvp.recommend.interactor.RecommendFragmentInteractorImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

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
        Subscription subscription = mInteractor.getRecommendCategories(new IGetDataDelegate<List<LiveCategory>>() {

            @Override
            public void getDataSuccess(List<LiveCategory> liveCategories) {
                mPresenterView.onGetRecommendCategories(liveCategories);
            }

            @Override
            public void getDataError(String errmsg) {

            }
        });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getAppStartInfo() {
        Subscription subscription = mInteractor.getStartInfo(new MSubscriber<JSONObject>() {
            @Override
            public void onNext(JSONObject obj) {
                super.onNext(obj);
                Gson mGson = new Gson();

                JSONArray appfocusArray = obj.optJSONArray("app-focus");

                Type type = new TypeToken<List<Banner>>() {
                }.getType();
                if (appfocusArray != null) {
                    List<Banner> banners = mGson.fromJson(appfocusArray.toString(), type);
                    mPresenterView.onGetBanners(banners);
                }


            }
        });
        mSubscriptions.add(subscription);
    }
}
