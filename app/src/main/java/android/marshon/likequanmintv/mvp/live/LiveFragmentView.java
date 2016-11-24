package android.marshon.likequanmintv.mvp.live;

import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.librarys.mvpbase.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public interface LiveFragmentView extends BaseView {

    void onPlayBeanList(List<PlayBean> playBeanListHolderList);
}
