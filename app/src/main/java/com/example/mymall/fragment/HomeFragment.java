package com.example.mymall.fragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mymall.Adapter.CategoryAdapter;
import com.example.mymall.Adapter.HomePageAdapter;
import com.example.mymall.Model.HomePageModel;
import com.example.mymall.R;

import java.util.ArrayList;

import static com.example.mymall.Utilites.DBqueries.categoryModelList;
import static com.example.mymall.Utilites.DBqueries.lists;
import static com.example.mymall.Utilites.DBqueries.loadCategories;
import static com.example.mymall.Utilites.DBqueries.loadFragmentData;
import static com.example.mymall.Utilites.DBqueries.loadedCategoriesNames;

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
    private ImageView noInternetConnection;
    HomePageAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        noInternetConnection = view.findViewById(R.id.no_internet_connection);
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {
            noInternetConnection.setVisibility(View.GONE);
            categoryRecyclerview = view.findViewById(R.id.home_category_recyclerview);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            categoryRecyclerview.setLayoutManager(layoutManager);


            categoryAdapter = new CategoryAdapter(categoryModelList, getActivity());
            categoryRecyclerview.setAdapter(categoryAdapter);
            if (categoryModelList.size() == 0) {
                loadCategories(categoryAdapter, getContext());
            } else {
                categoryAdapter.notifyDataSetChanged();
            }

            ////RecyclerView
            homePageRecyclerView = view.findViewById(R.id.home_page_recyclerview);
            LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
            testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            homePageRecyclerView.setLayoutManager(testingLayoutManager);


            if (lists.size() == 0) {
                loadedCategoriesNames.add("HOME");
                lists.add(new ArrayList<HomePageModel>());
                adapter = new HomePageAdapter(lists.get(0), getContext());
                loadFragmentData(adapter, getContext(), 0,"Home");
            } else {
                adapter = new HomePageAdapter(lists.get(0), getContext());
                adapter.notifyDataSetChanged();
            }
            homePageRecyclerView.setAdapter(adapter);
        } else {
            noInternetConnection.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.no_internet_connection).into(noInternetConnection);
        }


        //end RecyclerView
        return view;
    }

}
