package android.marshon.likequanmintv.mvp.column.interactor;

import android.marshon.likequanmintv.bean.ItemColumn;
import android.marshon.likequanmintv.librarys.http.RetrofitManager;
import android.marshon.likequanmintv.librarys.http.delagate.IGetDataDelegate;
import android.marshon.likequanmintv.librarys.http.rxjava.MSubscriber;
import android.marshon.likequanmintv.librarys.http.rxjava.TransformUtils;
import android.marshon.likequanmintv.librarys.utils.LogUtil;
import android.marshon.likequanmintv.librarys.utils.SPUtils;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

import rx.Subscription;

/**
 * Created by ITMarshon.Chen on 2016/11/24.
 * emal:itmarshon@163.com
 * desc:
 */

public class ColumnFragmentInteractorImpl {
    public ColumnFragmentInteractorImpl(){}

    public Subscription loadColumnList(final IGetDataDelegate<List<ItemColumn>> delegate){
        return RetrofitManager.getInstance()
                .getColumnAPIService().getColumnList("json/categories/list.json")
                .compose(TransformUtils.<JSONArray>defaultSchedulers())
                .subscribe(new MSubscriber<JSONArray>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(JSONArray jsonObject) {
                        super.onNext(jsonObject);
                        try {
                            LogUtil.e("jsonObject"+jsonObject.toString());
//                            JSONArray data = jsonObject.optJSONArray("data");
                            Type token =new TypeToken<List<ItemColumn>>(){}.getType();
                            List<ItemColumn> columnList=mGson.fromJson(jsonObject.toString(),token);
//                            List<PlayBean> playBeanList = convertList(data.toString());
                            delegate.getDataSuccess(columnList);
                        } catch (Exception e) {
                            LogUtil.e("数据有误" + jsonObject);
                        }
                    }
                });
    }
}
