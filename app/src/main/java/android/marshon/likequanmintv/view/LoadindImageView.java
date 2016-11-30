package android.marshon.likequanmintv.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.marshon.likequanmintv.R;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by ITMarshon.Chen on 2016/11/29.
 * emal:itmarshon@163.com
 * desc:
 */

public class LoadindImageView extends FrameLayout {
    public LoadindImageView(Context context) {
        this(context,null,0);
    }

    public LoadindImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadindImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ImageView target= (ImageView) findViewById(R.id.target);
//        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.loading_anim);
//        animation.setRepeatCount(-1);
//        target.startAnimation(animation);

        target.setImageResource(R.drawable.loading_anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) target.getDrawable();
        animationDrawable.start();

    }
}
