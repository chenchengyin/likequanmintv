package android.marshon.likequanmintv.mvp.live;

import android.marshon.likequanmintv.librarys.mvpbase.BaseView;

import org.json.JSONObject;

/**
 * Created by ITMarshon.Chen on 2016/11/27.
 * emal:itmarshon@163.com
 * desc:
 */

public interface LivePlayerView  extends BaseView{

    void onConnecting();
    void onReConnecting();
    void onConnectSucces();
    void onConnectFailed();
    void onPlayComleted();
    void onPlayerStart();
    void onPlayePause();

    //view
    void onRoomData(JSONObject roomJson);

}
