package android.marshon.likequanmintv.view.mediacontroll;

import android.content.Context;
import android.marshon.likequanmintv.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by ITMarshon.Chen on 2016/11/27.
 * emal:itmarshon@163.com
 * desc:
 */

public class VerticalMediaControllView extends FrameLayout {
    private ViewGroup verticalMediaControll;
    private ImageView ic_head;
    private TextView nickName;
    private TextView nicktitleName;
    private ImageView iv_thelive_focus;
    private Switch switchview_remind;

    // Content View Elements

    public ImageView ivBack;
    public LinearLayout ll_four_container;
    public ImageView iv_thelive_more;
    public CheckBox check_gift_switch;
    public CheckBox check_live_pause;
    public ImageView ivFullscreen;
    public TextView tv_thelive_num;
    public LinearLayout ll_jubao_and_share;
    public TextView tv_jubao;
    public TextView tv_share;
    public FrameLayout live_vertical_bottom;
    private OnVerticalControllListener onVerticalControllListener;
    private OnFullScreenListener onFullScreenListener;

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
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.widget_ver_controller, this);

        verticalMediaControll = (ViewGroup) findViewById(R.id.verticalMediaControll);
        ivBack = (ImageView) findViewById(R.id.iv_thelive_back);
        ll_four_container = (LinearLayout) findViewById(R.id.ll_four_container);
        iv_thelive_more = (ImageView) findViewById(R.id.iv_thelive_more);
        check_gift_switch = (CheckBox) findViewById(R.id.check_gift_switch);
        check_live_pause = (CheckBox) findViewById(R.id.check_live_pause);
        ivFullscreen = (ImageView) findViewById(R.id.iv_thelive_fullscreen);
        tv_thelive_num = (TextView) findViewById(R.id.tv_thelive_num);
        ll_jubao_and_share = (LinearLayout) findViewById(R.id.ll_jubao_and_share);
        tv_jubao = (TextView) findViewById(R.id.tv_jubao);
        tv_share = (TextView) findViewById(R.id.tv_share);
        live_vertical_bottom = (FrameLayout) findViewById(R.id.live_vertical_bottom);

        //info
        ic_head=(ImageView)findViewById(R.id.ic_head);
        nickName=(TextView)findViewById(R.id.nickName);
        nicktitleName=(TextView)findViewById(R.id.title);
        iv_thelive_focus=(ImageView)findViewById(R.id.iv_thelive_focus);
        switchview_remind=(Switch)findViewById(R.id.switchview_remind);

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
    }


    public void  onCreate(){
        setVisibility(VISIBLE);
    }

    public void  onDestroy(){
        setVisibility(GONE);

    }


    public void setOnVerticalControllListener(OnVerticalControllListener onVerticalControllListener) {
        this.onVerticalControllListener = onVerticalControllListener;
    }

    public interface OnVerticalControllListener{
        void onVerticalClickPause();
        void onVerticalClickStart();
        void onVerticalClickBack();
        void onVerticalClickShare();
    }
    public void setOnFullScreenListener(OnFullScreenListener onFullScreenListener) {
        this.onFullScreenListener = onFullScreenListener;
    }

    public interface OnFullScreenListener{
        void onVerticalClickFullScreen();
    }
}
