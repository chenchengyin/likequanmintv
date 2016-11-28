package android.marshon.likequanmintv.mvp.live.interactor;

import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.bean.livehouse.LiveHouse;
import android.marshon.likequanmintv.controller.RoomDataController;
import android.marshon.likequanmintv.librarys.http.RetrofitManager;
import android.marshon.likequanmintv.librarys.http.apiservice.LiveAPIService;
import android.marshon.likequanmintv.librarys.http.delagate.IGetDataDelegate;
import android.marshon.likequanmintv.librarys.http.rxjava.MSubscriber;
import android.marshon.likequanmintv.librarys.http.rxjava.TransformUtils;
import android.marshon.likequanmintv.librarys.mvpbase.BaseInteractor;
import android.marshon.likequanmintv.librarys.utils.LogUtil;
import android.marshon.likequanmintv.librarys.utils.SPUtils;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

/**
 * Created by Administrator on 2016/11/22.
 */


public class LiveInteractor extends BaseInteractor {

    @Inject
    public LiveInteractor(){

    }


    public Subscription  loadPlayList(final IGetDataDelegate<List<PlayBean>> delegate,String url){

         return RetrofitManager.getInstance()
                .getLiveAPIService().getPlayJson(url).
                compose(TransformUtils.<JSONObject>defaultSchedulers())
                .subscribe(new MSubscriber<JSONObject>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        delegate.getDataError("获取数据失败");
                    }

                    @Override
                    public void onNext(JSONObject jsonObject) {
                        super.onNext(jsonObject);
                        try {
                            LogUtil.e("jsonObject"+jsonObject.toString());
                            JSONArray data = jsonObject.optJSONArray("data");
                            Type token =new TypeToken<List<PlayBean>>(){}.getType();
                            List<PlayBean> playBeanList=mGson.fromJson(data.toString(),token);
//                            List<PlayBean> playBeanList = convertList(data.toString());
                            delegate.getDataSuccess(playBeanList);
                        } catch (Exception e) {
                            LogUtil.e("数据有误" + jsonObject);
                        }
                    }
                });
    }
    public Subscription  enterRoom(final IGetDataDelegate<JSONObject> delegate, String uid){

         return RetrofitManager.getInstance()
                .getLiveAPIService().enterRoom(uid).
                compose(TransformUtils.<JSONObject>defaultSchedulers())
                .subscribe(new MSubscriber<JSONObject>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        delegate.getDataError("获取数据失败");
                    }

                    @Override
                    public void onNext(JSONObject roomJson) {
                        super.onNext(roomJson);
                        delegate.getDataSuccess(roomJson);
                    }
                });
    }



}
