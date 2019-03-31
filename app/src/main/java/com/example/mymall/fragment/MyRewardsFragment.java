package com.example.mymall.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymall.Adapter.MyRewardsAdapter;
import com.example.mymall.Model.RewardModel;
import com.example.mymall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyRewardsFragment extends Fragment {


    public MyRewardsFragment() {
        // Required empty public constructor
    }

    private RecyclerView rewardsRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_rewards, container, false);
        rewardsRecyclerView = view.findViewById(R.id.my_rewards_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rewardsRecyclerView.setLayoutManager(layoutManager);

        List<RewardModel> rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel("Cashback", "till 2nd, june 2018", "GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));
        rewardModelList.add(new RewardModel("Discount", "till 12nd, june 2018", "GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));
        rewardModelList.add(new RewardModel("Cashback", "till 2nd, june 2018", "GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));
        rewardModelList.add(new RewardModel("Buy 1 get 1 free", "till 2nd, june 2018", "GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));
        rewardModelList.add(new RewardModel("Cashback", "till 2nd, june 2018", "GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));
        rewardModelList.add(new RewardModel("Cashback", "till 2nd, june 2018", "GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));
        rewardModelList.add(new RewardModel("Cashback", "till 2nd, june 2018", "GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));
        rewardModelList.add(new RewardModel("Cashback", "till 2nd, june 2018", "GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));

        MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter(rewardModelList);
        myRewardsAdapter.notifyDataSetChanged();
        rewardsRecyclerView.setLayoutManager(layoutManager);
        rewardsRecyclerView.setHasFixedSize(true);
        rewardsRecyclerView.setAdapter(myRewardsAdapter);


        return view;
    }

}
