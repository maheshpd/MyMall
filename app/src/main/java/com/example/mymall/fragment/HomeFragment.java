package com.example.mymall.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymall.Adapter.CategoryAdapter;
import com.example.mymall.Adapter.GridProductLayoutAdapter;
import com.example.mymall.Adapter.HorizontalProductAdapter;
import com.example.mymall.Adapter.SliderAdapter;
import com.example.mymall.Model.CategoryModel;
import com.example.mymall.Model.GridProductScrollModel;
import com.example.mymall.Model.HorizontalProductScrollModel;
import com.example.mymall.Model.SliderModel;
import com.example.mymall.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerview;
    private CategoryAdapter categoryAdapter;

    ////// Bannrt Slider
    public ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    /////

    /////strip Ad
    private ImageView stripAdImage;
    private ConstraintLayout stripadContainer;
    //

    //Horizontal Deals of the day
    private TextView horizontalTitle;
    private Button horizontalviewAllButton;
    private RecyclerView horizontalRecyclerView;
    //


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerview = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerview.setLayoutManager(layoutManager);

        List<CategoryModel> categoryModelList = new ArrayList<>();
        categoryModelList.add(new CategoryModel("Link", "Home"));
        categoryModelList.add(new CategoryModel("Link", "Electronics"));
        categoryModelList.add(new CategoryModel("Link", "Appliances"));
        categoryModelList.add(new CategoryModel("Link", "Furniture"));
        categoryModelList.add(new CategoryModel("Link", "Fashion"));
        categoryModelList.add(new CategoryModel("Link", "Toys"));
        categoryModelList.add(new CategoryModel("Link", "Wall Arts"));
        categoryModelList.add(new CategoryModel("Link", "Books"));
        categoryModelList.add(new CategoryModel("Link", "Mobile"));
        categoryModelList.add(new CategoryModel("Link", "Men Fashion"));

        categoryAdapter = new CategoryAdapter(categoryModelList, getActivity());
        categoryRecyclerview.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        //Banner slider
        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);

        sliderModelList = new ArrayList<>();

        sliderModelList.add(new SliderModel(R.drawable.ic_favorite_black, "#000000"));
        sliderModelList.add(new SliderModel(R.drawable.common_google_signin_btn_icon_dark_focused, "#000000"));
        sliderModelList.add(new SliderModel(R.drawable.sliderbanner, "#000000"));

        sliderModelList.add(new SliderModel(R.drawable.sliderbanner, "#000000"));
        sliderModelList.add(new SliderModel(R.drawable.ic_favorite_black, "#000000"));

        sliderModelList.add(new SliderModel(R.drawable.common_google_signin_btn_icon_dark_focused, "#000000"));
        sliderModelList.add(new SliderModel(R.drawable.sliderbanner, "#000000"));
        sliderModelList.add(new SliderModel(R.drawable.sliderbanner, "#000000"));

        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
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
                    pageLooper();
                }
            }
        };

        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);
        startBannerSlideShow();
        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopbannerSlideShow();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    startBannerSlideShow();
                }
                return false;
            }
        });
        //End Banner slider

        /////start strip Ad
        stripAdImage = view.findViewById(R.id.strip_ad_image);
        stripadContainer = view.findViewById(R.id.strip_ad_container);
        stripAdImage.setImageResource(R.drawable.sliderbanner);
        stripadContainer.setBackgroundColor(Color.parseColor("#000000"));
        ///end strip ad

        ///start delas of the day
        horizontalTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalviewAllButton = view.findViewById(R.id.horizontal_scroll_layout_btn);
        horizontalRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recyclerview);

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        HorizontalProductAdapter horizontalProductAdapter = new HorizontalProductAdapter(horizontalProductScrollModelList, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);
        horizontalRecyclerView.setAdapter(horizontalProductAdapter);
        horizontalProductAdapter.notifyDataSetChanged();
        //End Horizontal

        //start grid productLayout
        List<GridProductScrollModel> gridProductScrollModelList = new ArrayList<>();
        gridProductScrollModelList.add(new GridProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        gridProductScrollModelList.add(new GridProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        gridProductScrollModelList.add(new GridProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        gridProductScrollModelList.add(new GridProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        gridProductScrollModelList.add(new GridProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        gridProductScrollModelList.add(new GridProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        gridProductScrollModelList.add(new GridProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        gridProductScrollModelList.add(new GridProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));

        TextView gridLayoutTitle = view.findViewById(R.id.grid_product_layout_title);
        Button gridLayoutViewAllBtn = view.findViewById(R.id.grid_product_layout_viewall_btn);
        GridView gridView = view.findViewById(R.id.grid_product_layout_gridview);
        gridView.setAdapter(new GridProductLayoutAdapter(gridProductScrollModelList, getContext()));
        //end grid productLayout

        ////RecyclerView
        RecyclerView testing = view.findViewById(R.id.testing);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);
        //end RecyclerView
        return view;
    }

    private void pageLooper() {
        if (currentPage == sliderModelList.size() - 2) {
            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
        if (currentPage == 1) {
            currentPage = sliderModelList.size() - 3;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
    }

    private void startBannerSlideShow() {
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
