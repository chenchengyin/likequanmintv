package android.marshon.likequanmintv.adapter;

import android.app.Activity;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.Banner;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by It.Marshon on 2016/11/25 0025 18:35
 */


public  class BannerPagerAadapter extends PagerAdapter {

    private Activity mContext;
    private List<Banner> banners;

    public BannerPagerAadapter(Activity mContext, List<Banner> banners){
        this.banners = banners;
        this.mContext=mContext;

    }

    @Override
    public int getCount() {
        if (banners!=null&& !banners.isEmpty()){
            return banners.size();
        }

        return 0;
    }

    public Banner getItem(int position) {
        return banners.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        ImageView webImageView = (ImageView) View.inflate(mContext, R.layout.item_banner, null);
        ImageView webImageView = new ImageView(mContext);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT);
//            if (banners!=null&& !banners.isEmpty()){
//                webImageView.setImageWithURL(getActivity(), banners.get(position), R.drawable.banner);
//                webImageView.setImageResource(R.drawable.banner);
//                Picasso.with(mActivity).load()
//            }else if(bannerRes!=null&& !bannerRes.isEmpty()){
//            }
        webImageView.setImageResource(R.drawable.logo);
        Banner banner = banners.get(position);
        Glide.with(mContext)
                .load(banner.thumb)
                .fitCenter()
                .into(webImageView);
        webImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(webImageView,layoutParams);
        return webImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setLiveCategoryList(List<Banner> liveCategoryList) {
        this.banners.clear();
        this.banners.addAll(liveCategoryList);
    }
}