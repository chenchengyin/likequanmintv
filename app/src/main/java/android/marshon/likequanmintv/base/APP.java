package android.marshon.likequanmintv.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.marshon.likequanmintv.db.DaoMaster;
import android.marshon.likequanmintv.db.DaoSession;
import android.marshon.likequanmintv.di.component.ApplicationComponent;
import android.marshon.likequanmintv.di.component.DaggerApplicationComponent;
import android.marshon.likequanmintv.di.module.ApplicationModule;
import android.marshon.likequanmintv.librarys.utils.LogUtil;
import android.os.Handler;


import com.squareup.leakcanary.LeakCanary;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static android.R.attr.path;

/**
 * Created by Marshon.Chen on 2016/8/10.
 * DESC:
 */
public class APP extends Application {

    private static OkHttpClient okHttpClient;
    private static Handler mHandler;
    private static Context mContext;

    public static final String DB_NAME= "history3-db";
    private ApplicationComponent mApplicationComponent;

    public static boolean isDebug() {
        return true;  //默认debug
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();

        mContext = getApplicationContext();

        LeakCanary.install(this);
        initApplicationComponent();
    }

    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }



    public static Context getContext() {
        return mContext;
    }
    public static Handler getMHanlder() {
        return mHandler;
    }
    public static OkHttpClient getOkhttpClient() {
        if (okHttpClient==null){
            //手动创建一个OkHttpClient并设置超时时间
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }
    private static final int DEFAULT_TIMEOUT = 10;



    public static DaoSession getWriteableDaoSession(){
        //greendao
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(APP.getContext(), DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        String path = daoMaster.getDatabase().toString();
        LogUtil.e("数据库:"+path);
        return daoSession;
    }
    public static DaoSession getReadAbleDaoSession(){
        //greendao
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(APP.getContext(), DB_NAME, null);
        SQLiteDatabase db = helper.getReadableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
//        String path = daoMaster.getDatabase().getPath();
//        LogUtil.e("数据库路径:"+path);
        return daoSession;
    }


}
