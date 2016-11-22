package android.marshon.likequanmintv.librarys.http.apiservice;


import org.json.JSONObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by marshon on 2016/10/12 0012 10:45
 */

public interface ProfileAPIService {

    @GET("json/play/list.json?11211639&v=2.2.4&os=1&ver=4")
    Observable<JSONObject> getPlayJson(@Query("v") String v, @Query("os") String os, @Query("ver") String ver);

}
