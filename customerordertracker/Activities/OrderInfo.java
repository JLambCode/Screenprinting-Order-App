package com.example.customerordertracker.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Item;
import com.example.customerordertracker.Entities.Order;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.ItemViewModel;
import com.example.customerordertracker.ViewModel.OrderViewModel;
import com.example.customerordertracker.ui.ItemAdapter;
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

import static com.example.customerordertracker.Utilities.Constants.EDIT_ORDER_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.EDIT_SHIPPING_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.NEW_EMPLOYEE_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.NEW_ITEM_ACTIVITY_REQUEST_CODE;

public class OrderInfo extends AppCompatActivity {

    private OrderViewModel mOrderViewModel;
    private ItemViewModel mItemViewModel;
    private TextView mName;
    private TextView mDueDate;
    private TextView mDescription;
    private TextView mDeliveryType;
    private TextView mReferenceArt;
    private TextView mAddress;
    private TextView mCity;
    private TextView mState;
    private TextView mZip;
    private TextView mTotalItems;
    private List<Item> itemData;
    private int numItems;
    private int orderId;
    private int clientId;
    private List<Order> orderData = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        setTitle("Order Info");
        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        mOrderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        mName = findViewById(R.id.outputName);
        mDueDate = findViewById(R.id.outputDateDue);
        mDescription = findViewById(R.id.outputDescription);
        mDeliveryType = findViewById(R.id.outputDeliveryType);
        mReferenceArt = findViewById(R.id.outputReferenceArt);
        mAddress = findViewById(R.id.outputAddress);
        mCity = findViewById(R.id.outputCity);
        mState = findViewById(R.id.outputState);
        mZip = findViewById(R.id.outputZip);
        mTotalItems = findViewById(R.id.outputItemTotal);

        RecyclerView recyclerView = findViewById(R.id.item_list_recycler);
        final ItemAdapter itemAdapter = new ItemAdapter(itemData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);

        mItemViewModel.getAllItems().observe(this, items -> {
            List<Item> orderItems = new ArrayList<>();
            int itemCount = 0;
            for(Item p:items){
                if(p.getOrderID()==orderId){
                    orderItems.add(p);
                    itemCount = itemCount + Integer.parseInt(p.getQuantity());
                }
            }
            itemAdapter.setItems(orderItems);
            numItems = items.size();
            mTotalItems.setText(String.valueOf(itemCount));
        });

        orderId = getIntent().getIntExtra("orderId", 0);
        clientId = getIntent().getIntExtra("orderClientId", 0);
        mName.setText(getIntent().getStringExtra("orderName"));
        mDueDate.setText(getIntent().getStringExtra("orderDateDue"));
        mDescription.setText(getIntent().getStringExtra("orderDescription"));
        mDeliveryType.setText(getIntent().getStringExtra("orderDeliveryType"));
        mReferenceArt.setText(getIntent().getStringExtra("orderReferenceArt"));
        mAddress.setText(getIntent().getStringExtra("orderAddress"));
        mCity.setText(getIntent().getStringExtra("orderCity"));
        mState.setText(getIntent().getStringExtra("orderState"));
        mZip.setText(getIntent().getStringExtra("orderZip"));

        Button viewArt = findViewById(R.id.btnArtInfo);
        viewArt.setOnClickListener(view -> {
            Intent intent = new Intent(this, ArtList.class);
            String name = mName.getText().toString();
            intent.putExtra("orderId", orderId);
            intent.putExtra("orderName", name);
            startActivity(intent);
        });

        Button addItem = findViewById(R.id.btnAddItem);
        addItem.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewItem.class);
            intent.putExtra("orderId", orderId);
            startActivityForResult(intent, NEW_ITEM_ACTIVITY_REQUEST_CODE);
        });

        FloatingActionButton edit = findViewById(R.id.btnEdit);
        edit.setOnClickListener(view -> {
            Intent intent = new Intent(this, OrderEdit.class);
            intent.putExtra("orderId", orderId);
            intent.putExtra("orderName", mName.getText().toString());
            intent.putExtra("orderDueDate", mDueDate.getText().toString());
            intent.putExtra("orderDescription", mDescription.getText().toString());
            intent.putExtra("orderDeliveryType", mDeliveryType.getText().toString());
            intent.putExtra("orderReferenceArt", mReferenceArt.getText().toString());
            intent.putExtra("orderAddress", mAddress.getText().toString());
            intent.putExtra("orderCity", mCity.getText().toString());
            intent.putExtra("orderState", mState.getText().toString());
            intent.putExtra("orderZip", mZip.getText().toString());
            intent.putExtra("orderClientId", clientId);

            startActivityForResult(intent, EDIT_ORDER_ACTIVITY_REQUEST_CODE);
        });

        FloatingActionButton delete = findViewById(R.id.btnDelete);
        delete.setOnClickListener(view -> {
            mOrderViewModel.getAllOrders().observe(this, orders -> {
                for(Order order: orders){
                    if(order.getOrderID() == orderId){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Confirmation");
                        builder.setMessage("Are you sure you want to delete this order?");
                        builder.setCancelable(true);
                        builder.setPositiveButton("Delete", (dialog, which) -> {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                            builder1.setTitle("Confirmation");
                            builder1.setMessage("This operation is not reversible, are you still sure?");
                            builder1.setCancelable(true);
                            builder1.setPositiveButton("Delete", (dialog1, which1) -> {
                                mOrderViewModel.delete(order);
                                Toast.makeText(getApplicationContext(), "Order deleted", Toast.LENGTH_SHORT).show();
                                finish();
                            });
                            builder1.setNegativeButton("Cancel", (dialog1, which1) -> dialog1.dismiss());
                            AlertDialog dialog1 = builder1.create();
                            dialog1.show();
                        });
                        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            });
        });

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent replyIntent) {
        super.onActivityResult(requestCode, resultCode, replyIntent);
        if (requestCode == EDIT_ORDER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy", Locale.US);
                int orderId = replyIntent.getIntExtra("orderId",0);
                String name = replyIntent.getStringExtra("orderName");
                String dateDueString = replyIntent.getStringExtra("orderDueDate");
                String deliveryType = replyIntent.getStringExtra("orderDeliveryType");
                String referenceArt = replyIntent.getStringExtra("orderReferenceArt");
                String description = replyIntent.getStringExtra("orderDescription");
                String address = replyIntent.getStringExtra("orderAddress");
                String city = replyIntent.getStringExtra("orderCity");
                String state = replyIntent.getStringExtra("orderState");
                String zip = replyIntent.getStringExtra("orderZip");
                int clientId = replyIntent.getIntExtra("orderClientId", 0);

                Date dateDue = dateFormat.parse(dateDueString);
                Order updatedOrder = new Order(orderId, name, dateDue, deliveryType, referenceArt, description, address, city, state, zip, clientId);
                mOrderViewModel.update(updatedOrder);
                //final OrderAdapter adapter = new OrderAdapter(orderData, this);
                //adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Order successfully updated", Toast.LENGTH_SHORT).show();
            }
            catch (ParseException e){
                e.printStackTrace();
            }
        }

        if (requestCode == NEW_ITEM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            int itemId = replyIntent.getIntExtra("itemId",0);
            String name = replyIntent.getStringExtra("itemName");
            String description = replyIntent.getStringExtra("itemDescription");
            String quantity = replyIntent.getStringExtra("itemQuantity");
            String AorY = replyIntent.getStringExtra("itemAorY");
            String size = replyIntent.getStringExtra("itemSize");
            String application = replyIntent.getStringExtra("itemApplication");
            String color = replyIntent.getStringExtra("itemColor");
            int orderId = replyIntent.getIntExtra("itemOrderId",0);
            int vendorId = replyIntent.getIntExtra("itemVendorId",0);
            Item newItem = new Item(itemId, name, description, quantity, AorY, size, application, color, orderId, vendorId);
            mItemViewModel.insert(newItem);
            Toast.makeText(getApplicationContext(), "Item successfully added", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateList() {
        RecyclerView recyclerView = findViewById(R.id.item_list_recycler);
        final ItemAdapter itemAdapter = new ItemAdapter(itemData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();
        mItemViewModel.getAllItems().observe(this, items -> {
            List<Item> orderItems = new ArrayList<>();
            int itemCount = 0;
            for(Item p:items){
                if(p.getOrderID()==orderId){
                    orderItems.add(p);
                    itemCount = itemCount + Integer.parseInt(p.getQuantity());
                }
            }
            itemAdapter.setItems(orderItems);
            numItems = items.size();
            mTotalItems.setText(String.valueOf(itemCount));
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
