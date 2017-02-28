package android.marshon.likequanmintv.mvp.column;

import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.ItemColumn;
import android.marshon.likequanmintv.librarys.base.BaseActivity;
import android.marshon.likequanmintv.mvp.recommend.ui.BaseLiveWraperFragment;
import android.marshon.likequanmintv.mvp.recommend.ui.LoveLiveListFragment;
import android.marshon.likequanmintv.utils.SystemBarUtils;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by It.Marshon on 2016/11/30 0030 14:22
 */

public class ColumnListActivity extends BaseActivity {

    private TextView title;
    private FrameLayout content;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_columnlist);
        SystemBarUtils.setStatusBarTranslate(this,R.color.colorPrimary);
        initView();
    }



    private void initView() {
        title=(TextView)findViewById(R.id.title);
        ivBack =(ImageView)findViewById(R.id.imgBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        content=(FrameLayout)findViewById(R.id.content);

        ItemColumn itemColumn= (ItemColumn) getIntent().getSerializableExtra("itemColumn");
        title.setText(""+itemColumn.name);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ts = fm.beginTransaction();
        Bundle bundle=new Bundle();
        String mUrl="json/categories/"+itemColumn.slug+"/list.json";
        bundle.putString("url",mUrl);
        bundle.putString("tag",itemColumn.name);
        if (itemColumn.slug.equals("love")){
            ts.replace(R.id.content, LoveLiveListFragment.newInstance(bundle));
        }else {
            ts.replace(R.id.content, BaseLiveWraperFragment.newInstance(bundle));
        }
        ts.commit();
    }
}
