package android.marshon.likequanmintv.librarys.mvpbase;

import android.marshon.likequanmintv.base.APP;
import android.marshon.likequanmintv.librarys.utils.LogUtil;
import android.marshon.likequanmintv.librarys.utils.NetUtil;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


/**
 * Created by It.Marshon on 2016/11/21 0021 13:10
 */

public class BaseInteractor {

    protected Gson mGson=new Gson();

    //检测网络转改
    protected boolean hasNetWork(){
        if (!NetUtil.isNetWorkConnectted()){
            Toast.makeText(APP.getContext(), "请连接网络或稍后重试...", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



    protected void exceptionHandler(Throwable e){
        LogUtil.e("error!!:"+e.toString());

    }

    public <T> List<T> convertList(String jsonStr) {
        Type token =new TypeToken<List<T>>(){}.getType();
        return mGson.fromJson(jsonStr,token);
    }
}
