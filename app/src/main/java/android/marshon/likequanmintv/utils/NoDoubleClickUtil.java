package android.marshon.likequanmintv.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by fulunyong on 2016/10/17 0017 16:33
 */

public class NoDoubleClickUtil {

    private SparseArray<View> clicks=new SparseArray(5);

    private static  NoDoubleClickUtil util;


    public static NoDoubleClickUtil getInstatnce(){
        if (util==null){
            util= new NoDoubleClickUtil();
        }
        return util;

    }
    public boolean isClicked(final View view){
        View viewTemp = clicks.get(view.getId());
        if (viewTemp!=null){
            return true;
        }else{
            clicks.put(view.getId(),view);
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    clicks.remove(view.getId());
                }
            },500);
            return false;
        }
    }

    public static void detach(){
        if (util!=null){
            util=null;
        }
    }
}
