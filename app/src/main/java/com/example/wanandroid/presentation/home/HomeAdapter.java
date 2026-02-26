package com.example.wanandroid.presentation.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.wanandroid.R;
import com.example.wanandroid.domain.model.Article;
import com.example.wanandroid.domain.model.Banner;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_BANNER = 0;
    private static final int TYPE_ARTICLE = 1;

    private List<Banner> banners = new ArrayList<>();
    private List<Article> articles = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onArticleClick(Article article);
        void onBannerClick(Banner banner);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setBanners(List<Banner> banners) {
        if (banners != null) {
            this.banners = banners;
        } else {
            this.banners = new ArrayList<>();
        }
        notifyItemChanged(0);//通知RecyclerView刷新banner，RecyclerView接口的方法
    }

    public void submitList(List<Article> articles) {
        if (articles != null) {
            this.articles = articles;
        } else {
            this.articles = new ArrayList<>();
        }
        notifyDataSetChanged();//通知RecyclerView刷新数据，因为文章列表的位置会变化，所以用DataSetChanged刷新整个列表
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else {
            return TYPE_ARTICLE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_BANNER) {
            View view = inflater.inflate(R.layout.item_home_banner, parent, false);
            return new BannerViewHolder(view, listener);
        } else {
            View view = inflater.inflate(R.layout.item_article, parent, false);
            return new ArticleViewHolder(view, listener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            BannerViewHolder bannerHolder = (BannerViewHolder) holder;
            bannerHolder.bind(banners);
        } else if (holder instanceof ArticleViewHolder) {
            ArticleViewHolder articleHolder = (ArticleViewHolder) holder;
            articleHolder.bind(articles.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        int bannerCount = 1;
        int articleCount = articles.size();
        return bannerCount + articleCount;
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        ViewPager2 viewPager;
        OnItemClickListener clickListener;

        BannerViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            this.clickListener = listener;
            viewPager = itemView.findViewById(R.id.banner_view_pager);
        }

        public ViewPager2 getViewPager() {
            return viewPager;
        }


        void bind(List<Banner> banners) {
            if (banners == null || banners.isEmpty()) {
                return;
            }
            BannerPagerAdapter adapter = new BannerPagerAdapter(banners, clickListener);
            viewPager.setAdapter(adapter);
        }
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvDate;
        TextView tvTop;
        OnItemClickListener clickListener;

        ArticleViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            this.clickListener = listener;
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTop = itemView.findViewById(R.id.tv_top);
        }

        void bind(Article article) {
            tvTitle.setText(article.getTitle());
            tvAuthor.setText(article.getAuthor());
            tvDate.setText(article.getNiceDate());

            if (article.isTop()) {
                tvTop.setVisibility(View.VISIBLE);
            } else {
                tvTop.setVisibility(View.GONE);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onArticleClick(article);
                    }
                }
            });
        }
    }

    static class BannerPagerAdapter extends RecyclerView.Adapter<BannerPagerAdapter.BannerItemHolder> {

        private OnItemClickListener clickListener;
        private List<Banner> bannerList;

        BannerPagerAdapter(List<Banner> bannerList, OnItemClickListener listener) {
            if (bannerList != null) {
                this.bannerList = bannerList;
            } else {
                this.bannerList = new ArrayList<>();
            }
            this.clickListener = listener;
        }

        @NonNull
        @Override
        public BannerItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.item_banner_image, parent, false);
            return new BannerItemHolder(view, clickListener);
        }

        @Override
        public void onBindViewHolder(@NonNull BannerItemHolder holder, int position) {
            Banner banner = bannerList.get(position);
            holder.bind(banner);
        }

        @Override
        public int getItemCount() {
            return bannerList.size();
        }

        static class BannerItemHolder extends RecyclerView.ViewHolder {
            ImageView ivBanner;
            OnItemClickListener clickListener;

            BannerItemHolder(@NonNull View itemView, OnItemClickListener listener) {
                super(itemView);
                this.clickListener = listener;
                ivBanner = itemView.findViewById(R.id.iv_banner);
            }

            void bind(Banner banner) {
                Glide.with(itemView.getContext())
                        .load(banner.getImagePath())
                        .into(ivBanner);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (clickListener != null) {
                            clickListener.onBannerClick(banner);
                        }
                    }
                });
            }
        }
    }
}