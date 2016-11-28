package android.marshon.likequanmintv.view.mediacontroll;

import android.animation.Animator;
import android.content.Context;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.PlayBean;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.glide.glide.GlideCircleTransform;

/**
 * Created by ITMarshon.Chen on 2016/11/27.
 * emal:itmarshon@163.com
 * desc:
 */

public class VerticalMediaControllView extends FrameLayout {

    private Handler mHandler=new Handler();

    private ImageView ic_head;
    private TextView nickName;
    private TextView titleName;
    private ImageView ivIsFocus;
    private Switch swRemind;


    // Content View Elements

    public ImageView ivBack;
    public LinearLayout ll_four_container;
    public ImageView theliveMore;
    public CheckBox check_gift_switch;
    public CheckBox check_live_pause;
    public ImageView ivFullscreen;
    public TextView theliveNum;
    public LinearLayout ll_jubao_and_share;
    public TextView tv_jubao;
    public TextView tv_share;
    public FrameLayout live_vertical_bottom;
    private View rootView;
    private OnVerticalControllListener onVerticalControllListener;
    private OnFullScreenListener onFullScreenListener;
    private View verMediaControll;
    private View infoContainer;


    public VerticalMediaControllView(Context context) {
        this(context, null, 0);
    }

    public VerticalMediaControllView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalMediaControllView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initLister();
        onCreate();
    }
    //init
    private void initView(Context context) {
        rootView = View.inflate(context, R.layout.widget_ver_controller, this);

        ivBack = (ImageView) findViewById(R.id.iv_thelive_back);
        verMediaControll=findViewById(R.id.verMediaControll);
        infoContainer=findViewById(R.id.infoContainer);
        ll_four_container = (LinearLayout) findViewById(R.id.ll_four_container);
        theliveMore = (ImageView) findViewById(R.id.iv_thelive_more);
        check_gift_switch = (CheckBox) findViewById(R.id.check_gift_switch);
        check_live_pause = (CheckBox) findViewById(R.id.check_live_pause);
        ivFullscreen = (ImageView) findViewById(R.id.iv_thelive_fullscreen);
        theliveNum = (TextView) findViewById(R.id.tv_thelive_num);
        ll_jubao_and_share = (LinearLayout) findViewById(R.id.ll_jubao_and_share);
        tv_jubao = (TextView) findViewById(R.id.tv_jubao);
        tv_share = (TextView) findViewById(R.id.tv_share);
        live_vertical_bottom = (FrameLayout) findViewById(R.id.live_vertical_bottom);

        //info
        ic_head=(ImageView)findViewById(R.id.ic_head);
        nickName=(TextView)findViewById(R.id.nickName);
        titleName =(TextView)findViewById(R.id.title);
        ivIsFocus =(ImageView)findViewById(R.id.iv_thelive_focus);
        swRemind =(Switch)findViewById(R.id.switchview_remind);

    }
    private void initLister() {
        ivFullscreen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onFullScreenListener!=null){
                    onFullScreenListener.onVerticalClickFullScreen();
                }
                //横屏显示
                onDestroy();

            }
        });
        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onVerticalControllListener!=null){
                    onVerticalControllListener.onVerticalClickBack();
                }
            }
        });
        theliveMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_jubao_and_share.setVisibility(VISIBLE);
                show();
            }
        });

    }
    //data
    public void setData(PlayBean playBean){
        theliveNum.setText(""+playBean.view);
        Glide.with(getContext()).load(""+playBean.avatar).transform(new GlideCircleTransform(getContext()))
                .into(ic_head);
        nickName.setText(""+playBean.nick);
        titleName.setText(""+playBean.title);
        ivIsFocus.setSelected(playBean.follow>0);
        swRemind.setChecked(true);
    }
    //life recycle
    public void  onCreate(){
        show();
        //info // TODO: 2016/11/29
        infoContainer.setVisibility(VISIBLE);

    }
    public void  onDestroy(){
        verMediaControll.setVisibility(INVISIBLE);
        theliveNum.setVisibility(VISIBLE);
        mHandler.removeCallbacksAndMessages(null);
        //// TODO: 2016/11/29
        infoContainer.setVisibility(GONE);
    }

    //action
    public boolean onTouchEvent(boolean isVertical,MotionEvent event) {
        if (isVertical){
            show();
        }
        return onTouchEvent(event);
    }
    private Runnable hiddenControll=new Runnable() {
        @Override
        public void run() {
            verMediaControll.animate().alpha(0).setDuration(1000).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    hide();

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
        }
    };
    private void hide(){
        verMediaControll.setAlpha(1f);
        verMediaControll.setVisibility(INVISIBLE);
        theliveNum.setVisibility(VISIBLE);
    }
    private void show(){
        ll_jubao_and_share.setVisibility(GONE);
        verMediaControll.setVisibility(VISIBLE);
        mHandler.removeCallbacks(hiddenControll);
        mHandler.postDelayed(hiddenControll,3000);
    }

    //listener
    public void setOnVerticalControllListener(OnVerticalControllListener onVerticalControllListener) {
        this.onVerticalControllListener = onVerticalControllListener;
    }
    public interface OnVerticalControllListener{
        void onVerticalClickPause();
        void onVerticalClickStart();
        void onVerticalClickBack();
    }
    public void setOnFullScreenListener(OnFullScreenListener onFullScreenListener) {
        this.onFullScreenListener = onFullScreenListener;
    }
    public interface OnFullScreenListener{
        void onVerticalClickFullScreen();
    }
}
