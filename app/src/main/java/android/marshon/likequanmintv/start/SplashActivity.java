package android.marshon.likequanmintv.start;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.librarys.base.BaseActivity;
import android.marshon.likequanmintv.mvp.main.MainActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;

/**
 * Created by Marshon.Chen on 2016/6/1.
 * DESC:
 */
public class SplashActivity extends AppCompatActivity implements Animator.AnimatorListener {


    private View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        start();
    }

    private void start() {
        rootView = findViewById(R.id.main_root);
        final View viewById = findViewById(R.id.tt);
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0.3f,1.0f);
        valueAnimator.setDuration(1200);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                rootView.setAlpha(alpha);
                viewById.setScaleX(alpha);
                viewById.setScaleY(alpha);

            }
        });
        valueAnimator.addListener(this);
        valueAnimator.start();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String firstrun = "";
        if (TextUtils.isEmpty(firstrun)){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
            finish();
        }else{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
            finish();
        }

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
