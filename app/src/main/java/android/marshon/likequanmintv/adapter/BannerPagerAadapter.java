package android.marshon.likequanmintv.adapter;

import android.app.Activity;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.Banner;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by It.Marshon on 2016/11/25 0025 18:35
 */


class BannerPagerAadapter extends PagerAdapter {

    private List<Banner> banners;

    public BannerPagerAadapter(Activity mContext, List<Banner> banners){
        this.banners = banners;

    }


    @Override
    public int getCount() {
        if (banners!=null&& !banners.isEmpty()){
            return banners.size();
        }else if(bannerRes!=null&& !bannerRes.isEmpty()){
            return bannerRes.size();
        }
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView webImageView = (ImageView) View.inflate(getActivity(), R.layout.item_banner, null);
//            if (banners!=null&& !banners.isEmpty()){
//                webImageView.setImageWithURL(getActivity(), banners.get(position), R.drawable.banner);
//                webImageView.setImageResource(R.drawable.banner);
//                Picasso.with(mActivity).load()
//            }else if(bannerRes!=null&& !bannerRes.isEmpty()){
//            }
        webImageView.setImageResource(R.drawable.banner);
        webImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(webImageView);
        return webImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}