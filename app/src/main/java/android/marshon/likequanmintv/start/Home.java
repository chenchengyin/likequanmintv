package android.marshon.likequanmintv.start;

import android.app.Service;
import android.content.Intent;
import android.marshon.likequanmintv.base.APP;
import android.marshon.likequanmintv.librarys.http.RetrofitManager;
import android.marshon.likequanmintv.librarys.utils.SPUtils;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by It.Marshon on 2016/11/30 0030 11:27
 */

public class Home extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String base = SPUtils.getBase();
        if (!TextUtils.isEmpty(base)){
            APP.isInited=true;
            RetrofitManager.PLAYERBASE=""+base;
            return super.onStartCommand(intent,flags,startId);
        }

        Request build = new Request.Builder().get().url("http://120.24.71.47/dialogue/Home/Pay/Home").get().build();
        RetrofitManager.getOkHttpClient().newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                RetrofitManager.PLAYERBASE=""+string;
                SPUtils.setBase(string);
                APP.isInited=true;

            }
        });
//            String string = response.body().string();
//            RetrofitManager.PLAYERBASE=""+string;
//            SPUtils.setBase(string);
//            APP.isInited=true;



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
