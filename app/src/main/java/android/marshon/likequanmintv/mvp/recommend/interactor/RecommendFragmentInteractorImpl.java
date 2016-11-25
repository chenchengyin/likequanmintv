package android.marshon.likequanmintv.mvp.recommend.interactor;

import android.marshon.likequanmintv.bean.LiveCategory;
import android.marshon.likequanmintv.librarys.http.RetrofitManager;
import android.marshon.likequanmintv.librarys.http.delagate.IGetDataDelegate;
import android.marshon.likequanmintv.librarys.http.rxjava.MSubscriber;
import android.marshon.likequanmintv.librarys.http.rxjava.TransformUtils;
import android.marshon.likequanmintv.librarys.mvpbase.BaseInteractor;
import android.marshon.likequanmintv.librarys.utils.LogUtil;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by It.Marshon on 2016/11/24 0024 17:57
 */

public class RecommendFragmentInteractorImpl extends BaseInteractor {

    @Inject
    public RecommendFragmentInteractorImpl(){

    }

    public Subscription getStartInfo(MSubscriber<JSONObject> mSubscriber) {
        return RetrofitManager.getInstance()
                .getRecommendAPIService().getAppStartInfo()
                .compose(TransformUtils.<JSONObject>defaultSchedulers())
                .subscribe(mSubscriber);
    }
    public Subscription getAllCategories(final IGetDataDelegate<List<LiveCategory>> delegate) {

        return RetrofitManager.getInstance()
                .getRecommendAPIService().getAllCategories()
                .compose(TransformUtils.<JSONArray>defaultSchedulers())
                .subscribe(new MSubscriber<JSONArray>() {

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(JSONArray roomJsonArray) {
                        super.onNext(roomJsonArray);
                        try {
                            LogUtil.e("json所有分类"+roomJsonArray.toString());
//                            JSONArray roomJsonArray = jsonObject.optJSONArray("room");
//                            JSONArray adJsonArray = jsonObject.optJSONArray("ad");
                            Type token =new TypeToken<List<LiveCategory>>(){}.getType();
                            List<LiveCategory> liveCategories=mGson.fromJson(roomJsonArray.toString(),token);
//                            List<PlayBean> playBeanList = convertList(data.toString());
                            delegate.getDataSuccess(liveCategories);
                        } catch (Exception e) {
                            LogUtil.e("数据有误" + getClass().getName());
                        }
                    }
                });
    }
    public Subscription getRecommendCategories(final IGetDataDelegate<List<LiveCategory>> delegate) {

        return RetrofitManager.getInstance()
                .getRecommendAPIService().getRecommendCategories()
                .compose(TransformUtils.<JSONObject>defaultSchedulers())
                .subscribe(new MSubscriber<JSONObject>() {

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(JSONObject jsonObject) {
                        super.onNext(jsonObject);
                        try {
                            LogUtil.e("json首页推荐"+jsonObject.toString());
                            JSONArray roomJsonArray = jsonObject.optJSONArray("room");
                            JSONArray adJsonArray = jsonObject.optJSONArray("ad");
                            Type token =new TypeToken<List<LiveCategory>>(){}.getType();
                            List<LiveCategory> liveCategories=mGson.fromJson(roomJsonArray.toString(),token);
//                            List<PlayBean> playBeanList = convertList(data.toString());
                            delegate.getDataSuccess(liveCategories);
                        } catch (Exception e) {
                            LogUtil.e("数据有误" + getClass().getName());
                        }
                    }
                });
    }
}
