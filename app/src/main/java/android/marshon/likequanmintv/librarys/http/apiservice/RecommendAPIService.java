package android.marshon.likequanmintv.librarys.http.apiservice;


import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by marshon on 2016/10/12 0012 10:45
 */

public interface RecommendAPIService {



    @GET("json/page/app-data/info.json?v=2.2.4&os=1&ver=4")
    Observable<JSONObject> getAppStartInfo();

    @GET("json/app/index/category/info-android.json?v=2.2.4&os=1&ver=4")
    Observable<JSONArray> getAllCategories();

    @GET("json/app/index/recommend/list-android.json?11241742&v=2.2.4&os=1&ver=4")
    Observable<JSONObject> getRecommendCategories();

}
