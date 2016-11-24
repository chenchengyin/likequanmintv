package android.marshon.likequanmintv.adapter;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.librarys.base.BaseActivity;
import android.view.View;

/**
 * Created by ITMarshon.Chen on 2016/11/24.
 * emal:itmarshon@163.com
 * desc:
 */

public class BannerHeadViewHolder {

    public View headView;
    private Object banner;

    public BannerHeadViewHolder(BaseActivity mContext){
        headView = View.inflate(mContext, R.layout.widget_bannerview, null);

    }


    public void setBannerData(){

    }

    public Object getBanner() {
        if (banner==null){
            //获取数据
        }
        return banner;
    }
}
