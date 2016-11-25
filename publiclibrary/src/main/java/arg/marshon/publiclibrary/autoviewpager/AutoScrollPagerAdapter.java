package arg.marshon.publiclibrary.autoviewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class AutoScrollPagerAdapter extends PagerAdapter {

    private PagerAdapter wrappedAdapter;

    public AutoScrollPagerAdapter(PagerAdapter wrapped) {
        wrappedAdapter = wrapped;
    }

    @Override
    public int getCount() {
        if (wrappedAdapter == null) {
            return 0;
        } else if (wrappedAdapter.getCount() > 1) {
            return wrappedAdapter.getCount() + 2;
        } else {
            return wrappedAdapter.getCount();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position == 0) {
            return wrappedAdapter.instantiateItem(container, wrappedAdapter.getCount() - 1);
        } else if (position == wrappedAdapter.getCount() + 1) {
            return wrappedAdapter.instantiateItem(container, 0);
        } else {
            return wrappedAdapter.instantiateItem(container, position - 1);
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        wrappedAdapter.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return wrappedAdapter.isViewFromObject(view, o);
    }



}
