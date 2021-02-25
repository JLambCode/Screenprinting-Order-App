package com.example.customerordertracker.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Client;
import com.example.customerordertracker.Entities.Employee;
import com.example.customerordertracker.Entities.Item;
import com.example.customerordertracker.Entities.Order;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.ClientViewModel;
import com.example.customerordertracker.ViewModel.ItemViewModel;
import com.example.customerordertracker.ViewModel.OrderViewModel;
import com.example.customerordertracker.ui.ClientAdapter;
import com.example.customerordertracker.ui.EmployeeAdapter;
import com.example.customerordertracker.ui.ItemAdapter;
import com.example.customerordertracker.ui.OrderAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.customerordertracker.Utilities.Constants.FIND_ART_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.NEW_EMPLOYEE_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.NEW_ITEM_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.NEW_REF_ART_ACTIVITY_REQUEST_CODE;

public class NewOrder extends AppCompatActivity {

    private OrderViewModel mOrderViewModel;
    private ItemViewModel mItemViewModel;
    private ClientViewModel mClientViewModel;
    private EditText mName;
    private EditText mDueDate;
    private EditText mDateOrdered;
    private EditText mDescription;
    private EditText mReferenceArt;
    private EditText mAddress;
    private EditText mCity;
    private EditText mZip;
    private Spinner mState;
    private Spinner mDeliveryType;
    private TextView mTotalItems;
    private Date dateOrdered;
    private Date dueDate;
    private List<Item> itemData;
    private List<Order> orderData;
    private int numItems = 0;
    private int orderId;
    private int empId;
    private int clientId;
    private int orderCount;
    private String clientName;
    private DatePickerDialog dueDatePicker;
    private DatePickerDialog dateOrderedPicker;

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        clientId = getIntent().getIntExtra("clientId", 0);
        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        mClientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        clientName = getIntent().getStringExtra("clientName");
        setTitle("New Order for " + clientName);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy", Locale.US);

        orderId = getIntent().getIntExtra("newOrderId", 0);
        mDeliveryType = findViewById(R.id.spinnerDeliveryType);
        ArrayAdapter<CharSequence> deliveryAdapter = ArrayAdapter.createFromResource(this,R.array.delivery_type_array, android.R.layout.simple_spinner_item);
        deliveryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDeliveryType.setAdapter(deliveryAdapter);

        mState = findViewById(R.id.spinState);
        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this, R.array.state_array, android.R.layout.simple_spinner_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mState.setAdapter(stateAdapter);

        RecyclerView recyclerView = findViewById(R.id.item_list_recycler);
        final ItemAdapter itemAdapter = new ItemAdapter(itemData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);

        mItemViewModel.getAllItems().observe(this, items -> {
            List<Item> orderItems = new ArrayList<>();
            for(Item p:items){
                if(p.getOrderID()==orderId){
                    orderItems.add(p);
                }
            }
            itemAdapter.setItems(orderItems);
            numItems = items.size();
        });

        mName = findViewById(R.id.inputName);
        mDateOrdered = findViewById(R.id.inputDateOrdered);
        mDueDate = findViewById(R.id.inputDateDue);
        mDescription = findViewById(R.id.inputDescription);
        mReferenceArt = findViewById(R.id.inputReferenceArt);
        mAddress = findViewById(R.id.inputAddress);
        mCity = findViewById(R.id.inputCity);
        mState = findViewById(R.id.spinState);
        mZip = findViewById(R.id.inputZip);
        mTotalItems = findViewById(R.id.outputItemTotal);


        mItemViewModel.getAllItems().observe(this, items -> {
            int itemCount = 0;
            for (Item p:items) {
                if (p.getOrderID()==orderId) {
                    itemCount = itemCount + Integer.parseInt(p.getQuantity());
                }
            }
            mTotalItems.setText(String.valueOf(itemCount));
        });

        mDateOrdered.setInputType(InputType.TYPE_NULL);
        mDateOrdered.setOnClickListener(view -> {
            final Calendar cal1 = Calendar.getInstance();
            int day = cal1.get(Calendar.DAY_OF_MONTH);
            int month = cal1.get(Calendar.MONTH);
            int year = cal1.get(Calendar.YEAR);
            dateOrderedPicker = new DatePickerDialog(NewOrder.this, (view12, year12, monthOfYear, dayOfMonth) -> {
                mDateOrdered.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year12);
                try {
                    dateOrdered = dateFormat.parse(mDateOrdered.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }, year, month, day);
            dateOrderedPicker.show();
        });

        mDueDate.setInputType(InputType.TYPE_NULL);
        mDueDate.setOnClickListener(view -> {
            final Calendar cal1 = Calendar.getInstance();
            int day = cal1.get(Calendar.DAY_OF_MONTH);
            int month = cal1.get(Calendar.MONTH);
            int year = cal1.get(Calendar.YEAR);
            dueDatePicker = new DatePickerDialog(NewOrder.this, (view1, year1, monthOfYear, dayOfMonth) -> {
                mDueDate.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year1);
                try {
                    dueDate = dateFormat.parse(mDueDate.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }, year, month, day);
            dueDatePicker.show();
        });

        Button refArtBrowse = findViewById(R.id.btnBrowse);
        refArtBrowse.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/jpeg");
            startActivityForResult(intent, FIND_ART_ACTIVITY_REQUEST_CODE);
        });

        FloatingActionButton create = findViewById(R.id.btnAdd);
        create.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if(TextUtils.isEmpty(mName.getText()) ||
            TextUtils.isEmpty(mAddress.getText()) ||
            TextUtils.isEmpty(mCity.getText()) ||
            TextUtils.isEmpty(mDescription.getText()) ||
            TextUtils.isEmpty(mDueDate.getText()) ||
            TextUtils.isEmpty(mDateOrdered.getText()) ||
            TextUtils.isEmpty(mZip.getText())){
                Toast.makeText(getApplicationContext(), "One or more required fields are empty", Toast.LENGTH_SHORT).show();
            } else {

                replyIntent.putExtra("orderId", orderId);
                replyIntent.putExtra("orderName", mName.getText().toString());
                replyIntent.putExtra("orderDateOrdered", mDateOrdered.getText().toString());
                replyIntent.putExtra("orderDueDate", mDueDate.getText().toString());
                replyIntent.putExtra("orderDeliveryType", mDeliveryType.getSelectedItem().toString());
                replyIntent.putExtra("orderReferenceArt", mReferenceArt.getText().toString());
                replyIntent.putExtra("orderDescription", mDescription.getText().toString());
                replyIntent.putExtra("orderAddress", mAddress.getText().toString());
                replyIntent.putExtra("orderCity", mCity.getText().toString());
                replyIntent.putExtra("orderState", mState.getSelectedItem().toString());
                replyIntent.putExtra("orderStatePos", mState.getSelectedItemPosition());
                replyIntent.putExtra("orderZip", mZip.getText().toString());
                replyIntent.putExtra("orderClientId", clientId);

                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });

        FloatingActionButton cancel = findViewById(R.id.btnBack);
        cancel.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmation");
            builder.setMessage("Are you sure you want to stop creating this order?");
            builder.setCancelable(true);
            builder.setPositiveButton("Yes", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), "Order not created", Toast.LENGTH_SHORT).show();
                finish();
            });
            builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            parent.getItemAtPosition(pos);
        }
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void updateList() {
        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.item_list_recycler);
        final ItemAdapter itemAdapter = new ItemAdapter(itemData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();
        mItemViewModel.getAllItems().observe(this, items -> {
            List<Item> orderItems = new ArrayList<>();
            for(Item p:items){
                if(p.getOrderID()==orderId){
                    orderItems.add(p);
                }
            }
            itemAdapter.setItems(orderItems);
            numItems = items.size();
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent replyIntent) {
        super.onActivityResult(requestCode, resultCode, replyIntent);
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

        if(requestCode == FIND_ART_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            try {
                Uri uri = null;
                if (replyIntent != null) {
                    uri = replyIntent.getData();
                    mReferenceArt.setText(uri.getPath());
                }
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"File not found!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
