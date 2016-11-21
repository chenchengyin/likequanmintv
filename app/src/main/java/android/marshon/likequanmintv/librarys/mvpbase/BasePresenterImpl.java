package android.marshon.likequanmintv.librarys.mvpbase;

import android.support.annotation.NonNull;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by It.Marshon on 2016/11/21 0021 11:40
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    //presenter的subscription管理器
    protected CompositeSubscription mSubscriptions=new CompositeSubscription();
    @Override
    public void onCreate() {

    }


    @Override
    public void attachView(@NonNull T baseView) {

    }

    @Override
    public void onDestroy() {
        if (!mSubscriptions.isUnsubscribed()){
            mSubscriptions.unsubscribe();
        }
        mSubscriptions=null;

    }


}
