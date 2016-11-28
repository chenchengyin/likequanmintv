package android.marshon.likequanmintv.listener;

import android.marshon.likequanmintv.controller.LivePlayerHolder;
import android.marshon.likequanmintv.mvp.live.ui.CommonLiveUI;
import android.marshon.likequanmintv.view.mediacontroll.VerticalMediaControllView;

/**
 * Created by ITMarshon.Chen on 2016/11/28.
 * emal:itmarshon@163.com
 * desc:
 */

public class MyOnVerticalControllListener implements VerticalMediaControllView.OnVerticalControllListener {


    private CommonLiveUI activity;
    private LivePlayerHolder playerHolder;

    public MyOnVerticalControllListener(CommonLiveUI activity, LivePlayerHolder playerHolder) {
        this.activity = activity;
        this.playerHolder = playerHolder;
    }



    @Override
    public void onVerticalClickPause() {
        if (playerHolder!=null)
            playerHolder.pausePlayer();

    }

    @Override
    public void onVerticalClickStart() {
        if (playerHolder!=null)
            playerHolder.startPlayer();
    }

    @Override
    public void onVerticalClickBack() {
        activity.onBackPressed();
    }




}
