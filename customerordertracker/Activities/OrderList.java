package com.example.customerordertracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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

import static com.example.customerordertracker.Utilities.Constants.NEW_ORDER_ACTIVITY_REQUEST_CODE;

public class OrderList extends AppCompatActivity {

    private OrderViewModel mOrderViewModel;
    private List<Order> orderData = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_order_list);
        mOrderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        setTitle("Order List");
        RecyclerView recyclerView = findViewById(R.id.order_list_recycler);
        final OrderAdapter adapter = new OrderAdapter(orderData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        mOrderViewModel.getAllOrders().observe(this, orders -> adapter.setOrders(orders));
        updateList();

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });
    }

    private void updateList() {
        mOrderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.order_list_recycler);
        final OrderAdapter adapter = new OrderAdapter(orderData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mOrderViewModel.getAllOrders().observe(this, orders -> adapter.setOrders(orders));
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
