package android.marshon.likequanmintv.controller;

import android.content.Context;
import android.marshon.likequanmintv.base.APP;
import android.marshon.likequanmintv.librarys.utils.LogUtil;
import android.marshon.likequanmintv.mvp.live.ui.BaseLiveUI;
import android.media.AudioManager;
import android.os.PowerManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;

import java.io.IOException;

/**
 * Created by ITMarshon.Chen on 2016/11/27.
 * emal:itmarshon@163.com
 * desc:
 */

public class LivePlayerHolder {

    private static final String TAG = LivePlayerHolder.class.getSimpleName();
    private  Integer codec;
    private BaseLiveUI mActivity;
    private AVOptions mAVOptions;
    public int mSurfaceWidth;
    public int mSurfaceHeight;
    public SurfaceView mSurfaceView;
    public PLMediaPlayer mMediaPlayer;
    private String mVideoPath;
    private boolean mIsStopped = false;


    public LivePlayerHolder(BaseLiveUI mActivity, SurfaceView surfaceView, Integer codec, String mVideoPath){
        this.mActivity=mActivity;
        this.codec=codec;
        this.mVideoPath=mVideoPath;
        this.mSurfaceView=surfaceView;
        //init
        mSurfaceView.getHolder().addCallback(mCallback);
        mAVOptions = new AVOptions();
        mAVOptions.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        mAVOptions.setInteger(AVOptions.KEY_GET_AV_FRAME_TIMEOUT, 10 * 1000);
        mAVOptions.setInteger(AVOptions.KEY_LIVE_STREAMING, 1);
        mAVOptions.setInteger(AVOptions.KEY_DELAY_OPTIMIZATION, 1);
        mAVOptions.setInteger(AVOptions.KEY_MEDIACODEC, codec);

        // whether start play automatically after prepared, default value is 1
        mAVOptions.setInteger(AVOptions.KEY_START_ON_PREPARED, 0);
        AudioManager audioManager = (AudioManager) APP.getContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
    }

    private SurfaceHolder.Callback mCallback = new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            prepare();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            mSurfaceWidth = width;
            mSurfaceHeight = height;
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // release();
            release();
        }

    };



    private PLMediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangedListener = new PLMediaPlayer.OnVideoSizeChangedListener() {
        public void onVideoSizeChanged(PLMediaPlayer mp, int width, int height) {
            Log.i(TAG, "onVideoSizeChanged, width = "+ width + ",height = " + height);
            // resize the display window to fit the screen
            if (width != 0 && height != 0) {
                float ratioW = (float) width/(float) mSurfaceWidth;
                float ratioH = (float) height/(float) mSurfaceHeight;
                float ratio = Math.max(ratioW, ratioH);
                width  = (int) Math.ceil((float)width/ratio);
                height = (int) Math.ceil((float)height/ratio);
//                FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(width, height);
//                layout.gravity = Gravity.CENTER;
//                mSurfaceView.setLayoutParams(layout);
            }
        }
    };


    private PLMediaPlayer.OnPreparedListener mOnPreparedListener = new PLMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(PLMediaPlayer mp) {
            Log.i(TAG, "On Prepared !");
            startPlayer();
            mIsStopped = false;
        }
    };

    private PLMediaPlayer.OnInfoListener mOnInfoListener = new PLMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(PLMediaPlayer mp, int what, int extra) {
            Log.i(TAG, "OnInfo, what = " + what + ", extra = " + extra);
            switch (what) {
                case PLMediaPlayer.MEDIA_INFO_BUFFERING_START:

                    mActivity.onConnecting();
                    break;
                case PLMediaPlayer.MEDIA_INFO_BUFFERING_END:
                case PLMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                    mActivity.onConnectSucces();
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    private PLMediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener = new PLMediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(PLMediaPlayer mp, int percent) {
//            Log.d(TAG, "onBufferingUpdate: " + percent + "%");
        }
    };

    /**
     *  Listen the event of playing complete
     *  For playing local file, it's called when reading the file EOF
     *  For playing network stream, it's called when the buffered bytes played over
     *
     *  If setLooping(true) is called, the player will restart automatically
     *  And ｀onCompletion｀ will not be called
     *
     */
    private PLMediaPlayer.OnCompletionListener mOnCompletionListener = new PLMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(PLMediaPlayer mp) {
            Log.d(TAG, "Play Completed !");
            showToastTips("Play Completed !");
            mActivity.finish(); //// TODO: 2016/11/27 播放完成
        }
    };

    private PLMediaPlayer.OnErrorListener mOnErrorListener = new PLMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(PLMediaPlayer mp, int errorCode) {
            boolean isNeedReconnect = false;
            Log.e(TAG, "Error happened, errorCode = " + errorCode);
            switch (errorCode) {
                case PLMediaPlayer.ERROR_CODE_INVALID_URI:
                    showToastTips("Invalid URL !");
                    break;
                case PLMediaPlayer.ERROR_CODE_404_NOT_FOUND:
                    showToastTips("404 resource not found !");
                    break;
                case PLMediaPlayer.ERROR_CODE_CONNECTION_REFUSED:
                    showToastTips("Connection refused !");
                    break;
                case PLMediaPlayer.ERROR_CODE_CONNECTION_TIMEOUT:
                    showToastTips("Connection timeout !");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_EMPTY_PLAYLIST:
                    showToastTips("Empty playlist !");
                    break;
                case PLMediaPlayer.ERROR_CODE_STREAM_DISCONNECTED:
                    showToastTips("Stream disconnected !");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_IO_ERROR:
                    showToastTips("Network IO Error !");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_UNAUTHORIZED:
                    showToastTips("Unauthorized Error !");
                    break;
                case PLMediaPlayer.ERROR_CODE_PREPARE_TIMEOUT:
                    showToastTips("Prepare timeout !");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_READ_FRAME_TIMEOUT:
                    showToastTips("Read frame timeout !");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.MEDIA_ERROR_UNKNOWN:
                    break;
                default:
                    showToastTips("unknown error !");
                    break;
            }
            // Todo pls handle the error status here, reconnect or call finish()
            release();
            if (isNeedReconnect) {
                mActivity.onReConnecting();
                prepare();
            } else {
                mActivity.finish();
            }
            // Return true means the error has been handled
            // If return false, then `onCompletion` will be called
            return true;
        }
    };

    private void showToastTips(String msg) {
        LogUtil.i(TAG+msg);
    }




    public void prepare() {

        if (mMediaPlayer != null) {
            mMediaPlayer.setDisplay(mSurfaceView.getHolder());
            return;
        }

        try {

            mMediaPlayer = new PLMediaPlayer(mActivity, mAVOptions);
            mMediaPlayer.setOnPreparedListener(mOnPreparedListener);
            mMediaPlayer.setOnVideoSizeChangedListener(mOnVideoSizeChangedListener);
            mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            mMediaPlayer.setOnErrorListener(mOnErrorListener);
            mMediaPlayer.setOnInfoListener(mOnInfoListener);
            mMediaPlayer.setOnBufferingUpdateListener(mOnBufferingUpdateListener);

            // set replay if completed
            // mMediaPlayer.setLooping(true);

            mMediaPlayer.setWakeMode(mActivity, PowerManager.PARTIAL_WAKE_LOCK);

            mMediaPlayer.setDataSource(mVideoPath);
            mMediaPlayer.setDisplay(mSurfaceView.getHolder());
            mMediaPlayer.prepareAsync();
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startPlayer(){

        if (mIsStopped||mMediaPlayer==null) {
            prepare();
        } else {

            mMediaPlayer.start();
            mActivity.onPlayerStart();
        }

    }
    public void pausePlayer(){
        if (mMediaPlayer!=null)
            mMediaPlayer.pause();
        mActivity.onPlayePause();
    }

    public void stopPlayer(){
        if (mMediaPlayer!=null){
            mIsStopped = true;
            mMediaPlayer.stop();
            mMediaPlayer.reset();
        }

        mMediaPlayer=null;
    }
    public void releaseWithoutStop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.setDisplay(null);
        }
    }


    public void onResume() {
        mSurfaceView.getHolder().addCallback(mCallback);
        startPlayer();
    }

    public void onPause() {
        pausePlayer();
    }


    public void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        if (mSurfaceView.getHolder()!=null){
            mSurfaceView.getHolder().removeCallback(mCallback);
        }

        AudioManager audioManager = (AudioManager) APP.getContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.abandonAudioFocus(null);

    }


}
