package com.example.customerordertracker.Activities;

import android.os.Bundle;

import com.example.customerordertracker.Entities.Order;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.OrderViewModel;
import com.example.customerordertracker.ui.OrderAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchListOrder extends AppCompatActivity {

    private OrderViewModel mOrderViewModel;
    private List<Order> orderData;
    private int position;
    private CharSequence searchInput;

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        mOrderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        setTitle("Order Search Result");
        searchInput = getIntent().getCharSequenceExtra("searchInput");

        RecyclerView recyclerView = findViewById(R.id.search_list_recycler);
        final OrderAdapter adapter = new OrderAdapter(orderData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        mOrderViewModel.getAllOrders().observe(this, orders -> {
            List<Order> searchedOrders = new ArrayList<>();
            for(Order p:orders){
                if(p.getName().contains(searchInput) || p.getDescription().contains(searchInput)){
                    searchedOrders.add(p);
                }
            }
            adapter.setOrders(searchedOrders);
        });

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });
    }

    private void updateList() {
        RecyclerView recyclerView = findViewById(R.id.search_list_recycler);
        final OrderAdapter adapter = new OrderAdapter(orderData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mOrderViewModel.getAllOrders().observe(this, orders -> {
            List<Order> searchedOrders = new ArrayList<>();
            for (Order p : orders) {
                if (p.getName().contains(searchInput) || p.getDescription().contains(searchInput)) {
                    searchedOrders.add(p);
                }
            }
            adapter.setOrders(searchedOrders);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}