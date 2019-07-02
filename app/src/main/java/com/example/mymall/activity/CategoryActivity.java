package com.example.mymall.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mymall.Adapter.HomePageAdapter;
import com.example.mymall.Model.HomePageModel;
import com.example.mymall.R;

import java.util.ArrayList;

import static com.example.mymall.Utilites.DBqueries.lists;
import static com.example.mymall.Utilites.DBqueries.loadFragmentData;
import static com.example.mymall.Utilites.DBqueries.loadedCategoriesNames;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView categoryRecyclerview;
    HomePageAdapter adapter;
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

        //End Banner slider
        ////RecyclerView
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerview.setLayoutManager(testingLayoutManager);

        int listPosition = 0;
        for (int i = 0; i < loadedCategoriesNames.size(); i++) {
            if (loadedCategoriesNames.get(i).equals(title.toUpperCase())) {
                listPosition = i;
            }
        }

        if (listPosition == 0)
        {
            loadedCategoriesNames.add(title.toUpperCase());
            lists.add(new ArrayList<HomePageModel>());
            adapter = new HomePageAdapter(lists.get(loadedCategoriesNames.size()-1),this);
            loadFragmentData(adapter, this, loadedCategoriesNames.size()-1,title);
        }else {
            adapter = new HomePageAdapter(lists.get(listPosition),this);
        }


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
