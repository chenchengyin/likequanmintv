package android.marshon.likequanmintv.mvp.live;

/**
 * Created by ITMarshon.Chen on 2016/11/27.
 * emal:itmarshon@163.com
 * desc:
 */

public interface LivePlayerView {

    public void onConnecting();
    public void onReConnecting();
    public void onConnectSucces();
    public void onConnectFailed();
    public void onPlayComleted();
    public void onPlayerStart();
    public void onPlayePause();

}
