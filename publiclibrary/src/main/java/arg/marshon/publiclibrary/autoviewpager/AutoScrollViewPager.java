package arg.marshon.publiclibrary.autoviewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

// TODO add attr support?
public class AutoScrollViewPager extends ViewPager implements ViewPager.PageTransformer {

    public interface OnPageClickListener {
        void onPageClick(AutoScrollViewPager pager, int position);
    }

    private static final int MSG_AUTO_SCROLL = 0;
    private static final int DEFAULT_INTERNAL_IM_MILLIS = 2000;

    private PagerAdapter wrappedPagerAdapter;
    private PagerAdapter wrapperPagerAdapter;
    private InnerOnPageChangeListener listener;
    private AutoScrollFactorScroller scroller;
    private H handler;

    private boolean autoScroll = false;
    private int intervalInMillis;

    private float mInitialMotionX;
    private float mInitialMotionY;
    private float mLastMotionX;
    private float mLastMotionY;
    private int touchSlop;
    private OnPageClickListener onPageClickListener;

    private static class H extends Handler {
        private AutoScrollViewPager autoScrollViewPager;
        private WeakReference<AutoScrollViewPager> viewHolder;
        public H(AutoScrollViewPager autoScrollViewPager){
            this.autoScrollViewPager = autoScrollViewPager;
            viewHolder = new WeakReference<AutoScrollViewPager>(autoScrollViewPager);
        }

        @Override
        public void handleMessage(Message msg) {
            if (viewHolder.get()!=null){
                switch (msg.what) {
                    case MSG_AUTO_SCROLL:
                        autoScrollViewPager.setCurrentItem(autoScrollViewPager.getCurrentItem() + 1);
                        sendEmptyMessageDelayed(MSG_AUTO_SCROLL, autoScrollViewPager.intervalInMillis);
                        break;
                    default:
                        super.handleMessage(msg);
                        break;
                }
            }

        }
    }

    public AutoScrollViewPager(Context context) {
        super(context);
        init();
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        listener = new InnerOnPageChangeListener();
        super.setOnPageChangeListener(listener);

        handler = new H(this);
        touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

        setPageTransformer(true,this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeMessages(MSG_AUTO_SCROLL);
    }

    public void startAutoScroll() {
        startAutoScroll(intervalInMillis != 0 ? intervalInMillis : DEFAULT_INTERNAL_IM_MILLIS);
    }

    public void startAutoScroll(int intervalInMillis) {
        // Only post scroll message when necessary.
        if (getCount() > 1) {
            this.intervalInMillis = intervalInMillis;
            autoScroll = true;
            handler.removeMessages(MSG_AUTO_SCROLL);
            handler.sendEmptyMessageDelayed(MSG_AUTO_SCROLL, intervalInMillis);
        }
    }

    public void stopAutoScroll() {
        autoScroll = false;
        handler.removeMessages(MSG_AUTO_SCROLL);
    }

    public void setInterval(int intervalInMillis) {
        this.intervalInMillis = intervalInMillis;
    }

    public void setScrollFactgor(double factor) {
        setScrollerIfNeeded();
        scroller.setFactor(factor);
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.listener.setOnPageChangeListener(listener);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        wrappedPagerAdapter = adapter;
        wrapperPagerAdapter = (wrappedPagerAdapter == null) ? null : new AutoScrollPagerAdapter(adapter);
        super.setAdapter(wrapperPagerAdapter);

        if (adapter != null && adapter.getCount() != 0) {
            post(new Runnable() {
                @Override
                public void run() {
                    setCurrentItem(0, false);
                }
            });
        }
    }




    @Override
    public PagerAdapter getAdapter() {
        // In order to be compatible with ViewPagerIndicator
        return wrappedPagerAdapter;
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item + 1);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item + 1, smoothScroll);
    }

    @Override
    public int getCurrentItem() {
        int curr = super.getCurrentItem();
        if (wrappedPagerAdapter != null && wrappedPagerAdapter.getCount() > 1) {
            if (curr == 0) {
                curr = wrappedPagerAdapter.getCount() - 1;
            } else if (curr == wrapperPagerAdapter.getCount() - 1) {
                curr = 0;
            } else {
                curr = curr - 1;
            }
        }
        return curr;
    }

    public OnPageClickListener getOnPageClickListener() {
        return onPageClickListener;
    }

    public void setOnPageClickListener(OnPageClickListener onPageClickListener) {
        this.onPageClickListener = onPageClickListener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        getParent().getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (MotionEventCompat.getActionMasked(ev)) {
            case MotionEvent.ACTION_DOWN:
                if (getCurrentItemOfWrapper() + 1 == getCountOfWrapper()) {
                    setCurrentItem(0, false);
                } else if (getCurrentItemOfWrapper() == 0) {
                    setCurrentItem(getCount() - 1, false);
                }
                handler.removeMessages(MSG_AUTO_SCROLL);
                mInitialMotionX = ev.getX();
                mInitialMotionY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mLastMotionX = ev.getX();
                mLastMotionY = ev.getY();
                if ((int) Math.abs(mLastMotionX - mInitialMotionX) > touchSlop || (int) Math.abs(mLastMotionY - mInitialMotionY) > touchSlop) {
                    mInitialMotionX = 0.0f;
                    mInitialMotionY = 0.0f;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (autoScroll) {
                    startAutoScroll();
                }

                // Manually swipe not affected by scroll factor.
                if (scroller != null) {
                    final double lastFactor = scroller.getFactor();
                    scroller.setFactor(1);
                    post(new Runnable() {
                        @Override
                        public void run() {
                            scroller.setFactor(lastFactor);
                        }
                    });
                }

                mLastMotionX = ev.getX();
                mLastMotionY = ev.getY();
                if ((int) mInitialMotionX != 0 && (int) mInitialMotionY != 0) {
                    if ((int) Math.abs(mLastMotionX - mInitialMotionX) < touchSlop
                            && (int) Math.abs(mLastMotionY - mInitialMotionY) < touchSlop) {
                        mInitialMotionX = 0.0f;
                        mInitialMotionY = 0.0f;
                        mLastMotionX = 0.0f;
                        mLastMotionY = 0.0f;
                        if (onPageClickListener != null) {
                            onPageClickListener.onPageClick(this, getCurrentItem());
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * Get current item of the outer wrapper adapter.
     */
    private int getCurrentItemOfWrapper() {
        return super.getCurrentItem();
    }

    /**
     * Get item count of the outer wrapper adapter.
     */
    private int getCountOfWrapper() {
        if (wrapperPagerAdapter != null) {
            return wrapperPagerAdapter.getCount();
        }
        return 0;
    }

    /**
     * Get item count of the adapter which is set by user
     */
    private int getCount() {
        if (wrappedPagerAdapter != null) {
            return wrappedPagerAdapter.getCount();
        }
        return 0;
    }

    private void setScrollerIfNeeded() {
        if (scroller != null) {
            return;
        }
        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            Field interpolatorField = ViewPager.class.getDeclaredField("sInterpolator");
            interpolatorField.setAccessible(true);
            scroller = new AutoScrollFactorScroller(getContext(), (Interpolator) interpolatorField.get(null));
            scrollerField.set(this, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class InnerOnPageChangeListener implements OnPageChangeListener {
        private OnPageChangeListener listener;
        private int lastSelectedPage = -1;

        public InnerOnPageChangeListener() {
        }

        public InnerOnPageChangeListener(OnPageChangeListener listener) {
            setOnPageChangeListener(listener);
        }

        public void setOnPageChangeListener(OnPageChangeListener listener) {
            this.listener = listener;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == SCROLL_STATE_IDLE && getCount() > 1) {
                if (getCurrentItemOfWrapper() == 0) {
                    // scroll to the last page
                    setCurrentItem(getCount() - 1, false);
                } else if (getCurrentItemOfWrapper() == getCountOfWrapper() - 1) {
                    // scroll to the first page
                    setCurrentItem(0, false);
                }
            }
            if (listener != null) {
                listener.onPageScrollStateChanged(state);
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (listener != null && position > 0 && position < getCount()) {
                listener.onPageScrolled(position - 1, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(final int position) {
//            if (listener != null && position != 0 && position < wrappedPagerAdapter.getCount() + 1) {
            if (listener != null) {
                final int pos;
                // Fix position
                if (position == 0) {
                    pos = getCount() - 1;
                } else if (position == getCountOfWrapper() - 1) {
                    pos = 0;
                } else {
                    pos = position - 1;
                }

                // Comment this, onPageSelected will be triggered twice for position 0 and getCount -1.
                // Uncomment this, PageIndicator will have trouble.
//                if (lastSelectedPage != pos) {
                lastSelectedPage = pos;
                // Post a Runnable in order to be compatible with ViewPagerIndicator because
                // onPageSelected is invoked before onPageScrollStateChanged.
                AutoScrollViewPager.this.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onPageSelected(pos);
                    }
                });
//                }
            }
        }
    }

    private static final float MIN_SCALE = 0.75f;

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.setAlpha(1 - position);

            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }

}
