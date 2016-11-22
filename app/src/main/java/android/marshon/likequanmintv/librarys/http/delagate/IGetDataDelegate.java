package android.marshon.likequanmintv.librarys.http.delagate;

/**
 * Created by Marshon.Chen on 2016/6/4.
 * DESC:
 */
public interface IGetDataDelegate<T> {
    void getDataSuccess(T t);
    void getDataError(String errmsg);
}
