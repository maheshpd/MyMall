package com.example.mymall.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymall.Adapter.WishlistAdapter;
import com.example.mymall.Model.WishlistModel;
import com.example.mymall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyWishlistFragment extends Fragment {


    public MyWishlistFragment() {
        // Required empty public constructor
    }

    private RecyclerView wishlistRecyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_wishlist, container, false);
        wishlistRecyclerview = view.findViewById(R.id.my_wishlist_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wishlistRecyclerview.setLayoutManager(layoutManager);

        List<WishlistModel> wishlistModelList = new ArrayList<>();
        wishlistModelList.add(new WishlistModel(R.drawable.gionee, "Gionee A1", 2, "2.7", 25, "Rs.15999/-", "Rs.17999/-", "Case on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.gionee, "Gionee A1", 0, "3.5", 10, "Rs.15999/-", "Rs.17999/-", "Case on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.gionee, "Gionee A1", 2, "3.1", 15, "Rs.15999/-", "Rs.17999/-", "Case on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.gionee, "Gionee A1", 1, "4.5", 25, "Rs.15999/-", "Rs.17999/-", "Case on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.gionee, "Gionee A1", 4, "4.7", 45, "Rs.15999/-", "Rs.17999/-", "Case on delivery"));

        WishlistAdapter adapter = new WishlistAdapter(wishlistModelList);
        wishlistRecyclerview.setAdapter(adapter);
        wishlistRecyclerview.setHasFixedSize(true);
        adapter.notifyDataSetChanged();

        return view;
    }

}
