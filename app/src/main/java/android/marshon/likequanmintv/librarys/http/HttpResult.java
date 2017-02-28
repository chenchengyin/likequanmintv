package android.marshon.likequanmintv.librarys.http;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by liukun on 16/3/5.
 */
public class HttpResult<T> {

    public static  int  SUCCESS= 200;  //
    public static  int  SERVERERROR= -1;  //
    public static  int  ORDERFAILED= 40000;  //接单失败，请联系管理员查看。
    public static  int  PARAMERROR= 40002;  //40002	请求参数有误


    public static String SERVERFAILEDMSG= "服务器开小差,请稍后重试";  //


//    200	接单成功
//    -1	系统繁忙
//    40000	接单失败，请联系管理员查看。
//            40002	请求参数有误

    private static Gson mGson =new Gson();
    public int code;
    public String reason;
    public T result;

    public static HttpResult fromJson(JSONObject jsonString, final Class clazz){
        if (jsonString==null){

            return new HttpResult();
        }
        HttpResult httpResult = new HttpResult();
        int code = jsonString.optInt("code");
        httpResult.code=(code);
        httpResult.reason=(jsonString.optString("msg"));
        if (code==PARAMERROR){
            httpResult.reason=("参数错误!");
        }
        JSONObject result = jsonString.optJSONObject("result");
        if (result!=null){
            try{
                if (clazz!=null){
                    httpResult.result=(mGson.fromJson(result.toString(), clazz));

                }else{
                    httpResult.result=(result.toString());
                }
            }catch (Exception e){
                httpResult.result=(result.toString());
            }

        }
        return httpResult;
    }
    public static HttpResult fromJson(JSONObject jsonString, Type type){
        if (jsonString==null){
            HttpResult httpResult = new HttpResult();
            httpResult.reason=("官方"+SERVERFAILEDMSG);
            return httpResult;
        }
        HttpResult httpResult = new HttpResult();
        httpResult.code=(jsonString.optInt("code"));
        httpResult.reason=(jsonString.optString("msg"));
        JSONArray infoArray = jsonString.optJSONArray("result");
        JSONObject info = jsonString.optJSONObject("result");

        //解析code
        if (httpResult.code==SUCCESS){
            //解析数据
            if (info!=null||infoArray!=null){
                try{
                    if (type!=null){
                        if (infoArray==null){
                            httpResult.result=(mGson.fromJson(info.toString(), type));
                        }else{
                            httpResult.result=(mGson.fromJson(infoArray.toString(), type));
                        }
                    }else{
                        httpResult.result=(info.toString());
                    }
                }catch (Exception e){
                    httpResult.result=(info.toString());
                }
            }
        }else if (httpResult.code==SERVERERROR){
             httpResult.result=(new ArrayList<>());
            httpResult.reason=(SERVERFAILEDMSG);
        }

        return httpResult;
    }
    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", result=" + result +
                '}';
    }


    public boolean checkSuccess(){
        if (this==null)return false;
        return code==SUCCESS;
    }
}
