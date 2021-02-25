package com.example.customerordertracker.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Item;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.ItemViewModel;
import com.example.customerordertracker.ui.ItemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class NewItem extends AppCompatActivity {

    private ItemViewModel mItemViewModel;
    private EditText mName;
    private EditText mDescription;
    private EditText mQuantity;
    private Spinner mSize;
    private EditText mColor;
    private Spinner mVendor;
    private int itemId;
    private int vendorId;
    private int orderId;
    private int itemCount;
    private int mAorY;
    private int mApplication;
    private List<Item> itemData = new ArrayList<>();
    private RadioButton mRadAdult;
    private RadioButton mRadYouth;
    private RadioButton mRadInk;
    private RadioButton mRadThread;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        orderId = getIntent().getIntExtra("orderId", 0);
        setTitle("New Item for Order" + orderId);
        final ItemAdapter itemAdapter = new ItemAdapter(itemData, this);
        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        mItemViewModel.getAllItems().observe(this, items -> {
            List<Item> allItems = new ArrayList<>();
            for (Item p : items) {
                allItems.add(p);
            }
            itemAdapter.setItems(allItems);
            itemCount = allItems.size();
        });
        mName = findViewById(R.id.inputName);
        mDescription = findViewById(R.id.inputDescription);
        mQuantity = findViewById(R.id.inputQuantity);
        mColor = findViewById(R.id.inputColor);
        mRadAdult = findViewById(R.id.radAdult);
        mRadYouth = findViewById(R.id.radYouth);
        mRadInk = findViewById(R.id.radInk);
        mRadThread = findViewById(R.id.radThread);

        mRadAdult.setChecked(true);
        mRadInk.setChecked(true);

        mSize = findViewById(R.id.spinnerSize);
        ArrayAdapter<CharSequence> spinSizeAdapter = ArrayAdapter.createFromResource(this,R.array.sizes_array, android.R.layout.simple_spinner_item);
        spinSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSize.setAdapter(spinSizeAdapter);

        mVendor = findViewById(R.id.spinnerVendor);
        ArrayAdapter<CharSequence> spinVendorAdapter = ArrayAdapter.createFromResource(this, R.array.vendor_array, android.R.layout.simple_spinner_item);
        spinVendorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mVendor.setAdapter(spinVendorAdapter);

        FloatingActionButton save = findViewById(R.id.btnSave);
        save.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mName.getText()) ||
                    TextUtils.isEmpty(mDescription.getText()) ||
                    TextUtils.isEmpty(mQuantity.getText()) ||
                    TextUtils.isEmpty(mColor.getText())) {
                Toast.makeText(getApplicationContext(), "One or more required fields are empty", Toast.LENGTH_LONG).show();
            }
            else {
                replyIntent.putExtra("orderId", orderId);
                String name = mName.getText().toString();
                String description = mDescription.getText().toString();
                String quantity = mQuantity.getText().toString();
                String size = mSize.getSelectedItem().toString();
                int vendorId = mVendor.getSelectedItemPosition() + 1;
                String color = mColor.getText().toString();
                String AorY;
                if (mRadAdult.isChecked()) { AorY = "Adult"; }
                    else { AorY = "Youth"; }
                String application;
                if (mRadInk.isChecked()) { application = "Ink"; }
                    else { application = "Thread"; }
                int orderId = getIntent().getIntExtra("orderId", 0);

                replyIntent.putExtra("itemId", ++itemCount);
                replyIntent.putExtra("itemName", name);
                replyIntent.putExtra("itemDescription", description);
                replyIntent.putExtra("itemQuantity", quantity);
                replyIntent.putExtra("itemAorY", AorY);
                replyIntent.putExtra("itemSize", size);
                replyIntent.putExtra("itemApplication", application);
                replyIntent.putExtra("itemColor", color);
                replyIntent.putExtra("itemOrderId", orderId);
                replyIntent.putExtra("itemVendorId", vendorId);
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

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radAdult:
                if (checked)
                    mAorY = 1;
                break;
            case R.id.radYouth:
                if (checked)
                    mAorY = 0;
        }

        switch (view.getId()) {
            case R.id.radInk:
                if (checked)
                    mApplication = 1;
                break;
            case R.id.radThread:
                if (checked)
                    mApplication = 0;
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
