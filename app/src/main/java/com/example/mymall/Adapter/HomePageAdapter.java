package com.example.mymall.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mymall.Model.HomePageModel;

import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter {
    private List<HomePageModel> homePageModelList;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {
        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
