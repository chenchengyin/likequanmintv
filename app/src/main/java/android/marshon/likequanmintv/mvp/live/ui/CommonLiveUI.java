package android.marshon.likequanmintv.mvp.live.ui;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.controller.LivePlayerHolder;
import android.marshon.likequanmintv.controller.RoomDataController;
import android.marshon.likequanmintv.listener.MyOnVerticalControllListener;
import android.marshon.likequanmintv.mvp.live.LivePlayerPresenterImpl;
import android.marshon.likequanmintv.view.mediacontroll.HorMediaControllView;
import android.marshon.likequanmintv.view.mediacontroll.VerticalMediaControllView;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import javax.inject.Inject;

import static android.marshon.likequanmintv.R.id.bgImg;


/**
 *  This demo shows how to use PLMediaPlayer API playing video stream
 */
public class CommonLiveUI extends BaseLiveUI implements VerticalMediaControllView.OnFullScreenListener {
    private static final String TAG = CommonLiveUI.class.getSimpleName();
    private SurfaceView mSurfaceView;
    private VerticalMediaControllView verticalControll;
    private View mLoadingView;

    private int mPortWidth;
    private int mPortHeight;
    private LivePlayerHolder playerHolder;
    private View mStatusbar;
    private HorMediaControllView horizontalControll;
    private PlayBean mPlayBean;

    @Inject
    LivePlayerPresenterImpl livePlayerPresenterImpl;
    private RoomDataController mRoomDataController;
    private int mCodec;
    private String mPlayerPath;
    private ImageView bgImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_commonliveplayer);
        mActivityComponent.inject(this);
        livePlayerPresenterImpl.attachView(this);
        initPlayer();
        initVerControll();
        initHorContrll();
        initData();
    }

    private void initPlayer() {
        mStatusbar = findViewById(R.id.statusbar);
        mLoadingView = findViewById(R.id.LoadingView);
        bgImage = (ImageView) findViewById(R.id.bgImg);
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mPlayBean = (PlayBean) getIntent().getSerializableExtra("playBean");
        mCodec = getIntent().getIntExtra("mediaCodec", 0);
    }

    private void initVerControll(){
        verticalControll=(VerticalMediaControllView)findViewById(R.id.verticalControll);
        verticalControll.setOnVerticalControllListener(new MyOnVerticalControllListener(this,playerHolder));
        verticalControll.setOnFullScreenListener(this);
    }

    private void initHorContrll() {
        horizontalControll=(HorMediaControllView)findViewById(R.id.horizontalControll);
    }

    private void initData() {
        if (mPlayBean!=null){
            livePlayerPresenterImpl.enterRoom(mPlayBean.uid);
        }
        Glide.with(this)
                .load(mPlayBean.thumb)
                .fitCenter()
                .into(bgImage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (playerHolder!=null)
        playerHolder.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (playerHolder!=null)
        playerHolder.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (playerHolder!=null)
        playerHolder.onPause();
    }

    @Override
    protected void toPrepare() {
        if (playerHolder!=null)
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
    public void setRequestedOrientation(int requestedOrientation) {
        super.setRequestedOrientation(requestedOrientation);
        if (requestedOrientation==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            verticalControll.onCreate();
            horizontalControll.onDestroy();
        }else {
            horizontalControll.onCreate();
            verticalControll.onDestroy();
        }
    }

    @Override
    public void onBackPressed() {
        if (getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            ViewGroup.LayoutParams params =
                    (ViewGroup.LayoutParams) mSurfaceView.getLayoutParams();
            params.width=mPortWidth;
            params.height=mPortHeight;

            ViewGroup.LayoutParams mStatusbarParams = mStatusbar.getLayoutParams();
            mStatusbarParams.height= (int) (getResources().getDimension(R.dimen.status_bar_height)+0.5f);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//            float dimension = getResources().getDimension(R.dimen.status_bar_height);
//            params.topMargin= (int) dimension;
            return;
        }
        if (playerHolder!=null)
        playerHolder.release();
        super.onBackPressed();
    }


    @Override
    public void onConnecting() {

        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onReConnecting() {
        onConnecting();
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
//        bgImage.setVisibility(View.GONE);
        bgImage.animate().alpha(0).setDuration(1000).start();

    }

    @Override
    public void onPlayePause() {

    }

    @Override
    public void onRoomData(JSONObject roomJson) {
        mRoomDataController = new RoomDataController(roomJson);
        mPlayerPath = mRoomDataController.getPlayerPath(0);
        playerHolder = new LivePlayerHolder(this,mSurfaceView,mCodec,mPlayerPath);
        playerHolder.startPlayer();
    }

    @Override
    public void onVerticalClickFullScreen() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Display display =
                getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        int heightPixels = metrics.heightPixels;
        int widthPixels = metrics.widthPixels;
        Log.e("metrics","heightPixels"+heightPixels);
        Log.e("metrics","widthPixels"+widthPixels);
        ViewGroup.LayoutParams params =
                (ViewGroup.LayoutParams) mSurfaceView.getLayoutParams();

        int height = params.height;
        int width = params.width;

        //status bar
        ViewGroup.LayoutParams mStatusbarParams = mStatusbar.getLayoutParams();
        mStatusbarParams.height=0;
        getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);

        Log.e("mSurfaceView","width"+width);
        Log.e("mSurfaceView","height"+height);

        mPortWidth=width;
        mPortHeight=height;
        params.width=widthPixels;
        params.height=heightPixels;


//        mSurfaceView.setLayoutParams(params);

    }
}
