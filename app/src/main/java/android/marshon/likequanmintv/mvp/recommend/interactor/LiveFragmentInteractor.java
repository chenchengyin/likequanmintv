package android.marshon.likequanmintv.mvp.recommend.interactor;

import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.librarys.http.RetrofitManager;
import android.marshon.likequanmintv.librarys.http.apiservice.LiveAPIService;
import android.marshon.likequanmintv.librarys.http.delagate.IGetDataDelegate;
import android.marshon.likequanmintv.librarys.http.rxjava.MSubscriber;
import android.marshon.likequanmintv.librarys.http.rxjava.TransformUtils;
import android.marshon.likequanmintv.librarys.mvpbase.BaseInteractor;
import android.marshon.likequanmintv.librarys.utils.LogUtil;
import android.marshon.likequanmintv.librarys.utils.SPUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

/**
 * Created by Administrator on 2016/11/22.
 */


public class LiveFragmentInteractor extends BaseInteractor {

    @Inject
    public LiveFragmentInteractor(){

    }


    public Subscription  loadPlayList(final IGetDataDelegate<List<PlayBean>> delegate){

         return RetrofitManager.getInstance()
                .getLiveAPIService().getPlayJson(
                RetrofitManager.getInstance().getCacheControl(),
                SPUtils.getVersionCode(), SPUtils.getApiVersion()).
                compose(TransformUtils.<JSONObject>defaultSchedulers())
                .subscribe(new MSubscriber<JSONObject>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(JSONObject jsonObject) {
                        super.onNext(jsonObject);
                        try {
                            LogUtil.e("jsonObject"+jsonObject.toString());
                            JSONArray data = jsonObject.optJSONArray("data");
                            List<PlayBean> playBeanList = convertList(data.toString());
                            delegate.getDataSuccess(playBeanList);
                        } catch (Exception e) {
                            LogUtil.e("数据有误" + jsonObject);
                        }
                    }
                });
    }

    public Subscription  loadPlayList2(final IGetDataDelegate<List<PlayBean>> delegate){

        LiveAPIService liveAPIServicee = RetrofitManager.getInstance()
                .getLiveAPIService();
        Observable<JSONObject> playJson = null;
                  playJson= liveAPIServicee.getPlayJson2(
                RetrofitManager.getInstance().getCacheControl(),
                SPUtils.getVersionCode(), SPUtils.getApiVersion());
        Subscription subscription = playJson
                .compose(TransformUtils.<JSONObject>defaultSchedulers())
                .subscribe(new MSubscriber<JSONObject>() {

                    @Override
                    public void onNext(JSONObject jsonObject) {
                        super.onNext(jsonObject);

                        try {
                            LogUtil.e("jsonObject"+jsonObject.toString());
//                            JSONArray data = jsonObject.optJSONArray("data");
//                            Type typeToken = new TypeToken<List<PlayBean>>() {
//                            }.getType();
//                            List<PlayBean> playBeanList = mGson.fromJson(data.toString(), typeToken);
//                            delegate.getDataSuccess(playBeanList);
                        } catch (Exception e) {
                            LogUtil.e("数据有误" + jsonObject);
                        }
                    }
                });
        return subscription;
    }

}
