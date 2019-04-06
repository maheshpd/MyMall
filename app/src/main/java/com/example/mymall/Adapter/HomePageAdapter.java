package com.example.mymall.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mymall.Model.GridProductScrollModel;
import com.example.mymall.Model.HomePageModel;
import com.example.mymall.Model.HorizontalProductScrollModel;
import com.example.mymall.Model.SliderModel;
import com.example.mymall.R;
import com.example.mymall.activity.ProductDetailsActivity;
import com.example.mymall.activity.ViewAllActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {
    private List<HomePageModel> homePageModelList;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()) {
            case 0:
                return HomePageModel.BANNER_SLIDER;
            case 1:
                return HomePageModel.STRIP_AD_BANNER;
            case 2:
                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;
            case 3:
                return HomePageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case HomePageModel.BANNER_SLIDER:
                View bannersliderview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sliding_ad_layout, viewGroup, false);
                return new BannerSliderViewHolder(bannersliderview);
            case HomePageModel.STRIP_AD_BANNER:
                View stripadview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.strip_ad_layout, viewGroup, false);
                return new StripAdBannerViewHolder(stripadview);
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View horizontalProductView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_layout, viewGroup, false);
                return new HorizontalProductViewholder(horizontalProductView);
            case HomePageModel.GRID_PRODUCT_VIEW:
                View gridProductView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_product_layout, viewGroup, false);
                return new GridProductViewHolder(gridProductView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (homePageModelList.get(position).getType()) {
            case HomePageModel.BANNER_SLIDER:
                List<SliderModel> sliderModelList = homePageModelList.get(position).getSliderModelList();
                ((BannerSliderViewHolder) viewHolder).setBannerSliderViewPager(sliderModelList);
                break;
            case HomePageModel.STRIP_AD_BANNER:
                String resource = homePageModelList.get(position).getResource();
                String color = homePageModelList.get(position).getBackgroundColor();
                ((StripAdBannerViewHolder) viewHolder).setStripAd(resource, color);
                break;
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String horizontalLayouttitle = homePageModelList.get(position).getTitle();
                List<HorizontalProductScrollModel> horizontalProductScrollModelList = homePageModelList.get(position).getHorizontalProductScrollModelList();
                ((HorizontalProductViewholder) viewHolder).setHorizontalProductLayout(horizontalProductScrollModelList, horizontalLayouttitle);
                break;
            case HomePageModel.GRID_PRODUCT_VIEW:
                String GridLayouttitle = homePageModelList.get(position).getTitle();
                List<HorizontalProductScrollModel> gridProductModelList = homePageModelList.get(position).getHorizontalProductScrollModelList();
                ((GridProductViewHolder) viewHolder).setGridProductLayout(gridProductModelList, GridLayouttitle);
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }

    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {
        private ViewPager bannerSliderViewPager;
        private int currentPage;
        private Timer timer;
        final private long DELAY_TIME = 5000;
        final private long PERIOD_TIME = 5000;
        private List<SliderModel> arrangedList;

        BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);

            bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);
//            setBannerSliderViewPager();
        }

        public void setBannerSliderViewPager(final List<SliderModel> sliderModelList) {
            currentPage = 2;
            if (timer != null) {
                timer.cancel();
            }

            //Arrangment for slider view
            arrangedList = new ArrayList<>();
            for (int i = 0; i <sliderModelList.size() ; i++) {
                arrangedList.add(i,sliderModelList.get(i));
            }
            arrangedList.add(0,sliderModelList.get(sliderModelList.size() -2));
            arrangedList.add(1,sliderModelList.get(sliderModelList.size() -1));
            arrangedList.add(sliderModelList.get(0));
            arrangedList.add(sliderModelList.get(1));

            SliderAdapter sliderAdapter = new SliderAdapter(arrangedList);
            bannerSliderViewPager.setAdapter(sliderAdapter);
            bannerSliderViewPager.setClipToPadding(false);
            bannerSliderViewPager.setPageMargin(20);
            bannerSliderViewPager.setCurrentItem(currentPage);
            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {
                }

                @Override
                public void onPageSelected(int i) {
                    currentPage = i;
                }

                @Override
                public void onPageScrollStateChanged(int i) {
                    if (i == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(arrangedList);
                    }
                }
            };

            bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);
            startBannerSlideShow(arrangedList);
            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    pageLooper(arrangedList);
                    stopbannerSlideShow();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        startBannerSlideShow(arrangedList);
                    }
                    return false;
                }
            });
        }

        private void pageLooper(List<SliderModel> sliderModelList) {
            if (currentPage == sliderModelList.size() - 2) {
                currentPage = 2;
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }
            if (currentPage == 1) {
                currentPage = sliderModelList.size() - 3;
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }
        }

        private void startBannerSlideShow(final List<SliderModel> sliderModelList) {
            final Handler handler = new Handler();
            final Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentPage >= sliderModelList.size()) {
                        currentPage = 1;
                    }
                    bannerSliderViewPager.setCurrentItem(currentPage++, true);
                }
            };

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, DELAY_TIME, PERIOD_TIME);
        }

        private void stopbannerSlideShow() {
            timer.cancel();
        }
    }

    public class StripAdBannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView stripAdImage;
        private ConstraintLayout stripadContainer;

        public StripAdBannerViewHolder(@NonNull View itemView) {
            super(itemView);

            stripAdImage = itemView.findViewById(R.id.strip_ad_image);
            stripadContainer = itemView.findViewById(R.id.strip_ad_container);
        }

        private void setStripAd(String resource, String color) {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.home_icon)).into(stripAdImage);
            stripadContainer.setBackgroundColor(Color.parseColor(color));
        }
    }

    public class HorizontalProductViewholder extends RecyclerView.ViewHolder {
        private TextView horizontalTitle;
        private Button horizontalviewAllButton;
        private RecyclerView horizontalRecyclerView;

        public HorizontalProductViewholder(@NonNull View itemView) {
            super(itemView);
            horizontalTitle = itemView.findViewById(R.id.horizontal_scroll_layout_title);
            horizontalviewAllButton = itemView.findViewById(R.id.horizontal_scroll_layout_btn);
            horizontalRecyclerView = itemView.findViewById(R.id.horizontal_scroll_layout_recyclerview);
            horizontalRecyclerView.setRecycledViewPool(recycledViewPool);
        }

        private void setHorizontalProductLayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList, String title) {
            horizontalTitle.setText(title);

            if (horizontalProductScrollModelList.size() > 8) {
                horizontalviewAllButton.setVisibility(View.VISIBLE);
                horizontalviewAllButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                        viewAllIntent.putExtra("layout_code", 0);
                        itemView.getContext().startActivity(viewAllIntent);
                    }
                });
            } else {
                horizontalviewAllButton.setVisibility(View.INVISIBLE);
            }

            HorizontalProductAdapter horizontalProductAdapter = new HorizontalProductAdapter(horizontalProductScrollModelList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            horizontalRecyclerView.setLayoutManager(linearLayoutManager);
            horizontalRecyclerView.setAdapter(horizontalProductAdapter);
            horizontalProductAdapter.notifyDataSetChanged();
        }

    }

    public class GridProductViewHolder extends RecyclerView.ViewHolder {
        TextView gridLayoutTitle;
        Button gridLayoutViewAllBtn;
        private GridLayout gridProductLayout;

        public GridProductViewHolder(@NonNull View itemView) {
            super(itemView);

            gridLayoutTitle = itemView.findViewById(R.id.grid_product_layout_title);
            gridLayoutViewAllBtn = itemView.findViewById(R.id.grid_product_layout_viewall_btn);
            gridProductLayout = itemView.findViewById(R.id.gridLayout);
        }

        private void setGridProductLayout(List<HorizontalProductScrollModel> gridProductScrollModelList, String title) {
            gridLayoutTitle.setText(title);
            for (int i = 0; i < 4; i++) {
                ImageView productImage = gridProductLayout.getChildAt(i).findViewById(R.id.h_s_product_image);
                TextView productTitle = gridProductLayout.getChildAt(i).findViewById(R.id.h_s_product_title);
                TextView productDescription = gridProductLayout.getChildAt(i).findViewById(R.id.h_s_product_description);
                TextView productPrice = gridProductLayout.getChildAt(i).findViewById(R.id.h_s_product_price);

                productImage.setImageResource(gridProductScrollModelList.get(i).getProductImage());
                productTitle.setText(gridProductScrollModelList.get(i).getProductTitle());
                productDescription.setText(gridProductScrollModelList.get(i).getProductDescription());
                productPrice.setText(gridProductScrollModelList.get(i).getProductPrice());
                gridProductLayout.getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
                gridProductLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                        itemView.getContext().startActivity(productDetailsIntent);
                    }
                });
            }
            gridLayoutViewAllBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                    viewAllIntent.putExtra("layout_code", 1);
                    itemView.getContext().startActivity(viewAllIntent);
                }
            });
        }
    }
}
