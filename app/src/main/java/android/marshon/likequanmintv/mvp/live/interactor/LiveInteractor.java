package android.marshon.likequanmintv.mvp.live.interactor;

import android.marshon.likequanmintv.base.APP;
import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.librarys.http.RetrofitManager;
import android.marshon.likequanmintv.librarys.http.delagate.IGetDataDelegate;
import android.marshon.likequanmintv.librarys.http.rxjava.MSubscriber;
import android.marshon.likequanmintv.librarys.http.rxjava.TransformUtils;
import android.marshon.likequanmintv.librarys.mvpbase.BaseInteractor;
import android.marshon.likequanmintv.librarys.utils.LogUtil;
import android.marshon.likequanmintv.thread.ThreadManager;
import android.marshon.likequanmintv.utils.OrmKeys;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/11/22.
 */


public class LiveInteractor extends BaseInteractor {
    Subscription subscription=null;
    @Inject
    public LiveInteractor(){

    }


    public Subscription  loadPlayList(final IGetDataDelegate<List<PlayBean>> delegate, final String url){

        boolean needUpdate = OrmKeys.isNeedUpdate(OrmKeys.liveKey+url);
        if (needUpdate){
            subscription= loadPlayListFromNet(url,delegate);
        }else {
            subscription= Observable.create(new Observable.OnSubscribe<List<PlayBean>>() {
                @Override
                public void call(Subscriber<? super List<PlayBean>> subscriber) {
                    List<PlayBean> playBeanList = APP.getWriteableDaoSession().getPlayBeanDao().loadAll();
                    if (playBeanList!=null&&!playBeanList.isEmpty()){
                        subscriber.onNext(playBeanList);
                    }else {
                        subscription =loadPlayListFromNet(url,delegate);
                    }
                }
            })
                    .compose(TransformUtils.<List<PlayBean>>defaultSchedulers())
                    .subscribe(new Action1<List<PlayBean>>() {
                        @Override
                        public void call(List<PlayBean> itemColumns) {
                            delegate.getDataSuccess(itemColumns);
                        }
                    });
        }

        return subscription;

    }

    private Subscription loadPlayListFromNet(final String url, final IGetDataDelegate<List<PlayBean>> delegate) {
        return RetrofitManager.getInstance()
                .getLiveAPIService().getPlayJson(url).
                        compose(TransformUtils.<JSONObject>defaultSchedulers())
                .subscribe(new MSubscriber<JSONObject>() {

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
                            final List<PlayBean> playBeanList=mGson.fromJson(data.toString(),token);
//                            List<PlayBean> playBeanList = convertList(data.toString());
                            delegate.getDataSuccess(playBeanList);
                            ThreadManager.getLongPool().execute(new Runnable() {
                                @Override
                                public void run() {
                                    APP.getWriteableDaoSession().getPlayBeanDao().insertOrReplaceInTx(playBeanList);
                                }
                            });

                            OrmKeys.updateTime(OrmKeys.liveKey+url);

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
