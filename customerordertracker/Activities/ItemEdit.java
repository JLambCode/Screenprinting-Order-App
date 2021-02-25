package com.example.customerordertracker.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Employee;
import com.example.customerordertracker.Entities.Item;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.ItemViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class ItemEdit extends AppCompatActivity {

    private ItemViewModel mItemViewModel;
    private EditText mName;
    private EditText mDescription;
    private EditText mQuantity;
    private Spinner mSize;
    private EditText mColor;
    private Spinner mVendor;
    private RadioButton mRadAdult;
    private RadioButton mRadYouth;
    private RadioButton mRadInk;
    private RadioButton mRadThread;
    private int mAorY;
    private int mApplication;
    private int itemId;
    private int orderId;
    private int vendorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_edit);
        setTitle("Item Edit");
        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        itemId = getIntent().getIntExtra("itemId", 0);
        orderId = getIntent().getIntExtra("orderId", 0);
        vendorId = getIntent().getIntExtra("vendorId", 0);
        mName = findViewById(R.id.inputName);
        mDescription = findViewById(R.id.inputDescription);
        mQuantity = findViewById(R.id.inputQuantity);
        mColor = findViewById(R.id.inputColor);
        mRadAdult = findViewById(R.id.radAdult);
        mRadYouth = findViewById(R.id.radYouth);
        mRadInk = findViewById(R.id.radInk);
        mRadThread = findViewById(R.id.radThread);
        mName.setText(getIntent().getStringExtra("itemName"));
        mDescription.setText(getIntent().getStringExtra("itemDescription"));
        mQuantity.setText(getIntent().getStringExtra("itemQuantity"));
        mColor.setText(getIntent().getStringExtra("itemColor"));
        mAorY = getIntent().getIntExtra("itemAorY", 0);
        mApplication = getIntent().getIntExtra("itemApplication", 0);

        if (mAorY == 1){
            mRadAdult.setChecked(true);
        } else {
            mRadYouth.setChecked(true);
        }

        if (mApplication == 1){
            mRadInk.setChecked(true);
        } else {
            mRadThread.setChecked(true);
        }

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
                String name = mName.getText().toString();
                String description = mDescription.getText().toString();
                String quantity = mQuantity.getText().toString();
                String size = mSize.getSelectedItem().toString();
                int vendorId = mVendor.getSelectedItemPosition()+1;
                String vendorName = mVendor.getSelectedItem().toString();
                String color = mColor.getText().toString();
                int AorY;
                String AorYString;
                if (mRadAdult.isChecked()) {
                    AorYString = "Adult";
                    AorY = 1;
                } else {
                    AorYString = "Youth";
                    AorY = 0;
                }
                int application;
                String applicationString;
                if (mRadInk.isChecked()) {
                    applicationString = "Ink";
                    application = 1;
                } else {
                    applicationString = "Thread";
                    application = 0;
                }
                int itemId = getIntent().getIntExtra("itemId", 0);
                replyIntent.putExtra("itemId", itemId);
                replyIntent.putExtra("itemName", name);
                replyIntent.putExtra("itemDescription", description);
                replyIntent.putExtra("itemQuantity", quantity);
                replyIntent.putExtra("itemSize", size);
                replyIntent.putExtra("itemColor", color);
                replyIntent.putExtra("orderId", orderId);
                replyIntent.putExtra("itemAorYString", AorYString);
                replyIntent.putExtra("itemAorY", AorY);
                replyIntent.putExtra("itemApplicationString", applicationString);
                replyIntent.putExtra("itemApplication", application);
                replyIntent.putExtra("vendorId", vendorId);
                replyIntent.putExtra("vendorName", vendorName);

                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });
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

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            parent.getItemAtPosition(pos);
        }
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
