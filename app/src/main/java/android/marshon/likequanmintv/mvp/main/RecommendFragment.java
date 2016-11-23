package android.marshon.likequanmintv.mvp.main;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.base.BaseFragment;
import android.marshon.likequanmintv.librarys.mvpbase.BaseMvpFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/11/21.
 */

public class RecommendFragment extends BaseFragment {


    public static RecommendFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.frg_recommend;
    }

    @Override
    protected void initView(View rootView) {


    }
}
