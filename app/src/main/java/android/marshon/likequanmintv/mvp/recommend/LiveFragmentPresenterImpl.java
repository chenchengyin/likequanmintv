package android.marshon.likequanmintv.mvp.recommend;

import android.marshon.likequanmintv.librarys.mvpbase.BasePresenterImpl;
import android.marshon.likequanmintv.mvp.recommend.interactor.LiveFragmentInteractor;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/11/22.
 */

public class LiveFragmentPresenterImpl extends BasePresenterImpl<LiveFragmentView> implements LiveFragmentPresenter {

    @Inject
    public LiveFragmentPresenterImpl(){

    }

    @Inject
    LiveFragmentInteractor liveFragmentInteractor;

    @Override
    public void onCreate() {

        loadLiveList();
    }


    @Override
    public void loadLiveList() {
        liveFragmentInteractor.loadLiveList();
    }
}
