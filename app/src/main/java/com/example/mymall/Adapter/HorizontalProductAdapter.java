package com.example.mymall.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymall.Model.HorizontalProductScrollModel;
import com.example.mymall.R;

import java.util.List;

public class HorizontalProductAdapter extends RecyclerView.Adapter<HorizontalProductAdapter.HorizontalProductViewHolder> {
    private List<HorizontalProductScrollModel> list;
    private Context context;

    public HorizontalProductAdapter(List<HorizontalProductScrollModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HorizontalProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_item_layout, viewGroup, false);
        return new HorizontalProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductViewHolder horizontalProductViewHolder, int i) {
        HorizontalProductScrollModel horizontalProductScrollModel = list.get(i);
        horizontalProductViewHolder.productTitle.setText(horizontalProductScrollModel.getProductTitle());
        horizontalProductViewHolder.productDescription.setText(horizontalProductScrollModel.getProductDescription());
        horizontalProductViewHolder.productPrice.setText(horizontalProductScrollModel.getProductPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HorizontalProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productTitle, productDescription, productPrice;

        public HorizontalProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.h_s_product_image);
            productTitle = itemView.findViewById(R.id.h_s_product_title);
            productDescription = itemView.findViewById(R.id.h_s_product_description);
            productPrice = itemView.findViewById(R.id.h_s_product_price);
        }
    }
}