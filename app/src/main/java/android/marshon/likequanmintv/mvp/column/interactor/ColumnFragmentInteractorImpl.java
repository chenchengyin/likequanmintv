package android.marshon.likequanmintv.mvp.column.interactor;

import android.marshon.likequanmintv.base.APP;
import android.marshon.likequanmintv.bean.ItemColumn;
import android.marshon.likequanmintv.librarys.http.RetrofitManager;
import android.marshon.likequanmintv.librarys.http.delagate.IGetDataDelegate;
import android.marshon.likequanmintv.librarys.http.rxjava.MSubscriber;
import android.marshon.likequanmintv.librarys.http.rxjava.TransformUtils;
import android.marshon.likequanmintv.librarys.utils.LogUtil;
import android.marshon.likequanmintv.thread.ThreadManager;
import android.marshon.likequanmintv.utils.OrmKeys;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
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

        boolean needUpdate = OrmKeys.isNeedUpdate(OrmKeys.columnKey);
        if (needUpdate){
            return loadColumnListFromNet(delegate);
        }else {
            return Observable.create(new Observable.OnSubscribe<List<ItemColumn>>() {
                @Override
                public void call(Subscriber<? super List<ItemColumn>> subscriber) {
                    List<ItemColumn> itemColumns = APP.getWriteableDaoSession().getItemColumnDao().loadAll();
                    if (itemColumns!=null&&!itemColumns.isEmpty()){
                        subscriber.onNext(itemColumns);

                        LogUtil.d("拿本地数据");
                    }else {
                        loadColumnListFromNet(delegate);
                    }

                }
            })
                    .compose(TransformUtils.<List<ItemColumn>>defaultSchedulers())
                    .subscribe(new Action1<List<ItemColumn>>() {
                        @Override
                        public void call(List<ItemColumn> itemColumns) {
                            delegate.getDataSuccess(itemColumns);
        //                     loadColumnListFromNet(delegate);

                        }
                    });
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
                    final List<ItemColumn> columnList = mGson.fromJson(jsonObject.toString(), token);
                    delegate.getDataSuccess(columnList);
                    ThreadManager.getLongPool().execute(new Runnable() {
                        @Override
                        public void run() {
                            APP.getWriteableDaoSession().getItemColumnDao().insertOrReplaceInTx(columnList);
                        }
                    });
                    OrmKeys.updateTime(OrmKeys.columnKey);

                } catch (Exception e) {
                    LogUtil.e("数据有误" + e);
                }
            }
        };
        return RetrofitManager.getInstance()
                .getColumnAPIService().getColumnList("json/categories/list.json")
                .compose(TransformUtils.<JSONArray>defaultSchedulers())
                .subscribe(mSubscriber);
    }
}
