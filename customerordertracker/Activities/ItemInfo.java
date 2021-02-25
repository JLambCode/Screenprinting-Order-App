package com.example.customerordertracker.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Item;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.ItemViewModel;
import com.example.customerordertracker.ui.ItemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.lifecycle.ViewModelProvider;

import static com.example.customerordertracker.Utilities.Constants.EDIT_EMPLOYEE_ACTIVITY_REQUEST_CODE;
import static com.example.customerordertracker.Utilities.Constants.EDIT_ITEM_ACTIVITY_REQUEST_CODE;

public class ItemInfo extends AppCompatActivity {

    private ItemViewModel mItemViewModel;
    private TextView mName;
    private TextView mDescription;
    private TextView mQuantity;
    private TextView mSize;
    private TextView mColor;
    private TextView mVendor;
    private RadioButton mRadAdult;
    private RadioButton mRadYouth;
    private RadioButton mRadInk;
    private RadioButton mRadThread;
    private int mAorY;
    private int mApplication;
    private int itemId;
    private int orderId;
    private int vendorId;
    private List<Item> itemData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        setTitle("Item Info");
        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        itemId = getIntent().getIntExtra("itemId", 0);
        orderId = getIntent().getIntExtra("itemOrderId", 0);
        vendorId = getIntent().getIntExtra("vendorId", 0);
        mName = findViewById(R.id.outputName);
        mDescription = findViewById(R.id.outputDescription);
        mQuantity = findViewById(R.id.outputQuantity);
        mSize = findViewById(R.id.outputSize);
        mColor = findViewById(R.id.outputColor);
        mVendor = findViewById(R.id.outputVendor);
        mRadAdult = findViewById(R.id.radAdult);
        mRadYouth = findViewById(R.id.radYouth);
        mRadInk = findViewById(R.id.radInk);
        mRadThread = findViewById(R.id.radThread);

        ArrayAdapter<CharSequence> spinVendorAdapter = ArrayAdapter.createFromResource(this, R.array.vendor_array, android.R.layout.simple_spinner_item);
        spinVendorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mName.setText(getIntent().getStringExtra("itemName"));
        mDescription.setText(getIntent().getStringExtra("itemDescription"));
        mQuantity.setText(getIntent().getStringExtra("itemQuantity"));
        mSize.setText(getIntent().getStringExtra("itemSize"));
        mColor.setText(getIntent().getStringExtra("itemColor"));
        mVendor.setText(spinVendorAdapter.getItem(getIntent().getIntExtra("vendorId", 0)));


        if (Objects.requireNonNull(getIntent().getStringExtra("itemAorY")).matches("Adult")){
            mRadAdult.setChecked(true);
            mAorY = 1;
        } else {
            mRadYouth.setChecked(true);
            mAorY = 0;
        }

        if (Objects.requireNonNull(getIntent().getStringExtra("itemApplication")).matches("Ink")){
            mRadInk.setChecked(true);
            mApplication = 1;
        } else {
            mRadThread.setChecked(true);
            mApplication = 0;
        }
        mRadAdult.setClickable(false);
        mRadYouth.setClickable(false);
        mRadInk.setClickable(false);
        mRadThread.setClickable(false);

        FloatingActionButton delete = findViewById(R.id.btnDelete);
        delete.setOnClickListener(view -> {
            mItemViewModel.getAllItems().observe(this, items -> {
                for(Item p: items) {
                    if (p.getItemID() == itemId) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Confirmation");
                        builder.setMessage("Are you sure you want to delete this item?");
                        builder.setCancelable(true);
                        builder.setPositiveButton("Delete", (dialog, which) -> {
                            mItemViewModel.delete(p);
                            Toast.makeText(getApplicationContext(), "Item deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        });
                        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            });
        });

        FloatingActionButton edit = findViewById(R.id.btnEdit);
        edit.setOnClickListener(view -> {
            Intent intent = new Intent(this, ItemEdit.class);
            String name = mName.getText().toString();
            String description = mDescription.getText().toString();
            String quantity = mQuantity.getText().toString();
            String size = mSize.getText().toString();
            String color = mColor.getText().toString();
            int AorY = mAorY;
            int application = mApplication;
            int itemId = getIntent().getIntExtra("itemId", 0);
            intent.putExtra("itemId", itemId);
            intent.putExtra("itemName", name);
            intent.putExtra("itemDescription", description);
            intent.putExtra("itemQuantity", quantity);
            intent.putExtra("itemSize", size);
            intent.putExtra("itemColor", color);
            intent.putExtra("vendorId", vendorId);
            intent.putExtra("orderId", orderId);
            intent.putExtra("itemAorY", AorY);
            intent.putExtra("itemApplication", application);

            startActivityForResult(intent, EDIT_ITEM_ACTIVITY_REQUEST_CODE);
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

    public void onActivityResult(int requestCode, int resultCode, Intent replyIntent) {
        super.onActivityResult(requestCode, resultCode, replyIntent);
        if (requestCode == EDIT_ITEM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            int itemId = replyIntent.getIntExtra("itemId", 0);
            String name = replyIntent.getStringExtra("itemName");
            String description = replyIntent.getStringExtra("itemDescription");
            String color = replyIntent.getStringExtra("itemColor");
            String quantity = replyIntent.getStringExtra("itemQuantity");
            String size = replyIntent.getStringExtra("itemSize");
            String AorY = replyIntent.getStringExtra("itemAorYString");
            String application = replyIntent.getStringExtra("itemApplicationString");
            String vendor = replyIntent.getStringExtra("vendorName");
            int orderId = replyIntent.getIntExtra("orderId", 0);
            int vendorId = replyIntent.getIntExtra("vendorId", 0);

            mName.setText(name);
            mDescription.setText(description);
            mColor.setText(color);
            mQuantity.setText(quantity);
            mSize.setText(size);
            mVendor.setText(vendor);
            mAorY = replyIntent.getIntExtra("itemAorY", 0);
            mApplication = replyIntent.getIntExtra("itemApplication", 0);
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

            Item newItem = new Item(itemId, name, description, quantity, AorY, size, application, color, orderId, vendorId);
            mItemViewModel.update(newItem);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
