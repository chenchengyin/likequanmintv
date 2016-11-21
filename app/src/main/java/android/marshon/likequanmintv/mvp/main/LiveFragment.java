package android.marshon.likequanmintv.mvp.main;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.librarys.mvpbase.BaseMvpFragment;
import android.marshon.likequanmintv.mvp.recommend.LiveFragmentPresenter;
import android.marshon.likequanmintv.mvp.recommend.LiveFragmentPresenterImpl;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/11/21.
 */

public class LiveFragment extends BaseMvpFragment<LiveFragmentPresenter> {


    public static LiveFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LiveFragment fragment = new LiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    LiveFragmentPresenterImpl liveFragmentPresenter;

    @Override
    protected LiveFragmentPresenterImpl initInjector() {
         mFragmentComponent.inject(this); //初始化
        return liveFragmentPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_recommend;
    }

    @Override
    protected void initView(View rootView) {
        TextView tvName= (TextView) rootView.findViewById(R.id.tvName);
        tvName.setText("直播");
        liveFragmentPresenter.onCreate();
    }
}
