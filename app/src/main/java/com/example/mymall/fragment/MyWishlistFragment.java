package com.example.mymall.fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
        WishlistAdapter adapter = new WishlistAdapter(wishlistModelList,true);
        wishlistRecyclerview.setAdapter(adapter);
        wishlistRecyclerview.setHasFixedSize(true);
        adapter.notifyDataSetChanged();

        return view;
    }

}
