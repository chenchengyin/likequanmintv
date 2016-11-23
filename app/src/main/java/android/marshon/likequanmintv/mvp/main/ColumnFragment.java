package android.marshon.likequanmintv.mvp.main;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.base.BaseFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/11/21.
 */

public class ColumnFragment extends BaseFragment {


    public static ColumnFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ColumnFragment fragment = new ColumnFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.frg_column;
    }

    @Override
    protected void initView(View rootView) {

        TextView tvName= (TextView) rootView.findViewById(R.id.tvName);
        tvName.setText("栏目");

    }
}
