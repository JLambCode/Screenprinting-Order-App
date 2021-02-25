package com.example.customerordertracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Order;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.ClientViewModel;
import com.example.customerordertracker.ViewModel.OrderViewModel;
import com.example.customerordertracker.ui.OrderAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.customerordertracker.Utilities.Constants.NEW_ORDER_ACTIVITY_REQUEST_CODE;

public class ClientOrdersList extends AppCompatActivity {

    private OrderViewModel mOrderViewModel;
    private ClientViewModel mClientViewModel;
    private List<Order> orderData = new ArrayList<>();
    int clientId;
    int orderCount;
    private String mClientName;
    private int numOrders;

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_client_orders);
        clientId = getIntent().getIntExtra("clientId", 0);
        mClientName = getIntent().getStringExtra("clientName");
        mClientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);

        setTitle("Orders for " + mClientName);

        mOrderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.order_list_recycler);
        final OrderAdapter adapter = new OrderAdapter(orderData, this);
        recyclerView.setLayoutManager (new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        mOrderViewModel.getAllOrders().observe(this, orders -> {
            List<Order> clientOrders = new ArrayList<>();
            for (Order p : orders) {
                if (p.getClientID() == clientId) {
                    clientOrders.add(p);
                }
            }
            adapter.setOrders(clientOrders);
        });

        FloatingActionButton newOrder = findViewById(R.id.btnAdd);
        newOrder.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewOrder.class);
            intent.putExtra("clientId", clientId);
            intent.putExtra("clientName",mClientName);
            mOrderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
            final OrderAdapter orderAdapter = new OrderAdapter(orderData, this);
            mOrderViewModel.getAllOrders().observe(this, orders -> {
                List<Order> allOrders = new ArrayList<>();
                for (Order p : orders) {
                    allOrders.add(p);
                }
                orderAdapter.setOrders(allOrders);
                orderCount = allOrders.size();
            });
            int newOrderId = orderCount+1;
            intent.putExtra("newOrderId", newOrderId);
            startActivityForResult(intent, NEW_ORDER_ACTIVITY_REQUEST_CODE);
        });

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
        mOrderViewModel.getAllOrders().observe(this, orders -> {
            List<Order> clientOrders = new ArrayList<>();
            for (Order p : orders) {
                if (p.getClientID() == clientId) {
                    clientOrders.add(p);
                }
            }
            adapter.setOrders(clientOrders);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent replyIntent) {
        super.onActivityResult(requestCode, resultCode, replyIntent);
        if (requestCode == NEW_ORDER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy", Locale.US);
                int orderId = replyIntent.getIntExtra("orderId",0);
                String name = replyIntent.getStringExtra("orderName");
                String dateOrderedString = replyIntent.getStringExtra("orderDateOrdered");
                String dateDueString = replyIntent.getStringExtra("orderDueDate");
                String deliveryType = replyIntent.getStringExtra("orderDeliveryType");
                String referenceArt = replyIntent.getStringExtra("orderReferenceArt");
                String description = replyIntent.getStringExtra("orderDescription");
                String address = replyIntent.getStringExtra("orderAddress");
                String city = replyIntent.getStringExtra("orderCity");
                String state = replyIntent.getStringExtra("orderState");
                String zip = replyIntent.getStringExtra("orderZip");
                int clientId = replyIntent.getIntExtra("orderClientId", 0);

                Date dateOrdered = dateFormat.parse(dateOrderedString);
                Date dateDue = dateFormat.parse(dateDueString);
                Order newOrder = new Order(orderId, name, dateOrdered, dateDue, deliveryType, referenceArt, description, address, city, state, zip, clientId);
                mOrderViewModel.insert(newOrder);
                Toast.makeText(getApplicationContext(), "Order created", Toast.LENGTH_SHORT).show();
            }
            catch (ParseException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
