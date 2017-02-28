package android.marshon.likequanmintv.listener;

import android.marshon.likequanmintv.librarys.utils.LogUtil;
import android.marshon.likequanmintv.librarys.utils.screen.ScreenUtils;
import android.support.v7.widget.RecyclerView;

/**
 * Created by It.Marshon on 2016/11/24 0024 15:27
 */

public class UpDownRvScrollListener extends RecyclerView.OnScrollListener {

    private UpdownScroll mUpdownScroll;
    private int mDy;

    public UpDownRvScrollListener(UpdownScroll updownScroll) {
        mUpdownScroll = updownScroll;
    }



    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
//        int top = recyclerView.getChildAt(0).getTop();
//        LogUtil.e("top"+top);
        mDy += dy;
        LogUtil.e("UpDownRvScrollListener"+mDy);
        if (mDy> ScreenUtils.dp2px(55)){
            if (mUpdownScroll!=null){
                mUpdownScroll.onShouldDown(true);
            }
        }else {
            if (mUpdownScroll!=null){
                mUpdownScroll.onShouldDown(false);
            }
        }


    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    public interface UpdownScroll{
        void onShouldDown(boolean shouldDown);
    }

}
