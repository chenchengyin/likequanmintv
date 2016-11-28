package android.marshon.likequanmintv.utils.blur;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.marshon.likequanmintv.utils.blur.internal.Blur;
import android.marshon.likequanmintv.utils.blur.internal.BlurFactor;
import android.marshon.likequanmintv.utils.blur.internal.BlurTask;
import android.marshon.likequanmintv.utils.blur.internal.Helper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



/**
 * Copyright (C) 2015 Wasabeef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
  *     Blurry.with(PlayerActivity.this)
             .animate()
             .radius(10)
             .sampling(8)
             .capture(thumbnail)
             .into(thumbnail);
 *
 */

public class Blurry {

  private static final String TAG = Blurry.class.getSimpleName();

  public static Composer with(Context context) {
    return new Composer(context);
  }

  public static void delete(ViewGroup target) {
    View view = target.findViewWithTag(TAG);
    if (view != null) {
      target.removeView(view);
    }
  }

  public static class Composer {

    private View blurredView;
    private Context context;
    private BlurFactor factor;
    private boolean async;
    private boolean animate;
    private int duration = 300;
    private ImageComposer.ImageComposerListener listener;

    public Composer(Context context) {
      this.context = context;
      blurredView = new View(context);
      blurredView.setTag(TAG);
      factor = new BlurFactor();
    }

    public Composer radius(int radius) {
      factor.radius = radius;
      return this;
    }

    public Composer sampling(int sampling) {
      factor.sampling = sampling;
      return this;
    }

    public Composer color(int color) {
      factor.color = color;
      return this;
    }

    public Composer async() {
      async = true;
      return this;
    }

    public Composer async(ImageComposer.ImageComposerListener listener) {
      async = true;
      this.listener = listener;
      return this;
    }

    public Composer animate() {
      animate = true;
      return this;
    }

    public Composer animate(int duration) {
      animate = true;
      this.duration = duration;
      return this;
    }

    public ImageComposer capture(View capture) {
      return new ImageComposer(context, capture, factor, async, listener);
    }

    public void onto(final ViewGroup target) {
      factor.width = target.getMeasuredWidth();
      factor.height = target.getMeasuredHeight();

      if (async) {
        BlurTask task = new BlurTask(target, factor, new BlurTask.Callback() {
          @Override public void done(BitmapDrawable drawable) {
            addView(target, drawable);
          }
        });
        task.execute();
      } else {
        Drawable drawable = new BitmapDrawable(context.getResources(), Blur.of(target, factor));
        addView(target, drawable);
      }
    }

    private void addView(ViewGroup target, Drawable drawable) {
      Helper.setBackground(blurredView, drawable);
      target.addView(blurredView);

      if (animate) {
        Helper.animate(blurredView, duration);
      }
    }
  }

  public static class ImageComposer {

    private Context context;
    private View capture;
    private BlurFactor factor;
    private boolean async;
    private ImageComposerListener listener;

    public ImageComposer(Context context, View capture, BlurFactor factor, boolean async,
        ImageComposerListener listener) {
      this.context = context;
      this.capture = capture;
      this.factor = factor;
      this.async = async;
      this.listener = listener;
    }

    public void into(final ImageView target) {
      factor.width = capture.getMeasuredWidth();
      factor.height = capture.getMeasuredHeight();

      if (async) {
        BlurTask task = new BlurTask(capture, factor, new BlurTask.Callback() {
          @Override public void done(BitmapDrawable drawable) {
            if (listener == null) {
              target.setImageDrawable(drawable);
            } else {
              listener.onImageReady(drawable);
            }
          }
        });
        task.execute();
      } else {
        Drawable drawable = new BitmapDrawable(context.getResources(), Blur.of(capture, factor));
        target.setImageDrawable(drawable);
      }
    }

    public interface ImageComposerListener {
      void onImageReady(BitmapDrawable drawable);
    }
  }
}
