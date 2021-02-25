package com.example.customerordertracker.Activities;

import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Order;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.OrderViewModel;
import com.example.customerordertracker.ui.OrderAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Reports extends AppCompatActivity {

    private OrderViewModel mOrderViewModel;
    private Calendar current = Calendar.getInstance();
    private Calendar weekAway = Calendar.getInstance();
    private List<Order> orderData = new ArrayList<>();
    private CharSequence orderReport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        setTitle("Reports");
        mOrderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        current.setTime(Date.from(Instant.now()));
        weekAway.add(Calendar.DAY_OF_MONTH, 7);

        Button genReport = findViewById(R.id.btnDueOrderReport);
        genReport.setOnClickListener(view -> {
            mOrderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
            mOrderViewModel.getAllOrders().observe(this, orders -> {
                List<CharSequence> ordersList = new ArrayList<>();
                for (Order p : orders) {
                    if (p.getDateDue().compareTo(current.getTime()) >= 0 && p.getDateDue().compareTo(weekAway.getTime()) < 0) {
                        ordersList.add(p.toString());
                    }
                }
                Environment.getExternalStorageState();
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE}, 1);
                try{
                    File orderReports = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Order_Reports");
                    boolean isPathCreated = orderReports.exists();
                    if (!isPathCreated) {
                        isPathCreated = orderReports.mkdir();
                    }
                    File report = new File(orderReports,"OrdersDueReport.txt");
                    FileWriter writer = new FileWriter(report);
                    writer.write(ordersList.toString());
                    writer.flush();
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            });
        });

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
