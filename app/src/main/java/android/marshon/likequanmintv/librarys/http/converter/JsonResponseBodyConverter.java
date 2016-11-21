package android.marshon.likequanmintv.librarys.http.converter;


import android.marshon.likequanmintv.librarys.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    JsonResponseBodyConverter() {

    }

    @Override
    public T convert(ResponseBody value) throws IOException,SocketTimeoutException {

        try{
            JSONObject jsonObj;
            try {
                jsonObj = new JSONObject(value.string());
                return (T) jsonObj;
            } catch(JSONException e) {
                return null;
            }
        }
        catch (Exception e){
//            LogUtil.e("泛型只允许为 HttpResult ,请查看com.huashen.android.lock.http.HttpResult");
            LogUtil.e("泛型只允许为 String,错误信息为:"+e.toString());
            if ( e instanceof SocketTimeoutException)
            throw new SocketTimeoutException(e.getMessage());
        }
        return (T) value;

    }
}
