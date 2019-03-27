package com.example.mymall.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymall.Adapter.ProductSpecificationAdapter;
import com.example.mymall.Model.ProductSpecificationModel;
import com.example.mymall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductSpecificationFragment extends Fragment {


    public ProductSpecificationFragment() {
        // Required empty public constructor
    }

    private RecyclerView productSpecificationRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_specification, container, false);
        productSpecificationRecyclerView = view.findViewById(R.id.product_specification_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productSpecificationRecyclerView.setLayoutManager(linearLayoutManager);

        List<ProductSpecificationModel> productSpecificationModelList = new ArrayList<>();
        productSpecificationModelList.add(new ProductSpecificationModel("RAM", "4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM", "4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM", "4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM", "4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM", "4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM", "4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM", "4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM", "4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM", "4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM", "4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM", "4GB"));

        ProductSpecificationAdapter productSpecificationAdapter = new ProductSpecificationAdapter(productSpecificationModelList);
        productSpecificationRecyclerView.setAdapter(productSpecificationAdapter);
        productSpecificationRecyclerView.setHasFixedSize(true);
        productSpecificationAdapter.notifyDataSetChanged();
        return view;
    }

}