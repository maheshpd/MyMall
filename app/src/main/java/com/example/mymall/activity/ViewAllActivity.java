package com.example.mymall.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.example.mymall.Adapter.GridProductLayoutAdapter;
import com.example.mymall.Adapter.WishlistAdapter;
import com.example.mymall.Model.HorizontalProductScrollModel;
import com.example.mymall.Model.WishlistModel;
import com.example.mymall.R;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private GridView gridView;
    public static List<HorizontalProductScrollModel> horizontalProductScrollModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        gridView = findViewById(R.id.grid_view);
        int layout_code = getIntent().getIntExtra("layout_code", -1);
        if (layout_code==0) {


            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            List<WishlistModel> wishlistModelList = new ArrayList<>();
            wishlistModelList.add(new WishlistModel(R.drawable.gionee, "Gionee A1", 2, "2.7", 25, "Rs.15999/-", "Rs.17999/-", "Case on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.gionee, "Gionee A1", 0, "3.5", 10, "Rs.15999/-", "Rs.17999/-", "Case on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.gionee, "Gionee A1", 2, "3.1", 15, "Rs.15999/-", "Rs.17999/-", "Case on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.gionee, "Gionee A1", 1, "4.5", 25, "Rs.15999/-", "Rs.17999/-", "Case on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.gionee, "Gionee A1", 4, "4.7", 45, "Rs.15999/-", "Rs.17999/-", "Case on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.gionee, "Gionee A1", 2, "2.7", 25, "Rs.15999/-", "Rs.17999/-", "Case on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.gionee, "Gionee A1", 0, "3.5", 10, "Rs.15999/-", "Rs.17999/-", "Case on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.gionee, "Gionee A1", 2, "3.1", 15, "Rs.15999/-", "Rs.17999/-", "Case on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.gionee, "Gionee A1", 1, "4.5", 25, "Rs.15999/-", "Rs.17999/-", "Case on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.gionee, "Gionee A1", 4, "4.7", 45, "Rs.15999/-", "Rs.17999/-", "Case on delivery"));
            WishlistAdapter wishlistAdapter = new WishlistAdapter(wishlistModelList, false);
            recyclerView.setAdapter(wishlistAdapter);
            wishlistAdapter.notifyDataSetChanged();
        }else if (layout_code == 1) {
            gridView.setVisibility(View.VISIBLE);
            GridProductLayoutAdapter gridProductLayoutAdapter = new GridProductLayoutAdapter(horizontalProductScrollModelList);
            gridView.setAdapter(gridProductLayoutAdapter);
            gridProductLayoutAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
