package android.marshon.likequanmintv.utils;

import android.marshon.likequanmintv.librarys.utils.SPUtils;

/**
 * Created by ITMarshon.Chen on 2017/2/19.
 * emal:itmarshon@163.com
 * desc:
 */

public class OrmKeys {

    public static boolean isNeedUpdate(String key){
        long ormTime = SPUtils.getInstance().getLong(key, 0);
        long nowTime = System.currentTimeMillis();
        long deltaTime = nowTime - ormTime;
        return deltaTime>60*1000*5;
    }

    public static void updateTime(String key){
        SPUtils.getInstance().putLong(key, System.currentTimeMillis());
    }

    public static final String  columnKey="columnKey";

    public static final String  liveKey="liveKey";
    public static final String  recommondcategoryKey="recommondcategoryKey";
    public static final String  RecommendCategories="RecommendCategories";  //首页推荐列表

}
