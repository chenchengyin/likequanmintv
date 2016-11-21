package android.marshon.likequanmintv.mvp.recommend;

import android.support.annotation.NonNull;

import com.zy.soliloquize.chat.bean.Chapter;
import com.zy.soliloquize.chat.http.IGetObjectDelegate;
import com.zy.soliloquize.chat.http.rxjava.BasePresenterImpl;
import com.zy.soliloquize.chat.mvp.chat.presenter.interactor.MainMenuInteractorImpl;
import com.zy.soliloquize.chat.mvp.chat.view.MainMenuView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by It.Marshon on 2016/11/21 0021 11:25
 */

public class MainMenuPresenterImpl extends BasePresenterImpl<MainMenuView> implements MainMenuPresenter {

    private MainMenuInteractorImpl mainMenuInteractor;
    private MainMenuView mainMenuView;

    @Inject
    public MainMenuPresenterImpl(MainMenuInteractorImpl mainMenuInteractor) {
        this.mainMenuInteractor=mainMenuInteractor;
    }



    @Override
    public void loadChapters() {
        Subscription subscription = mainMenuInteractor.loadChapters(new IGetObjectDelegate<List<Chapter>>(){

            @Override
            public void onGetDataSuccess(List<Chapter> chapters) {
                mainMenuView.onChapterSuccess(chapters);

            }

            @Override
            public void onGetDataError(String errmsg) {

            }
        });
        if (subscription!=null){
            mSubscriptions.add(subscription);
        }
    }



    @Override
    public void onCreate() {
        loadChapters();
    }

    @Override
    public void attachView(@NonNull MainMenuView baseView) {
        this.mainMenuView=baseView;
    }


}
