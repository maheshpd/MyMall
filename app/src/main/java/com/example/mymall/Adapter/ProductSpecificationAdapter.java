package com.example.mymall.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymall.Model.ProductSpecificationModel;
import com.example.mymall.R;

import java.util.List;

public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.ProductSpecificationViewHolder> {
    private List<ProductSpecificationModel> productSpecificationModelList;

    public ProductSpecificationAdapter(List<ProductSpecificationModel> productSpecificationModelList) {
        this.productSpecificationModelList = productSpecificationModelList;
    }

    @NonNull
    @Override

    public ProductSpecificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_specification_item_layout, viewGroup, false);
        return new ProductSpecificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSpecificationViewHolder productSpecificationViewHolder, int i) {
        ProductSpecificationModel productSpecificationModel = productSpecificationModelList.get(i);
        productSpecificationViewHolder.featurename.setText(productSpecificationModel.getFeatureName());
        productSpecificationViewHolder.featureValue.setText(productSpecificationModel.getFeatureValue());
    }

    @Override
    public int getItemCount() {
        return productSpecificationModelList.size();
    }

    public class ProductSpecificationViewHolder extends RecyclerView.ViewHolder {
        private TextView featurename, featureValue;

        public ProductSpecificationViewHolder(@NonNull View itemView) {
            super(itemView);
            featurename = itemView.findViewById(R.id.feature_name);
            featureValue = itemView.findViewById(R.id.feature_value);
        }
    }
}
