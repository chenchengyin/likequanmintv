package android.marshon.likequanmintv.librarys.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.base.APP;
import android.marshon.likequanmintv.di.component.ActivityComponent;
import android.marshon.likequanmintv.di.component.DaggerActivityComponent;
import android.marshon.likequanmintv.di.module.ActivityModule;
import android.marshon.likequanmintv.librarys.mvpbase.BasePresenter;
import android.marshon.likequanmintv.librarys.mvpbase.BaseView;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.lang.reflect.Field;

import javax.annotation.Nullable;


/**
 * Created by MarshonIt on 2016/2/25 11:37
 *
 * @email itmarshon@126.com
 */
public class BaseActivity extends AppCompatActivity implements BaseView {
    protected int p=0;

    protected BasePresenter mBasePresent=null;

    //资源文件
    private Resources mResources;
    //布局文件加载器
    private LayoutInflater mLayoutInflater;


    protected ActivityComponent mActivityComponent;


//    @Override
//    public void setContentView(int layoutResID) {
//        View rootView = View.inflate(this, layoutResID, null);
//        super.setContentView(rootView);
//
//
//    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((APP) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    public boolean isTranslateStatusBar(){
        return false;
    }

    public int getColorPrimary(){
        return R.color.colorPrimary;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppActivityManager.getInstance().addActivity(this);
        mResources = getResources();
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏
        //4.4之后实现沉浸式状态栏
        //初始化布局文件加载器
        mLayoutInflater = LayoutInflater.from(this);
        //initDaggerComponent
        initActivityComponent();

        //leak
//        RefWatcher refWatcher = APP.getRefWatcher(this);
//        refWatcher.watch(this);
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_out_right);
    }


    @Override
    protected void onDestroy() {
        AppActivityManager.getInstance().removeActivity(this);
        super.onDestroy();

//        APP.getInstance().getLeakWather().watch(this);
        fixInputMethodManagerLeak(this);
    }

    /**
     * 解决InputMethodManager内存泄露现象
     */
    private static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) destContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f;
        Object obj_get;
        for (String param : arr) {
            try {
                f = imm.getClass().getDeclaredField(param);
                if (!f.isAccessible()) {
                    f.setAccessible(true);
                } // author: sodino mail:sodino@qq.com
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get
                            .getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                        /*if (QLog.isColorLevel()) {
                            QLog.d(ReflecterHelper.class.getSimpleName(), QLog.CLR, "fixInputMethodManagerLeak break, context is not suitable, get_context=" + v_get.getContext()+" dest_context=" + destContext);
                        }*/
                        break;
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onResume() {
        super.onResume();
//        if (mBasePresent!=null)mBasePresent.onResume();
//        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (mBasePresent!=null)mBasePresent.onPause();
//        JPushInterface.onPause(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBasePresent!=null){
            mBasePresent.onPause();
            mBasePresent=null;
        }

    }


    @Override
    public void showLongToast(String msg) {
        Toast.makeText(APP.getContext(),msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress(@Nullable String msg) {


    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(APP.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlert(String msg) {
        new AlertDialog.Builder(this)
         .setTitle("提示")
         .setMessage(""+msg)
         	.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            })
            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            })
         	.show();
    }

    protected void openActivity(Class clazz){
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }




}
