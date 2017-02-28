package android.marshon.likequanmintv.librarys.http.apiservice;


import org.json.JSONArray;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by marshon on 2016/10/12 0012 10:45
 */

public interface ColumnAPIService {

    @GET("{url}?11211637&os=1&v=2.2.4&os=1&ver=4")
    Observable<JSONArray> getColumnList(@Path("url") String url);

}
