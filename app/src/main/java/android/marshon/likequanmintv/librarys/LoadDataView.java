package android.marshon.likequanmintv.librarys;

import android.marshon.likequanmintv.librarys.mvpbase.BaseView;

/**
 * Created by Marshon.Chen on 2016/9/28.
 */

public interface LoadDataView<T>  extends BaseView {
    void onPreLoad();
    void onLoading();
    void onLoadData(T t);
    void onLoadDataFailed(String errmsg);
    void onLoadDataEmpty();

}
