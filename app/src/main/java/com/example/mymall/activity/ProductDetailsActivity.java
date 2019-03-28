package com.example.mymall.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mymall.Adapter.ProductDetailsAdapter;
import com.example.mymall.Adapter.ProductImagesAdapter;
import com.example.mymall.R;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    private ViewPager productImagesViewPager;
    private TabLayout viewpagerIndicator;
    private FloatingActionButton addToWishlistBtn;
    private static boolean AlREADYADDEDTOWISHLIST = false;

    private ViewPager productDetailsViewpager;
    private TabLayout productDetailsTabLayout;

    //start rating layout
    private LinearLayout rateNowContainer;

    //end rating layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Toolbar toolbar = findViewById(R.id.productDetailstoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImagesViewPager = findViewById(R.id.product_images_view_pager);
        viewpagerIndicator = findViewById(R.id.viewpager_indicator);
        addToWishlistBtn = findViewById(R.id.addtoWishlist);

        //product Details
        productDetailsViewpager = findViewById(R.id.product_details_viewpager);
        productDetailsTabLayout = findViewById(R.id.product_details_tablayout);

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.gionee);
        productImages.add(R.drawable.user);
        productImages.add(R.drawable.gionee);
        productImages.add(R.drawable.sliderbanner);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productImagesViewPager.setAdapter(productImagesAdapter);

        viewpagerIndicator.setupWithViewPager(productImagesViewPager, true);
        addToWishlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AlREADYADDEDTOWISHLIST) {
                    AlREADYADDEDTOWISHLIST = false;
                    addToWishlistBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9f9f9f")));
                } else {
                    AlREADYADDEDTOWISHLIST = true;
                    addToWishlistBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimary));
                }
            }
        });

        productDetailsViewpager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDetailsTabLayout.getTabCount()));
        productDetailsViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));
        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        ////rating layout
        rateNowContainer = findViewById(R.id.rate_now_container);
        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
            final int startPosition = x;
            rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(startPosition);
                }
            });
        }
        ////rating layout
    }

    private void setRating(int startPosition) {
        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
            ImageView startBtn = (ImageView) rateNowContainer.getChildAt(x);
            startBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            if (x <= startPosition){
                startBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.productdetails_action_search) {
            //todo: search
            return true;
        } else if (id == R.id.productdetails_cart_icon) {
            //todo:notification
            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;
            //todo:cart
        }
        return super.onOptionsItemSelected(item);
    }
}
