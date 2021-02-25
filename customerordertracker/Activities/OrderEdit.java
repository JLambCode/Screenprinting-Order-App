package com.example.customerordertracker.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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

import com.example.customerordertracker.Entities.Employee;
import com.example.customerordertracker.Entities.Item;
import com.example.customerordertracker.Entities.Order;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.EmployeeViewModel;
import com.example.customerordertracker.ViewModel.ItemViewModel;
import com.example.customerordertracker.ViewModel.OrderViewModel;
import com.example.customerordertracker.ui.EmployeeAdapter;
import com.example.customerordertracker.ui.ItemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.customerordertracker.Utilities.Constants.EDIT_REF_ART_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.FIND_ART_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.NEW_EMPLOYEE_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.NEW_ITEM_ACTIVITY_REQUEST_CODE;

public class OrderEdit extends AppCompatActivity {

    private OrderViewModel mOrderViewModel;
    private ItemViewModel mItemViewModel;
    private EditText mName;
    private EditText mDueDate;
    private EditText mDescription;
    private EditText mReferenceArt;
    private TextView mTotalItems;
    private EditText mAddress;
    private EditText mCity;
    private Spinner mState;
    private EditText mZip;
    private Spinner mDeliveryType;
    private List<Item> itemData = new ArrayList<>();
    private Date dateOrdered;
    private Date dueDate;
    private int numItems;
    private int orderId;
    private int clientId;
    private int orderStatePos;
    private DatePickerDialog dueDatePicker;

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_edit);
        setTitle("Edit Order");
        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        clientId = getIntent().getIntExtra("orderClientId", 0);
        Calendar cal = Calendar.getInstance();
        dateOrdered = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy", Locale.US);

        mName = findViewById(R.id.inputName);
        mDueDate = findViewById(R.id.inputDateDue);
        mDescription = findViewById(R.id.inputDescription);
        mReferenceArt = findViewById(R.id.inputReferenceArt);
        mAddress = findViewById(R.id.inputAddress);
        mCity = findViewById(R.id.inputCity);
        mZip = findViewById(R.id.inputZip);
        mTotalItems = findViewById(R.id.outputItemTotal);

        mDeliveryType = findViewById(R.id.spinnerDeliveryType);
        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(this,R.array.delivery_type_array, android.R.layout.simple_spinner_item);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDeliveryType.setAdapter(spinAdapter);

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
        mName.setText(getIntent().getStringExtra("orderName"));
        mDueDate.setText(getIntent().getStringExtra("orderDueDate"));
        mDescription.setText(getIntent().getStringExtra("orderDescription"));
        mReferenceArt.setText(getIntent().getStringExtra("orderReferenceArt"));
        mAddress.setText(getIntent().getStringExtra("orderAddress"));
        mCity.setText(getIntent().getStringExtra("orderCity"));
        mZip.setText(getIntent().getStringExtra("orderZip"));

        mDueDate.setInputType(InputType.TYPE_NULL);
        mDueDate.setOnClickListener(view -> {
            final Calendar cal1 = Calendar.getInstance();
            int day = cal1.get(Calendar.DAY_OF_MONTH);
            int month = cal1.get(Calendar.MONTH);
            int year = cal1.get(Calendar.YEAR);
            dueDatePicker = new DatePickerDialog(OrderEdit.this, (view1, year1, monthOfYear, dayOfMonth) -> {
                mDueDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);
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

        Button viewArt = findViewById(R.id.btnArtInfo);
        viewArt.setOnClickListener(view -> {
            Intent intent = new Intent(this, ArtList.class);
            intent.putExtra("orderId", orderId);
            startActivity(intent);
        });



        FloatingActionButton save = findViewById(R.id.btnSave);
        save.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mName.getText()) ||
                    TextUtils.isEmpty(mDueDate.getText()) ||
                    TextUtils.isEmpty(mDescription.getText())) {
                Toast.makeText(getApplicationContext(), "One or more required fields are empty", Toast.LENGTH_SHORT).show();
            }
            else {
                replyIntent.putExtra("orderId", orderId);
                replyIntent.putExtra("orderName", mName.getText().toString());
                replyIntent.putExtra("orderDueDate", mDueDate.getText().toString());
                replyIntent.putExtra("orderDescription", mDescription.getText().toString());
                replyIntent.putExtra("orderDeliveryType", mDeliveryType.getSelectedItem().toString());
                replyIntent.putExtra("orderReferenceArt", mReferenceArt.getText().toString());
                replyIntent.putExtra("orderAddress", mAddress.getText().toString());
                replyIntent.putExtra("orderCity", mCity.getText().toString());
                replyIntent.putExtra("orderState", mState.getSelectedItem().toString());
                replyIntent.putExtra("orderZip", mZip.getText().toString());
                replyIntent.putExtra("orderClientId", clientId);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
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

    public void onActivityResult(int requestCode, int resultCode, Intent replyIntent) {
        super.onActivityResult(requestCode, resultCode, replyIntent);
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
