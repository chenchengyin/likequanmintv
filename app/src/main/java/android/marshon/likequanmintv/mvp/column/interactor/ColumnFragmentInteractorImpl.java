package android.marshon.likequanmintv.mvp.column.interactor;

import android.marshon.likequanmintv.base.APP;
import android.marshon.likequanmintv.bean.ItemColumn;
import android.marshon.likequanmintv.librarys.http.RetrofitManager;
import android.marshon.likequanmintv.librarys.http.delagate.IGetDataDelegate;
import android.marshon.likequanmintv.librarys.http.rxjava.MSubscriber;
import android.marshon.likequanmintv.librarys.http.rxjava.TransformUtils;
import android.marshon.likequanmintv.librarys.utils.LogUtil;
import android.marshon.likequanmintv.librarys.utils.SPUtils;
import android.os.SystemClock;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by ITMarshon.Chen on 2016/11/24.
 * emal:itmarshon@163.com
 * desc:
 */

public class ColumnFragmentInteractorImpl {
    public ColumnFragmentInteractorImpl(){}

    public Subscription loadColumnList(final IGetDataDelegate<List<ItemColumn>> delegate){
        long columnfragment_load = SPUtils.getInstance().getLong("columnfragment_load", 0);
        long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        if (currentThreadTimeMillis-columnfragment_load<=1000*60*5){
           return Observable.create(new Observable.OnSubscribe<List<ItemColumn>>() {
                @Override
                public void call(Subscriber<? super List<ItemColumn>> subscriber) {
                    List<ItemColumn> itemColumns = APP.getWriteableDaoSession().getItemColumnDao().loadAll();
                    subscriber.onNext(itemColumns);
                }
            })
            .compose(TransformUtils.<List<ItemColumn>>defaultSchedulers())
             .subscribe(new Action1<List<ItemColumn>>() {
                 @Override
                 public void call(List<ItemColumn> itemColumns) {
                     delegate.getDataSuccess(itemColumns);
                     loadColumnListFromNet(delegate);

                 }
             });
        }else {
            return loadColumnListFromNet(delegate);
        }

    }


    private Subscription loadColumnListFromNet(final IGetDataDelegate<List<ItemColumn>> delegate){
        MSubscriber<JSONArray> mSubscriber = new MSubscriber<JSONArray>() {
            @Override
            public void onNext(JSONArray jsonObject) {
                super.onNext(jsonObject);
                try {
                    LogUtil.e("jsonObject" + jsonObject.toString());
                    Type token = new TypeToken<List<ItemColumn>>() {
                    }.getType();
                    List<ItemColumn> columnList = mGson.fromJson(jsonObject.toString(), token);
                    delegate.getDataSuccess(columnList);
                    APP.getWriteableDaoSession().getItemColumnDao().insertOrReplaceInTx(columnList);

                } catch (Exception e) {
                    LogUtil.e("数据有误" + jsonObject);
                }
            }
        };
        return RetrofitManager.getInstance()
                .getColumnAPIService().getColumnList("json/categories/list.json")
                .compose(TransformUtils.<JSONArray>defaultSchedulers())
                .subscribe(mSubscriber);
    }
}
