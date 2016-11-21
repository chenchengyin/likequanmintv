package android.marshon.likequanmintv.librarys.mvpbase;

import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/9/27.
 */

public interface  BaseView {
    void showProgress(@Nullable String msg);
    void hideProgress();
    void showToast(String msg);
    void showAlert(String msg);
    void showLongToast(String msg);
}
