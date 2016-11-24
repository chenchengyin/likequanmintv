package android.marshon.likequanmintv.librarys.http.apiservice;


import org.json.JSONArray;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by marshon on 2016/10/12 0012 10:45
 */

public interface ColumnAPIService {

    @GET("json/categories/list.json?11211637&os=1")
    Observable<JSONArray> getColumnList(@Query("v") String v, @Query("ver") String ver);

}
