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
import com.example.mymall.activity.CategoryActivity;
import com.example.mymall.Model.CategoryModel;
import com.example.mymall.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<CategoryModel> list;
    private Context context;

    public CategoryAdapter(List<CategoryModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, viewGroup, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder categoryViewHolder, final int position) {
        CategoryModel categoryModel = list.get(position);
        categoryViewHolder.CategoryName.setText(categoryModel.getCategoryName());

        String icon = categoryModel.getCategoryIconLink();
        categoryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != 0) {

                    String name = categoryViewHolder.CategoryName.getText().toString();
                    Intent categoryintent = new Intent(context, CategoryActivity.class);
                    categoryintent.putExtra("CategoryName", name);
                    context.startActivity(categoryintent);
                }
            }
        });
        if (!icon.equals("null")) {
            Glide.with(context).load(icon).apply(new RequestOptions().placeholder(R.drawable.home_icon)).into(categoryViewHolder.categoryImage);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView categoryImage;
        private TextView CategoryName;

        CategoryViewHolder(@NonNull final View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.category_icon);
            CategoryName = itemView.findViewById(R.id.category_name);

        }
    }
}
