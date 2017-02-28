package android.marshon.likequanmintv.mvp.recommend.ui;

import android.content.Intent;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.bean.Banner;
import android.marshon.likequanmintv.bean.LiveCategory;
import android.marshon.likequanmintv.bean.PlayBean;
import android.marshon.likequanmintv.controller.BannerHeadViewController;
import android.marshon.likequanmintv.librarys.mvpbase.BaseMvpFragment;
import android.marshon.likequanmintv.mvp.live.ui.CommonLiveUI;
import android.marshon.likequanmintv.mvp.live.ui.VerFullLiveUI;
import android.marshon.likequanmintv.mvp.recommend.RecommendRecommendPresenter;
import android.marshon.likequanmintv.mvp.recommend.RecommendRecommendPresenterImpl;
import android.marshon.likequanmintv.mvp.recommend.RecommendRecommendView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.glide.glide.GlideCircleTransform;
import com.zhy.adapter.recyclerview.glide.glide.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ITMarshon.Chen on 2016/11/24.
 * emal:itmarshon@163.com
 * desc:
 */

public class RecommendRecommendFragment extends BaseMvpFragment<RecommendRecommendPresenter> implements RecommendRecommendView{

    private ListView listview;
    private List<LiveCategory> liveCategoryList=new ArrayList<>();
    private BannerHeadViewController bannerHeadViewHolder;

    @Inject
    RecommendRecommendPresenterImpl recommendRecommendPresenter;
    private RecommendCategoryAdapter adapter;

    public static RecommendRecommendFragment newInstance() {
        Bundle args = new Bundle();
        RecommendRecommendFragment fragment = new RecommendRecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecommendRecommendPresenter initInjector() {
        mFragmentComponent.inject(this);
        return recommendRecommendPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_recommend_recommend;
    }

    @Override
    protected void initView(View rootView) {
        listview=(ListView)find(R.id.listview);
        //initHead
        bannerHeadViewHolder=new BannerHeadViewController(this);

        adapter=new RecommendCategoryAdapter(liveCategoryList);
        listview.setAdapter(adapter);
    }


    @Override
    public void onGetBanners(List<Banner> bannerList) {
        bannerHeadViewHolder.setBannerData(bannerList);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void initData() {
        mPresenter.getRecommendCategories();
        mPresenter.getAppStartInfo();
    }

    @Override
    public void onGetRecommendCategories(List<LiveCategory> liveCategoryLists) {
        adapter.refreshData(liveCategoryLists);

    }

    public class RecommendCategoryAdapter extends BaseAdapter{

        private static final int VIEWTYPE_HEAD = 1;
        private  List<LiveCategory> liveCategoryList;

        public RecommendCategoryAdapter(List<LiveCategory> liveCategoryList){
            this.liveCategoryList = liveCategoryList;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (position==0){
                return VIEWTYPE_HEAD;
            }
            return super.getItemViewType(position);
        }

        @Override
        public int getCount() {
            return liveCategoryList==null?0:liveCategoryList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            int itemViewType = getItemViewType(position);

            if (itemViewType==VIEWTYPE_HEAD){
                //头部
                if (convertView == null) { //加载数据
//                    bannerHeadViewHolder.getBanner();
                }
                return bannerHeadViewHolder.headView;
            }else {
                //普通view
                RecommendCategoryViewHolder recommendCategoryViewHolder=null;
                if (convertView==null){
                    convertView=View.inflate(mActivity,R.layout.listitem_recommend_categry,null);
                    recommendCategoryViewHolder=new RecommendCategoryViewHolder(convertView);
                    LiveCategory liveCategory = liveCategoryList.get(position - 1);
                    recommendCategoryViewHolder.setData(liveCategory);
                    convertView.setTag(recommendCategoryViewHolder);
                }else {
                    recommendCategoryViewHolder= (RecommendCategoryViewHolder) convertView.getTag();
                    LiveCategory liveCategory = liveCategoryList.get(position - 1);
                    recommendCategoryViewHolder.setData(liveCategory);
                }

                return convertView;
            }


        }

        public void refreshData(List<LiveCategory> liveCategoryLists) {
            this.liveCategoryList=liveCategoryLists;
            notifyDataSetChanged();
        }
    }

    class RecommendCategoryViewHolder{

        private TextView goToThisLive;
        private RecyclerView rmGridView;
        private TextView categoryName;

        public RecommendCategoryViewHolder(View itemView){
            categoryName = (TextView) itemView.findViewById(R.id.category_name);
            goToThisLive = (TextView) itemView.findViewById(R.id.goToThisLive);
            rmGridView=(RecyclerView)itemView.findViewById(R.id.rmGridView);
        }

        public void setData(final LiveCategory liveCategory) {

            categoryName.setText(""+liveCategory.getName());
            goToThisLive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //选中指定tab
                }
            });
            List<PlayBean> rooms = liveCategory.list;
            rmGridView.setLayoutManager(new GridLayoutManager(mActivity,2));
            boolean isLove=false;
            if (liveCategory.getSlug().equals("love")){
                isLove=true;
            }
            rmGridView.setAdapter(new CommonAdapter<PlayBean>(mActivity,isLove?R.layout.listitem_love:R.layout.listitem_recommendcategory_child,rooms) {
                @Override
                protected void convert(ViewHolder holder, final PlayBean room, int position) {
                    if (liveCategory.getSlug().equals("love")){
                        holder.setImageUrl(R.id.thumnails,room.thumb,new GlideRoundTransform(mActivity,5));
                        holder.setText(R.id.tv_viewnum,room.view);
                        holder.setText(R.id.intro,room.title);
                        holder.getItemView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent =new Intent(mActivity, VerFullLiveUI.class);
                                intent.putExtra("playBean",room);
                                mActivity.startActivity(intent);
//                                getActivity().overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
                            }
                        });
                    }else {
                        holder.setImageUrl(R.id.thumnails,room.thumb,new GlideRoundTransform(mContext,5));
                        holder.setText(R.id.title,room.title);
                        holder.setText(R.id.tv_viewnum,room.view);
                        holder.setText(R.id.nickName,room.nick);
                        holder.setImageUrl(R.id.ic_head,room.avatar,new GlideCircleTransform(mContext));
                        holder.getItemView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent =new Intent(mActivity, CommonLiveUI.class);
                                intent.putExtra("playBean",room);
                                mActivity.startActivity(intent);
//                                getActivity().overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
                            }
                        });
                    }

                }
            });

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        bannerHeadViewHolder.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        bannerHeadViewHolder.onPaused();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}