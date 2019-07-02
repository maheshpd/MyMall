package com.example.mymall.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mymall.Model.HorizontalProductScrollModel;
import com.example.mymall.R;
import com.example.mymall.activity.ProductDetailsActivity;

import java.util.List;

public class HorizontalProductAdapter extends RecyclerView.Adapter<HorizontalProductAdapter.HorizontalProductViewHolder> {
    private List<HorizontalProductScrollModel> list;
    private Context context;
    public HorizontalProductAdapter(List<HorizontalProductScrollModel> list,Context context) {
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
        Glide.with(context).load(horizontalProductScrollModel.getProductImage()).apply(new RequestOptions()).placeholder(R.drawable.gionee).into(horizontalProductViewHolder.productImage);
        horizontalProductViewHolder.productTitle.setText(horizontalProductScrollModel.getProductTitle());
        horizontalProductViewHolder.productDescription.setText(horizontalProductScrollModel.getProductDescription());
        horizontalProductViewHolder.productPrice.setText("Rs."+horizontalProductScrollModel.getProductPrice()+"/-");
    }

    @Override
    public int getItemCount() {
        if (list.size() > 8) {
            return 8;
        } else {
            return list.size();
        }
    }

    public class HorizontalProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productTitle, productDescription, productPrice;

        public HorizontalProductViewHolder(@NonNull final View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.h_s_product_image);
            productTitle = itemView.findViewById(R.id.h_s_product_title);
            productDescription = itemView.findViewById(R.id.h_s_product_description);
            productPrice = itemView.findViewById(R.id.h_s_product_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }
    }
}
