package android.marshon.likequanmintv.librarys.http.apiservice;


import org.json.JSONObject;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by marshon on 2016/10/12 0012 10:45
 */

public interface LiveAPIService {


//    @GET("dialogue/Home/message/getUserChapters?user_id=3")
    @GET("json/play/list.json?11211639&os=1")
    Observable<JSONObject> getPlayJson(@Header("Cache-Control") String cacheControl
            , @Query("v") String v, @Query("ver") String ver);

    @GET("json/play/list.json?11211639&os=1")
    Observable<JSONObject> getPlayJson2(@Header("Cache-Control") String cacheControl
            , @Query("v") String v, @Query("ver") String ver);

}
