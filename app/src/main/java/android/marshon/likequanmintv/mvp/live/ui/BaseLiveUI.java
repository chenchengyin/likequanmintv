package android.marshon.likequanmintv.mvp.live.ui;

import android.content.pm.ActivityInfo;
import android.marshon.likequanmintv.librarys.base.BaseActivity;
import android.marshon.likequanmintv.librarys.utils.NetUtil;
import android.marshon.likequanmintv.mvp.live.LivePlayerView;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by ITMarshon.Chen on 2016/11/27.
 * emal:itmarshon@163.com
 * desc:
 */

public abstract class BaseLiveUI extends BaseActivity implements LivePlayerView {

    protected boolean mIsActivityPaused = true;
    protected Toast mToast = null;


    @Override
    protected void onResume() {
        super.onResume();
        mIsActivityPaused = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsActivityPaused = true;
    }


    @Override
    public void onBackPressed() {
        if (getRequestedOrientation()== ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return;
        }

        super.onBackPressed();
    }

    protected void showToastTips(final String tips) {
        if (mIsActivityPaused) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                }
                showToast(tips);
            }
        });
    }

    private static final int MESSAGE_ID_RECONNECTING = 0x01;
    private void sendReconnectMessage() {
        showToastTips("正在重连...");
        onReConnecting();
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MESSAGE_ID_RECONNECTING), 500);
    }

    protected Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what != MESSAGE_ID_RECONNECTING) {
                return;
            }
            if (mIsActivityPaused) {
                finish();
                return;
            }
            if (!NetUtil.isNetWorkConnectted()) {
                sendReconnectMessage();
                return;
            }
            toPrepare();
        }
    };




    protected abstract void toPrepare();
}
