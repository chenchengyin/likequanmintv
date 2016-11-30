package android.marshon.likequanmintv.mvp.live;

import android.marshon.likequanmintv.librarys.http.delagate.IGetDataDelegate;
import android.marshon.likequanmintv.librarys.mvpbase.BasePresenterImpl;
import android.marshon.likequanmintv.mvp.live.interactor.LiveInteractor;

import org.json.JSONObject;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by It.Marshon on 2016/11/28 0028 15:58
 */

public class LivePlayerPresenterImpl  extends BasePresenterImpl<LivePlayerView> implements LivePlayerPresenter {

    @Inject
    LiveInteractor mLiveInteractor;

    @Inject
    public LivePlayerPresenterImpl(){

    }

    @Override
    public void enterRoom(String uid) {
        Subscription subscription = mLiveInteractor.enterRoom(new IGetDataDelegate<JSONObject>() {
            @Override
            public void getDataSuccess(JSONObject roomJson) {
                mPresenterView.onRoomData(roomJson);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.showToast(errmsg);

            }
        }, uid);
        mSubscriptions.add(subscription);
    }
}
