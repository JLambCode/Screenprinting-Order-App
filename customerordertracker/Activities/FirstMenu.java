package com.example.customerordertracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Order;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.OrderViewModel;
import com.example.customerordertracker.ui.OrderAdapter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FirstMenu extends AppCompatActivity {

    private OrderViewModel mOrderViewModel;
    private List<Order> orderData = new ArrayList<>();
    public static int numOrders;
    private Calendar current = Calendar.getInstance();
    private Calendar weekAway = Calendar.getInstance();
    private TextView searchInput;

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_menu);
        mOrderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        setTitle("Main Menu");
        searchInput = findViewById(R.id.inputSearch);
        current.setTime(Date.from(Instant.now()));
        weekAway.add(Calendar.DAY_OF_MONTH, 7);

        RecyclerView recyclerView = findViewById(R.id.orders_due_recycler);
        final OrderAdapter adapter = new OrderAdapter(orderData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        try {
            mOrderViewModel.getAllOrders().observe(this, orders -> {
                List<Order> dueOrders = new ArrayList<>();
                for (Order p : orders) {
                    if (p.getDateDue().compareTo(current.getTime()) >= 0 && p.getDateDue().compareTo(weekAway.getTime()) < 0) {
                        dueOrders.add(p);
                    }
                }
                adapter.setOrders(dueOrders);
                numOrders = dueOrders.size();
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No orders due within a week", Toast.LENGTH_SHORT).show();
        }

        Button searchOrder = findViewById(R.id.btnSearchOrder);
        Button searchClient = findViewById(R.id.btnSearchClient);
        Button viewOrders = findViewById(R.id.btnOrders);
        Button viewClients = findViewById(R.id.btnClients);
        Button back = findViewById(R.id.btnBack);
        Button reports = findViewById(R.id.btnReports);

        back.setOnClickListener(view -> {
            finish();
        });

        reports.setOnClickListener(view -> {
            Intent intent = new Intent(this, Reports.class);
            startActivity(intent);
        });

        viewOrders.setOnClickListener(view -> {
                Intent intent = new Intent(this, OrderList.class);
                startActivity(intent);
        });

        viewClients.setOnClickListener(view -> {
                Intent intent = new Intent(this, ClientList.class);
                startActivity(intent);
        });

        searchOrder.setOnClickListener(view -> {
            Intent intent = new Intent(this, SearchListOrder.class);
            intent.putExtra("searchInput", searchInput.getText().toString());
            startActivity(intent);

        });

        searchClient.setOnClickListener(view -> {
            Intent intent = new Intent(this, SearchListClient.class);
            intent.putExtra("searchInput", searchInput.getText().toString());
            startActivity(intent);
        });
    }

    private void updateList() {
        mOrderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.orders_due_recycler);
        final OrderAdapter adapter = new OrderAdapter(orderData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        try {
            mOrderViewModel.getAllOrders().observe(this, orders -> {
                List<Order> dueOrders = new ArrayList<>();
                for (Order p : orders) {
                    if (p.getDateDue().compareTo(current.getTime()) >= 0 && p.getDateDue().compareTo(weekAway.getTime()) < 0) {
                        dueOrders.add(p);
                    }
                }
                adapter.setOrders(dueOrders);
                numOrders = dueOrders.size();
            });
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "No orders due within a week", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
