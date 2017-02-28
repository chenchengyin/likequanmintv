/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package android.marshon.likequanmintv.librarys.http;

import android.marshon.likequanmintv.base.APP;
import android.marshon.likequanmintv.librarys.http.apiservice.ColumnAPIService;
import android.marshon.likequanmintv.librarys.http.apiservice.LiveAPIService;
import android.marshon.likequanmintv.librarys.http.apiservice.ProfileAPIService;
import android.marshon.likequanmintv.librarys.http.apiservice.RecommendAPIService;
import android.marshon.likequanmintv.librarys.http.converter.JsonConverterFactory;
import android.marshon.likequanmintv.librarys.http.rxjavacalladapter.RxJavaCallAdapterFactory;
import android.marshon.likequanmintv.librarys.utils.LogUtil;
import android.marshon.likequanmintv.librarys.utils.NetUtil;
import android.util.SparseArray;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * @author 咖枯
 * @version 1.0 2016/5/26
 */
public class RetrofitManager {
    public static  String PLAYERBASE = null;
    private final Retrofit mRetrofit;

    private String getHost(int hostType) {
        if (hostType==HostType.NETEASE_JOKE) {
            return PLAYERBASE;
        }
        return PLAYERBASE;
    }

    private RecommendAPIService mRecommendAPIService;
    private ColumnAPIService mColumnAPIService;
    private LiveAPIService mLiveAPIService;
    private ProfileAPIService mProfileAPIService;

    public RecommendAPIService getRecommendAPIService(){
        return mRetrofit.create(RecommendAPIService.class);
    }
    public ColumnAPIService getColumnAPIService(){
        return mRetrofit.create(ColumnAPIService.class);
    }
    public LiveAPIService getLiveAPIService(){
        return mRetrofit.create(LiveAPIService.class);
    }
    public ProfileAPIService getProfileAPIService(){
        return mRetrofit.create(ProfileAPIService.class);
    }

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;

    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";

    private static volatile OkHttpClient sOkHttpClient;

    private static SparseArray<RetrofitManager> sRetrofitManager = new SparseArray<>();

    public RetrofitManager(@HostType.HostTypeChecker int hostType) {
        mRetrofit = new Retrofit.Builder().baseUrl(getHost(hostType))
                .client(getOkHttpClient())
                .addConverterFactory(JsonConverterFactory.create())
               .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }


    public static OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                Cache cache = new Cache(new File(APP.getContext().getCacheDir(), "HttpCache"),
                        1024 * 1024 * 100);
                if (sOkHttpClient == null) {
                    sOkHttpClient = new OkHttpClient.Builder().cache(cache)
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(mLoggingInterceptor)
                            .build();
                }
            }
        }
        return sOkHttpClient;
    }

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private static final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            if (!NetUtil.isNetWorkConnectted()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                LogUtil.d("no network");
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtil.isNetWorkConnectted()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    private static final Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            LogUtil.i(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            LogUtil.i(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };

    public static RetrofitManager getInstance() {
        return getInstance(HostType.NETEASE_JOKE);
    }
    public static RetrofitManager getInstance(int hostType) {
        RetrofitManager retrofitManager = sRetrofitManager.get(hostType);
        if (retrofitManager == null) {
            retrofitManager = new RetrofitManager(hostType);
            sRetrofitManager.put(hostType, retrofitManager);
            return retrofitManager;
        }
        return retrofitManager;
    }



    /**
     * 根据网络状况获取缓存的策略
     */
    public String getCacheControl() {
        return NetUtil.isNetWorkConnectted() ? CACHE_CONTROL_AGE : CACHE_CONTROL_CACHE;
    }

}
