package android.marshon.likequanmintv.librarys.http.converter;


import android.marshon.likequanmintv.librarys.utils.LogUtil;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type type;
    Gson mGson=new Gson();

    JsonResponseBodyConverter(Type type) {
        this.type=type;
    }


    @Override
    public T convert(ResponseBody value) throws IOException {

        Class clazz= (Class) type;
        String canonicalName = clazz.getCanonicalName();

        if (canonicalName.equals(JSONObject.class.getName())){
            LogUtil.e("进来了");
            try{
                JSONObject jsonObj;
                jsonObj = new JSONObject(value.string());
                return (T) jsonObj;
            }
            catch (Exception e){
                LogUtil.e("解析返回json数据出错:"+e.toString());
            }
        }else{
            T data = mGson.fromJson(value.string(), this.type);
            return data;
        }
        LogUtil.e("返回数据："+value.string());
        return null;
    }
}
