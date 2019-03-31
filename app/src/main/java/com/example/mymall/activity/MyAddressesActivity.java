package com.example.mymall.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mymall.Adapter.AddressesAdapter;
import com.example.mymall.Model.AddressesModel;
import com.example.mymall.R;

import java.util.ArrayList;
import java.util.List;

public class MyAddressesActivity extends AppCompatActivity {
    Toolbar toolbar;
    private RecyclerView myAddressesRecyclerView;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAddressesRecyclerView.setLayoutManager(layoutManager);

        List<AddressesModel> addressesModelList = new ArrayList<>();
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456"));
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456"));
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456"));
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456"));
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456"));
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456"));
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456"));
        addressesModelList.add(new AddressesModel("Prasad", "Nallasopara", "123456"));

        AddressesAdapter addressesAdapter = new AddressesAdapter(addressesModelList);
        myAddressesRecyclerView.setAdapter(addressesAdapter);
        myAddressesRecyclerView.setHasFixedSize(true);
        addressesAdapter.notifyDataSetChanged();
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
