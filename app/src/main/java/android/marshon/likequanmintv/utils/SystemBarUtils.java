package android.marshon.likequanmintv.utils;

import android.marshon.likequanmintv.R;
import android.support.v7.app.AppCompatActivity;

import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by ITMarshon.Chen on 2016/11/23.
 * emal:itmarshon@163.com
 * desc:
 */

public class SystemBarUtils {


    public static void setStatusBarTranslate(AppCompatActivity mActivity, int resId){

        SystemBarTintManager tintManager = new SystemBarTintManager(mActivity);
        if (resId== R.color.transparent){
            // enable status bar tint
            tintManager.setStatusBarTintEnabled(false);
            // enable navigation bar tint
            tintManager.setNavigationBarTintEnabled(false);
            //noinspection deprecation
        }else {
            // enable status bar tint
            tintManager.setStatusBarTintEnabled(true);
            // enable navigation bar tint
            tintManager.setNavigationBarTintEnabled(true);
            // enable navigation bar tint
        }
        tintManager.setStatusBarTintColor(mActivity.getResources().getColor(resId));

    }
}
