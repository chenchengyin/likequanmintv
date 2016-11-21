package android.marshon.likequanmintv.librarys.http.rxjava;


import android.marshon.likequanmintv.librarys.utils.LogUtil;

import com.google.gson.Gson;


import rx.Subscriber;

/**
 * Created by fulunyong on 2016/10/12 0012 14:52
 */

public class MSubscriber<T> extends Subscriber<T> {


    public static final String SERVERFAIL = "哎呀,服务器内部开小差了丫^_^";

    public Gson mGson=new Gson();
    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.e("网络出错了:"+e);

    }

   @Override
    public void onNext(T obj) {

    }
}
