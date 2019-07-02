package com.example.mymall.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mymall.Model.ProductSpecificationModel;
import com.example.mymall.R;

import java.util.List;

public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.ProductSpecificationViewHolder> {
    private List<ProductSpecificationModel> productSpecificationModelList;

    public ProductSpecificationAdapter(List<ProductSpecificationModel> productSpecificationModelList) {
        this.productSpecificationModelList = productSpecificationModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (productSpecificationModelList.get(position).getType()) {
            case 0:
                return ProductSpecificationModel.SPECIFICATION_TITLE;
            case 1:
                return ProductSpecificationModel.SPECIFICATION_BODY;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public ProductSpecificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case ProductSpecificationModel.SPECIFICATION_TITLE:
                TextView title = new TextView(viewGroup.getContext());
                title.setTypeface(null, Typeface.BOLD);
                title.setTextColor(Color.parseColor("#000000"));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(setDp(16, viewGroup.getContext()),
                        setDp(16, viewGroup.getContext()),
                        setDp(16, viewGroup.getContext()),
                        setDp(8, viewGroup.getContext()));
                title.setLayoutParams(layoutParams);
                return new ProductSpecificationViewHolder(title);

            case ProductSpecificationModel.SPECIFICATION_BODY:
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_specification_item_layout, viewGroup, false);
                return new ProductSpecificationViewHolder(view);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ProductSpecificationViewHolder productSpecificationViewHolder, int i) {

        switch (productSpecificationModelList.get(i).getType()) {
            case ProductSpecificationModel.SPECIFICATION_TITLE:
                productSpecificationViewHolder.setTitle(productSpecificationModelList.get(i).getTitle());
                break;
            case ProductSpecificationModel.SPECIFICATION_BODY:
                String featureTitle = productSpecificationModelList.get(i).getFeatureName();
                String featureDetails = productSpecificationModelList.get(i).getFeatureValue();
                productSpecificationViewHolder.setFeature(featureTitle, featureDetails);
                break;
            default:
                return;
        }


    }

    @Override
    public int getItemCount() {
        return productSpecificationModelList.size();
    }

    public class ProductSpecificationViewHolder extends RecyclerView.ViewHolder {
        private TextView featurename, featureValue, title;

        public ProductSpecificationViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        private void setTitle(String titleText) {
            title = (TextView) itemView;
            title.setText(titleText);
        }


        private void setFeature(String featureTitle, String featuredetails) {
            featurename = itemView.findViewById(R.id.feature_name);
            featureValue = itemView.findViewById(R.id.feature_value);
            featurename.setText(featureTitle);
            featurename.setText(featuredetails);
        }
    }

    private int setDp(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
