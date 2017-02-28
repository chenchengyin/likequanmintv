package android.marshon.likequanmintv.start;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.base.APP;
import android.marshon.likequanmintv.bean.AppStart;
import android.marshon.likequanmintv.event.BannerEvent;
import android.marshon.likequanmintv.librarys.http.rxjava.MSubscriber;
import android.marshon.likequanmintv.mvp.main.MainActivity;
import android.marshon.likequanmintv.mvp.recommend.interactor.RecommendFragmentInteractorImpl;
import android.marshon.likequanmintv.utils.WebContainerActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscription;

/**
 * Created by Marshon.Chen on 2016/6/1.
 * DESC:
 */
public class SplashActivity extends AppCompatActivity implements Animator.AnimatorListener {


    private static final int CODE_AD = 111;
    private View rootView;
    private RecommendFragmentInteractorImpl mInteractor=new RecommendFragmentInteractorImpl();
    private Subscription mSubscription;
    private static boolean hasAa;
    Handler mhandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        if (!APP.isInited){
            mhandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    start();
                    initData();
                }
            },3000);
        }else {
            start();
            initData();
        }

    }

    private void initData() {

        mSubscription = mInteractor.getStartInfo(new MSubscriber<JSONObject>() {

            @Override
            public void onNext(JSONObject appinfoJson) {
                super.onNext(appinfoJson);
                try {
                    JSONArray androidstartArray = appinfoJson.optJSONArray("androidstart");
                    JSONObject androidstartObject = androidstartArray.getJSONObject(0);
                    Gson mGson=new Gson();

                    JSONArray appfocusArray = appinfoJson.optJSONArray("app-focus");
                    EventBus.getDefault().postSticky(new BannerEvent(appfocusArray));

                    if (androidstartObject!=null){
                        //广告
                        final AppStart mAppStart=mGson.fromJson(androidstartObject.toString(), AppStart.class);
                        if (mAppStart!=null){
                            hasAa=true;
                            //去广告页面

                            mhandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent=new Intent(SplashActivity.this, WebContainerActivity.class);
                                    intent.putExtra(Intent.EXTRA_TITLE,""+mAppStart.title);
                                    intent.putExtra(Intent.EXTRA_TEXT,""+mAppStart.link);
                                    intent.putExtra("isAd",true);
                                    startActivityForResult(intent,CODE_AD);
                                }
                            },1500);

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private void start() {
        rootView = findViewById(R.id.main_root);
        final View viewById = findViewById(R.id.tt);
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0.3f,1.0f);
        valueAnimator.setDuration(1500);
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
        if (hasAa)return;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gotoMain();
    }

    private void gotoMain(){
        String firstrun = "";
        if (TextUtils.isEmpty(firstrun)){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
//            finish();
        }else{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
//            finish();
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK==resultCode&&requestCode==CODE_AD){
            gotoMain();
        }
    }

    @Override
    protected void onDestroy() {
        if (mSubscription!=null&&mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
        super.onDestroy();

    }
}
