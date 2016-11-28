package android.marshon.likequanmintv.mvp.live;

import android.marshon.likequanmintv.librarys.mvpbase.BasePresenter;

/**
 * Created by Administrator on 2016/11/22.
 */

public interface LivePlayerPresenter extends BasePresenter<LivePlayerView> {

    void enterRoom(String uid);
}
