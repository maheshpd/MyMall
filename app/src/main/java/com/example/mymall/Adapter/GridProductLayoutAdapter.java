package com.example.mymall.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymall.Model.GridProductScrollModel;
import com.example.mymall.Model.HorizontalProductScrollModel;
import com.example.mymall.R;
import com.example.mymall.activity.ProductDetailsActivity;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {

    List<HorizontalProductScrollModel> gridProductScrollModelList;
    private Context context;

    public GridProductLayoutAdapter(List<HorizontalProductScrollModel> gridProductScrollModelList) {
        this.gridProductScrollModelList = gridProductScrollModelList;
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
    public View getView(int position, View convertView, final ViewGroup parent) {
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

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(parent.getContext(), ProductDetailsActivity.class);
                    parent.getContext().startActivity(productDetailsIntent);
                }
            });
        } else {
            view = convertView;
        }
        return view;
    }
}
