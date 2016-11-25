package android.marshon.likequanmintv.mvp.live;

import android.marshon.likequanmintv.librarys.mvpbase.BasePresenter;

/**
 * Created by Administrator on 2016/11/22.
 */

public interface LiveFragmentPresenter extends BasePresenter<LiveFragmentView> {

    void loadPlayList();
    void loadPlayListByUrl(String url);

    void addMorePlayList();
}
