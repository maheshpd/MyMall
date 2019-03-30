package com.example.mymall.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymall.Adapter.CartAdapter;
import com.example.mymall.Model.CartItemModel;
import com.example.mymall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {


    public MyCartFragment() {
        // Required empty public constructor
    }

    private RecyclerView cartItemsRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);
        cartItemsRecyclerView = view.findViewById(R.id.cart_item_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        cartItemsRecyclerView.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0, R.drawable.gionee, "Gionee", 2, "Rs.49999/-", "Rs.51000/-", 1, 0, 0));
        cartItemModelList.add(new CartItemModel(0, R.drawable.gionee, "Gionee", 0, "Rs.49999/-", "Rs.51000/-", 1, 1, 0));
        cartItemModelList.add(new CartItemModel(0, R.drawable.gionee, "Gionee", 2, "Rs.49999/-", "Rs.51000/-", 1, 2, 0));
        cartItemModelList.add(new CartItemModel(1, "Price (3 items)", "Rs.15999/-", "Free", "Rs.48000/-", "5000"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartItemsRecyclerView.setAdapter(cartAdapter);
        cartItemsRecyclerView.setHasFixedSize(true);
        cartAdapter.notifyDataSetChanged();
        return view;
    }

}