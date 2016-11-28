package android.marshon.likequanmintv.view.mediacontroll;

import android.animation.Animator;
import android.content.Context;
import android.marshon.likequanmintv.R;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by It.Marshon on 2016/11/28 0028 14:14
 */

public class HorMediaControllView extends FrameLayout {

    private Handler mHandler=new Handler();
    private View rootView;
    public RelativeLayout layout_topbar;
    public ImageView img_btn_back;
    public LinearLayout top_ll;
    public CheckBox check_gift_switch;
    public TextView txt_btn_clarity;
    public CheckBox check_focus;
    public ImageView img_btn_share;
    public TextView txt_btn_describe;
    public TextView txt_get_seed;
    public ListView lv_quality;
    public TextView tv_volume_brightness;
    public LinearLayout layout_bottom_thelive;
    public CheckBox player_switch;
    public ImageView img_btn_refresh;
    public ImageView txt_btn_hot_word;
    public EditText ediit_comment;
    public ImageView img_btn_send;
    public CheckBox rad_btn_danmu_switch;
    public ImageView img_btn_gift;
    private OnHorControllListener onHorControllListener;

    public HorMediaControllView(Context context) {
        this(context, null,0);
    }

    public HorMediaControllView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HorMediaControllView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initListener();
    }
    //init
    private void initView(Context context) {
        rootView = View.inflate(context, R.layout.widget_hor_controller, this);
        layout_topbar = (RelativeLayout) findViewById(R.id.layout_topbar);
        img_btn_back = (ImageView) findViewById(R.id.img_btn_back);
        top_ll = (LinearLayout) findViewById(R.id.top_ll);
        check_gift_switch = (CheckBox) findViewById(R.id.check_gift_switch);
        txt_btn_clarity = (TextView) findViewById(R.id.txt_btn_clarity);
        check_focus = (CheckBox) findViewById(R.id.check_focus);
        img_btn_share = (ImageView) findViewById(R.id.img_btn_share);
        txt_btn_describe = (TextView) findViewById(R.id.txt_btn_describe);
        txt_get_seed = (TextView) findViewById(R.id.txt_get_seed);
        lv_quality = (ListView) findViewById(R.id.lv_quality);
        tv_volume_brightness = (TextView) findViewById(R.id.tv_volume_brightness);
        layout_bottom_thelive = (LinearLayout) findViewById(R.id.layout_bottom_thelive);
        player_switch = (CheckBox) findViewById(R.id.rad_btn_player_switch);
        img_btn_refresh = (ImageView) findViewById(R.id.img_btn_refresh);
        txt_btn_hot_word = (ImageView) findViewById(R.id.txt_btn_hot_word);
        ediit_comment = (EditText) findViewById(R.id.ediit_comment);
        img_btn_send = (ImageView) findViewById(R.id.img_btn_send);
        rad_btn_danmu_switch = (CheckBox) findViewById(R.id.rad_btn_danmu_switch);
        img_btn_gift = (ImageView) findViewById(R.id.img_btn_gift);
    }
    private void initListener() {
        img_btn_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onHorControllListener!=null){
                    onHorControllListener.onHorClickBack();
                }

            }
        });
        player_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (onHorControllListener!=null){
                        onHorControllListener.onHorClickPause();
                    }
                }else {
                    if (onHorControllListener!=null){
                        onHorControllListener.onHorClickStart();
                    }
                }
            }
        });
    }
    //life recycler
    public void  onCreate(){
        show();
    }
    public void  onDestroy(){
        rootView.setVisibility(INVISIBLE);
        mHandler.removeCallbacksAndMessages(null);
    }

    //action
    public boolean onTouchEvent(boolean isVertical,MotionEvent event) {
        if (!isVertical){
            show();
        }
        return onTouchEvent(event);
    }
    private void hide(){
        rootView.setAlpha(1f);
        rootView.setVisibility(INVISIBLE);
    }
    private void show(){
        rootView.setVisibility(VISIBLE);
        mHandler.removeCallbacks(hiddenControll);
        mHandler.postDelayed(hiddenControll,3000);
    }

    //listener
    private Runnable hiddenControll=new Runnable() {
        @Override
        public void run() {
            rootView.animate().alpha(0).setDuration(1000).setListener(new Animator.AnimatorListener() {
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
    public void setOnHorControllListener(OnHorControllListener onHorControllListener) {
        this.onHorControllListener = onHorControllListener;
    }
    public interface OnHorControllListener{
        void onHorClickPause();
        void onHorClickStart();
        void onHorClickBack();
    }

}
