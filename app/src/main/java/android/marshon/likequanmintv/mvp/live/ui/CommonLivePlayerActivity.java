package android.marshon.likequanmintv.mvp.live.ui;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.controller.LivePlayerHolder;
import android.marshon.likequanmintv.listener.MyOnVerticalControllListener;
import android.marshon.likequanmintv.utils.SystemBarUtils;
import android.marshon.likequanmintv.view.VerticalMediaControllView;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 *  This demo shows how to use PLMediaPlayer API playing video stream
 */
public class CommonLivePlayerActivity extends BaseLivePlayerActivity implements VerticalMediaControllView.OnFullScreenListener {

    private static final String TAG = CommonLivePlayerActivity.class.getSimpleName();



    private SurfaceView mSurfaceView;
    private VerticalMediaControllView verticalControll;
    private View mLoadingView;
    private String mVideoPath = null;



    private int mPortWidth;
    private int mPortHeight;
    private LivePlayerHolder playerHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_commonliveplayer);

//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setNavigationBarTintEnabled(true);
//        tintManager.setStatusBarTintColor(getResources().getColor());

        SystemBarUtils.setStatusBarTranslate(this,R.color.colorPrimary);

        mLoadingView = findViewById(R.id.LoadingView);
        mSurfaceView = (SurfaceView) findViewById(R.id.SurfaceView);
        int codec = getIntent().getIntExtra("mediaCodec", 0);
        mVideoPath = getIntent().getStringExtra("videoPath");
        mVideoPath="http://hls.quanmin.tv/live/4242823_L4/playlist.m3u8";
        playerHolder = new LivePlayerHolder(this,mSurfaceView,codec,mVideoPath);
        verticalControll=(VerticalMediaControllView)findViewById(R.id.verticalControll);
        verticalControll.setOnVerticalControllListener(new MyOnVerticalControllListener(this,playerHolder));
        verticalControll.setOnFullScreenListener(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        playerHolder.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playerHolder.startPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        playerHolder.pausePlayer();
    }

    @Override
    protected void toPrepare() {
        playerHolder.prepare();
    }

    public void onClickPlay(View v) {
            playerHolder.startPlayer();
    }

    public void onClickPause(View v) {
        playerHolder.pausePlayer();
    }

    public void onClickResume(View v) {
        playerHolder.startPlayer();
    }

    public void onClickStop(View v) {
        playerHolder.stopPlayer();

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            //portrait
        }else {
            //landscape
        }

    }

    @Override
    public void onBackPressed() {
        if (getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            FrameLayout.LayoutParams params =
                    (FrameLayout.LayoutParams) mSurfaceView.getLayoutParams();
            params.width=mPortWidth;
            params.height=mPortHeight;
            float dimension = getResources().getDimension(R.dimen.status_bar_height);
            params.topMargin= (int) dimension;
            return;
        }
        playerHolder.release();
        super.onBackPressed();
    }


    @Override
    public void onConnecting() {
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onReConnecting() {
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onConnectSucces() {
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void onConnectFailed() {
        showToastTips("连接失败");
    }

    @Override
    public void onPlayComleted() {
        showToastTips("主播离开了");
    }

    @Override
    public void onPlayerStart() {

    }

    @Override
    public void onPlayePause() {

    }

    @Override
    public void onVerticalClickFullScreen() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

        SystemBarUtils.setStatusBarTranslate(this,R.color.transparent);

        Display display =
                getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        int heightPixels = metrics.heightPixels;
        int widthPixels = metrics.widthPixels;
        Log.e("metrics","heightPixels"+heightPixels);
        Log.e("metrics","widthPixels"+widthPixels);
        FrameLayout.LayoutParams params =
                (FrameLayout.LayoutParams) mSurfaceView.getLayoutParams();

        int height = params.height;
        int width = params.width;
        params.topMargin=0;
        Log.e("mSurfaceView","width"+width);
        Log.e("mSurfaceView","height"+height);

        mPortWidth=width;
        mPortHeight=height;
        params.width=widthPixels;
        params.height=heightPixels;


//        mSurfaceView.setLayoutParams(params);

    }
}
