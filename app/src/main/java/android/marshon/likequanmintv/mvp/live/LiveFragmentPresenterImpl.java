package android.marshon.likequanmintv.mvp.live;

import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.librarys.http.delagate.IGetDataDelegate;
import android.marshon.likequanmintv.librarys.mvpbase.BasePresenterImpl;
import android.marshon.likequanmintv.mvp.live.interactor.LiveFragmentInteractor;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/11/22.
 */

public class LiveFragmentPresenterImpl extends BasePresenterImpl<LiveFragmentView>
        implements LiveFragmentPresenter {

    @Inject
    public LiveFragmentPresenterImpl(){

    }

    @Inject
    LiveFragmentInteractor liveFragmentInteractor;

    @Override
    public void onCreate() {
        loadPlayList();
    }


    @Override
    public void loadPlayList() {
        liveFragmentInteractor.loadPlayList(new IGetDataDelegate<List<PlayBean>>(){

            @Override
            public void getDataSuccess(List<PlayBean> playBeanListHolderList) {
                mPresenterView.onPlayBeanList(playBeanListHolderList);

            }

            @Override
            public void getDataError(String errmsg) {

            }
        });
    }

    @Override
    public void addMorePlayList() {

    }
}
