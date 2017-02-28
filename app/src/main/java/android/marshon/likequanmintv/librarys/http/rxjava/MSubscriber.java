package android.marshon.likequanmintv.librarys.http.rxjava;


import android.marshon.likequanmintv.base.APP;
import android.marshon.likequanmintv.librarys.utils.LogUtil;
import android.marshon.likequanmintv.librarys.utils.NetUtil;
import android.widget.Toast;

import com.google.gson.Gson;


import rx.Subscriber;

/**
 * Created by fulunyong on 2016/10/12 0012 14:52
 */

public class MSubscriber<T> extends Subscriber<T> {


    public static final String SERVERFAIL = "哎呀,服务器内部开小差了丫^_^";

    public Gson mGson=new Gson();


    protected boolean hasNetWork(){
        if (!NetUtil.isNetWorkConnectted()){
            Toast.makeText(APP.getContext(), "请连接网络或稍后重试...", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {

        LogUtil.e("MSubscriber onError:"+e);

    }

   @Override
    public void onNext(T obj) {
       LogUtil.i("netback:"+obj.toString());

    }
}
