package com.example.mymall.fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymall.R;

import static com.example.mymall.activity.ProductDetailsActivity.productDescription;
import static com.example.mymall.activity.ProductDetailsActivity.productOtherDetails;
import static com.example.mymall.activity.ProductDetailsActivity.tabPosition;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDescriptionFragment extends Fragment {

    private TextView descriptionBody;
    public ProductDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product_description, container, false);
        descriptionBody = view.findViewById(R.id.tv_product_description);
        if (tabPosition == 0) {
            descriptionBody.setText(productDescription);
        }else {
            descriptionBody.setText(productOtherDetails);
        }
        return view;
    }

}
