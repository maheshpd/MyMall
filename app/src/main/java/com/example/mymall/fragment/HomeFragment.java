package com.example.mymall.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mymall.Adapter.CategoryAdapter;
import com.example.mymall.Adapter.HomePageAdapter;
import com.example.mymall.Model.CategoryModel;
import com.example.mymall.Model.HomePageModel;
import com.example.mymall.Model.SliderModel;
import com.example.mymall.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerview;
    private CategoryAdapter categoryAdapter;
    private RecyclerView homePageRecyclerView;
    private List<CategoryModel> categoryModelList;
    HomePageAdapter adapter;
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
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(), documentSnapshot.get("categoryName").toString()));
                            }
                            categoryAdapter.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Banner slider



        //End Banner slider

        ///start delas of the day
//        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.gionee, "Gionee A1", "SD 625 Processor", "Rs.15000/-"));

        //End Horizontal


        ////RecyclerView
        homePageRecyclerView = view.findViewById(R.id.home_page_recyclerview);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homePageRecyclerView.setLayoutManager(testingLayoutManager);
        final List<HomePageModel> homePageModelList = new ArrayList<>();
        adapter = new HomePageAdapter(homePageModelList);
        homePageRecyclerView.setAdapter(adapter);

        firebaseFirestore.collection("CATEGORIES").document("HOME")
                .collection("TOP_DEALS").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                if ((long) documentSnapshot.get("view_type")==0){
                                    List<SliderModel> sliderModelList = new ArrayList<>();
                                    long no_of_banners = (long)documentSnapshot.get("no_of_banners");
                                    for (long i = 1; i < no_of_banners + 1; i++) {
                                        sliderModelList.add(new SliderModel(documentSnapshot.get("banner_"+i).toString(),
                                                documentSnapshot.get("banner_"+i+"_background").toString()));
                                    }
                                    homePageModelList.add(new HomePageModel(0,sliderModelList));
                                }else if ((long) documentSnapshot.get("view_type")==1){
                                    homePageModelList.add(new HomePageModel(1,documentSnapshot.get("strip_ad_banner").toString(),
                                            documentSnapshot.get("background").toString()));
                                }else if ((long) documentSnapshot.get("view_type")==2){

                                }else if ((long) documentSnapshot.get("view_type")==3){

                                }
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //end RecyclerView
        return view;
    }

}
