package com.example.mymall.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.mymall.Adapter.AddressesAdapter;
import com.example.mymall.Model.AddressesModel;
import com.example.mymall.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.mymall.activity.DeliveryActivity.SELECTE_ADDRESS;

public class MyAddressesActivity extends AppCompatActivity {
    Toolbar toolbar;
    private RecyclerView myAddressesRecyclerView;
    private static AddressesAdapter addressesAdapter;
    private Button deliverHereBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Addresses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myAddressesRecyclerView = findViewById(R.id.addresses_recyclerview);
        deliverHereBtn = findViewById(R.id.deliver_here_btn);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAddressesRecyclerView.setLayoutManager(layoutManager);

        List<AddressesModel> addressesModelList = new ArrayList<>();
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456", true));
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456", false));
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456", false));
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456", false));
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456", false));
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456", false));
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456", false));
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456", false));

        int mode = getIntent().getIntExtra("MODE", -1);
        if (mode == SELECTE_ADDRESS) {
            deliverHereBtn.setVisibility(View.VISIBLE);
        }else {
            deliverHereBtn.setVisibility(View.GONE);
        }
        addressesAdapter = new AddressesAdapter(addressesModelList, mode);
        myAddressesRecyclerView.setAdapter(addressesAdapter);
        myAddressesRecyclerView.setHasFixedSize(true);
        ((SimpleItemAnimator) myAddressesRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        addressesAdapter.notifyDataSetChanged();
    }

    public static void refreshItem(int deselect, int select) {
        addressesAdapter.notifyItemChanged(deselect);
        addressesAdapter.notifyItemChanged(select);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
