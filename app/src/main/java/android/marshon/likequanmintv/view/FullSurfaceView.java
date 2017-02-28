package android.marshon.likequanmintv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by ITMarshon.Chen on 2016/11/27.
 * emal:itmarshon@163.com
 * desc:
 */

public class FullSurfaceView extends SurfaceView{
    public FullSurfaceView(Context context) {
        super(context);
    }

    public FullSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMeasureSize = getDefaultSize(0, widthMeasureSpec);
        int heightMeasureSize = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSize,heightMeasureSize);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

}
