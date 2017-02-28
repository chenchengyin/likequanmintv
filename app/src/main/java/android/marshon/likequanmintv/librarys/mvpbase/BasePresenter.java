package android.marshon.likequanmintv.librarys.mvpbase;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2016/9/27.
 */

public interface BasePresenter<T extends BaseView> {
    void onCreate();

    void attachView(@NonNull T t);
//    void onResume();
    void onPause();
    void onDestroy();
}
