package com.example.mymall.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mymall.Adapter.CategoryAdapter;
import com.example.mymall.Adapter.HomePageAdapter;
import com.example.mymall.Model.CategoryModel;
import com.example.mymall.Model.HomePageModel;
import com.example.mymall.Model.HorizontalProductScrollModel;
import com.example.mymall.Model.SliderModel;
import com.example.mymall.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView categoryRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.category_toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //wisget
        categoryRecyclerview = findViewById(R.id.category_recyclerview);

        //Banner slider
        List<SliderModel> sliderModelList = new ArrayList<>();

        sliderModelList.add(new SliderModel(R.drawable.ic_favorite_black, "#000000"));
        sliderModelList.add(new SliderModel(R.drawable.common_google_signin_btn_icon_dark_focused, "#000000"));
        sliderModelList.add(new SliderModel(R.drawable.sliderbanner, "#000000"));

        sliderModelList.add(new SliderModel(R.drawable.sliderbanner, "#000000"));
        sliderModelList.add(new SliderModel(R.drawable.ic_favorite_black, "#000000"));

        sliderModelList.add(new SliderModel(R.drawable.common_google_signin_btn_icon_dark_focused, "#000000"));
        sliderModelList.add(new SliderModel(R.drawable.sliderbanner, "#000000"));
        sliderModelList.add(new SliderModel(R.drawable.sliderbanner, "#000000"));
        //End Banner slider

        ///start delas of the day
        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));

        //End Horizontal


        ////RecyclerView
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerview.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.sliderbanner, "#000000"));
        homePageModelList.add(new HomePageModel(2, "Deals of the day", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3, "Treading", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.sliderbanner, "#ffff00"));
        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        categoryRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.category_search_icon) {
            //todo: search
            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
