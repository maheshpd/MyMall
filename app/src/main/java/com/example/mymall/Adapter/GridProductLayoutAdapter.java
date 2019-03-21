package com.example.mymall.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymall.Model.GridProductScrollModel;
import com.example.mymall.R;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {

    List<GridProductScrollModel> gridProductScrollModelList;
    private Context context;

    public GridProductLayoutAdapter(List<GridProductScrollModel> gridProductScrollModelList, Context context) {
        this.gridProductScrollModelList = gridProductScrollModelList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_scroll_item_layout, null);
            view.setElevation(0);
            view.setBackgroundColor(Color.parseColor("#ffffff"));
            ImageView productImage = view.findViewById(R.id.g_s_product_image);
            TextView productTitle = view.findViewById(R.id.g_s_product_title);
            TextView productDescription = view.findViewById(R.id.g_s_product_description);
            TextView productPrice = view.findViewById(R.id.g_s_product_price);

            productImage.setImageResource(gridProductScrollModelList.get(position).getProductImage());
            productTitle.setText(gridProductScrollModelList.get(position).getProductTitle());
            productDescription.setText(gridProductScrollModelList.get(position).getProductDescription());
            productPrice.setText(gridProductScrollModelList.get(position).getProductPrice());
        } else {
            view = convertView;
        }
        return view;
    }
}