package com.example.mymall.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymall.Adapter.CategoryAdapter;
import com.example.mymall.Adapter.GridProductLayoutAdapter;
import com.example.mymall.Adapter.HomePageAdapter;
import com.example.mymall.Adapter.HorizontalProductAdapter;
import com.example.mymall.Adapter.SliderAdapter;
import com.example.mymall.Model.CategoryModel;
import com.example.mymall.Model.GridProductScrollModel;
import com.example.mymall.Model.HomePageModel;
import com.example.mymall.Model.HorizontalProductScrollModel;
import com.example.mymall.Model.SliderModel;
import com.example.mymall.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerview;
    private CategoryAdapter categoryAdapter;
    private RecyclerView testing;
    private  List<CategoryModel> categoryModelList;

    ////Firebase
    private FirebaseFirestore firebaseFirestore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerview = view.findViewById(R.id.home_category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerview.setLayoutManager(layoutManager);

        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categoryModelList, getActivity());
        categoryRecyclerview.setAdapter(categoryAdapter);


        //Firebase
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(),documentSnapshot.get("categoryName").toString()));
                            }
                            categoryAdapter.notifyDataSetChanged();
                        }else {
                            String error = task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Banner slider
        List<SliderModel> sliderModelList = new ArrayList<>();

        sliderModelList.add(new SliderModel(R.drawable.sliderbanner, "#000000"));
        sliderModelList.add(new SliderModel(R.drawable.ic_favorite_black, "#000000"));
        sliderModelList.add(new SliderModel(R.drawable.common_google_signin_btn_icon_dark_focused, "#000000"));

        //End Banner slider

        ///start delas of the day
        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));

        //End Horizontal


        ////RecyclerView
        testing = view.findViewById(R.id.home_page_recyclerview);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.sliderbanner, "#000000"));
        homePageModelList.add(new HomePageModel(2, "Deals of the day", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3, "Treading", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.sliderbanner, "#ffff00"));

        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.sliderbanner, "#000000"));
        homePageModelList.add(new HomePageModel(2, "Deals of the day", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3, "Treading", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.sliderbanner, "#ffff00"));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //end RecyclerView
        return view;
    }

}
