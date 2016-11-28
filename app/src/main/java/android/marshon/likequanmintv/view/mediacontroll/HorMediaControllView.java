package android.marshon.likequanmintv.view.mediacontroll;

import android.content.Context;
import android.marshon.likequanmintv.R;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by It.Marshon on 2016/11/28 0028 14:14
 */

public class HorMediaControllView extends FrameLayout {
    public HorMediaControllView(Context context) {
        this(context, null,0);
    }

    public HorMediaControllView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HorMediaControllView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.widget_hor_controller, this);
    }


    public void  onCreate(){
        setVisibility(VISIBLE);
    }

    public void  onDestroy(){
        setVisibility(GONE);

    }

}
