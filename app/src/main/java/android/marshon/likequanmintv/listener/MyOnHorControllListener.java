package android.marshon.likequanmintv.listener;

import android.marshon.likequanmintv.controller.LivePlayerHolder;
import android.marshon.likequanmintv.mvp.live.ui.CommonLiveUI;
import android.marshon.likequanmintv.view.mediacontroll.HorMediaControllView;

/**
 * Created by ITMarshon.Chen on 2016/11/28.
 * emal:itmarshon@163.com
 * desc:
 */

public class MyOnHorControllListener implements HorMediaControllView.OnHorControllListener {

    private CommonLiveUI activity;
    private LivePlayerHolder playerHolder;

    public MyOnHorControllListener(CommonLiveUI activity, LivePlayerHolder playerHolder) {
        this.activity = activity;
        this.playerHolder = playerHolder;

    }

    @Override
    public void onHorClickPause() {
        activity.onPlayePause();
    }

    @Override
    public void onHorClickStart() {
        activity.onPlayerStart();
    }

    @Override
    public void onHorClickBack() {
        activity.onBackPressed();
    }

}
