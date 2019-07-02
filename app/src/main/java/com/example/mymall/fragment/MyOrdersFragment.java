package com.example.mymall.fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymall.Adapter.MyOrderAdapter;
import com.example.mymall.Model.MyOrderItemModel;
import com.example.mymall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {


    public MyOrdersFragment() {
        // Required empty public constructor
    }

    private RecyclerView myOrdersRecyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);
        myOrdersRecyclerview = view.findViewById(R.id.my_order_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myOrdersRecyclerview.setLayoutManager(layoutManager);

        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.gionee,2,"Gionne A1","Delivered On Mon, 1st 2019"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.gionee,1,"Gionne A1","Delivered On Mon, 5st 2019"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.gionee,0,"Gionne A1","Cancelled"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.gionee,2,"Gionne A1","Delivered On Mon, 20st 2019"));

        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderItemModelList);
        myOrdersRecyclerview.setAdapter(myOrderAdapter);
        myOrdersRecyclerview.setHasFixedSize(true);
        myOrderAdapter.notifyDataSetChanged();
        return view;
    }

}
