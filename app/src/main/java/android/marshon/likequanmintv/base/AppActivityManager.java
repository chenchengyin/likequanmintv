package android.marshon.likequanmintv.base;

import android.app.Activity;
import android.content.Context;
import android.marshon.likequanmintv.librarys.utils.LogUtil;

import java.util.Stack;

/**
 * 管理activity
 */

public class AppActivityManager {
    private static Stack<Activity> mActivityStack;
    private static AppActivityManager mAppManager;

    private AppActivityManager() {
    }

    /**
     * 单一实例
     */
    public static AppActivityManager getInstance() {
        if (mAppManager == null) {
            mAppManager = new AppActivityManager();
        }
        return mAppManager;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }

        mActivityStack.add(activity);
    }

    /**
     * 获取栈顶Activity
     */
    public Activity getTopActivity() {
        return mActivityStack.lastElement();
    }

    /**
     * 结束栈顶Activity
     */
    public void killTopActivity() {
        Activity activity = mActivityStack.lastElement();
        killActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    private void killActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void killActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                killActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    private void killAllActivity() {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }

    /**
     * 退出应用程序
     */
    @SuppressWarnings("deprecation")
    public void AppExit(Context context) {
        try {
            killAllActivity();
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            LogUtil.e(""+e);

        }
    }
}
