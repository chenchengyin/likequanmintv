package android.marshon.likequanmintv.view.lazyvp;

import android.content.Context;
import android.content.res.TypedArray;
import android.marshon.likequanmintv.R;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * ViewPager that add items lazily in the two following situation:
 * <ul>
 *     <li>its adapter inherits from {@link LazyViewPagerAdapter}</li>
 *     <li>its adapter inherits from {@link LazyFragmentPagerAdapter} and Fragment implements {@link LazyFragmentPagerAdapter.Laziable} </li>
 * </ul>
 */
public class LazyViewPager extends ViewPager {

	private static final float DEFAULT_OFFSET = 0.5f;

	private LazyPagerAdapter mLazyPagerAdapter;
	private float mInitLazyItemOffset = DEFAULT_OFFSET;

	public LazyViewPager(Context context) {
		super(context);
	}

	public LazyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LazyViewPager);
		setInitLazyItemOffset(a.getFloat(R.styleable.LazyViewPager_init_lazy_item_offset, DEFAULT_OFFSET));
		a.recycle();
	}

    /**
     * change the initLazyItemOffset
     * @param initLazyItemOffset set mInitLazyItemOffset if {@code 0 < initLazyItemOffset <= 1}
     */
	public void setInitLazyItemOffset(float initLazyItemOffset) {
		if (initLazyItemOffset > 0 && initLazyItemOffset <= 1) {
		    mInitLazyItemOffset = initLazyItemOffset;
        }
	}

	@Override
	public void setAdapter(PagerAdapter adapter) {
		super.setAdapter(adapter);
        mLazyPagerAdapter = adapter != null && adapter instanceof LazyPagerAdapter ? (LazyPagerAdapter) adapter : null;
	}

	@Override
	protected void onPageScrolled(int position, float offset, int offsetPixels) {
		if (mLazyPagerAdapter != null) {
			if (getCurrentItem() == position) {
				int lazyPosition = position + 1;
				if (offset >= mInitLazyItemOffset && mLazyPagerAdapter.isLazyItem(lazyPosition)) {
                    mLazyPagerAdapter.startUpdate(this);
                    mLazyPagerAdapter.addLazyItem(this, lazyPosition);
                    mLazyPagerAdapter.finishUpdate(this);
				}
			} else if (getCurrentItem() > position) {
				int lazyPosition = position;
				if (1 - offset >= mInitLazyItemOffset && mLazyPagerAdapter.isLazyItem(lazyPosition)) {
                    mLazyPagerAdapter.startUpdate(this);
                    mLazyPagerAdapter.addLazyItem(this, lazyPosition);
                    mLazyPagerAdapter.finishUpdate(this);
				}
			}
		}
		super.onPageScrolled(position, offset, offsetPixels);
	}
}
