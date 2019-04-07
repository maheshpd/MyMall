package com.example.mymall.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymall.Adapter.CategoryAdapter;
import com.example.mymall.Adapter.HomePageAdapter;
import com.example.mymall.R;

import static com.example.mymall.Utilites.DBqueries.categoryModelList;
import static com.example.mymall.Utilites.DBqueries.homePageModelList;
import static com.example.mymall.Utilites.DBqueries.loadCategories;
import static com.example.mymall.Utilites.DBqueries.loadFragmentData;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerview;
    private CategoryAdapter categoryAdapter;
    private RecyclerView homePageRecyclerView;

    HomePageAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerview = view.findViewById(R.id.home_category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerview.setLayoutManager(layoutManager);


        categoryAdapter = new CategoryAdapter(categoryModelList, getActivity());
        categoryRecyclerview.setAdapter(categoryAdapter);
        if (categoryModelList.size() == 0) {
            loadCategories(categoryAdapter,getContext());
        }else {
            categoryAdapter.notifyDataSetChanged();
        }

        ////RecyclerView
        homePageRecyclerView = view.findViewById(R.id.home_page_recyclerview);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homePageRecyclerView.setLayoutManager(testingLayoutManager);

        adapter = new HomePageAdapter(homePageModelList, getContext());
        homePageRecyclerView.setAdapter(adapter);

        if (homePageModelList.size() == 0) {
            loadFragmentData(adapter,getContext());
        }else {
            categoryAdapter.notifyDataSetChanged();
        }

        //end RecyclerView
        return view;
    }

}
