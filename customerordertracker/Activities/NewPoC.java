package com.example.customerordertracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Client;
import com.example.customerordertracker.Entities.POC;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.ClientViewModel;
import com.example.customerordertracker.ViewModel.POCViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class NewPoC extends AppCompatActivity {

    private POCViewModel mPOCViewModel;
    private ClientViewModel mClientViewModel;
    private EditText mFName;
    private EditText mLName;
    private EditText mPhone;
    private EditText mEmail;
    private int pocId;
    private int clientId;
    private int numPocs;
    private String clientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_poc);
        mClientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        mPOCViewModel = new ViewModelProvider(this).get(POCViewModel.class);
        clientId = getIntent().getIntExtra("clientId", 0);
        clientName = getIntent().getStringExtra("clientName");
        setTitle("Add Point of Contact for " + clientName);
        mFName = findViewById(R.id.inputFName);
        mLName = findViewById(R.id.inputLName);
        mPhone = findViewById(R.id.inputPhone);
        mEmail = findViewById(R.id.inputEmail);

        mPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());


        try {
            mPOCViewModel.getAllPOC().observe(this, pocs -> {
                List<POC> allPocs = new ArrayList<>();
                for (POC p : pocs) {
                    allPocs.add(p);
                }
                numPocs = allPocs.size();
            });
        }catch (Exception e){
            numPocs = 0;
        }

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });

        FloatingActionButton save = findViewById(R.id.btnSave);
        save.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mFName.getText()) ||
                    TextUtils.isEmpty(mLName.getText()) ||
                    TextUtils.isEmpty(mPhone.getText()) ||
                    TextUtils.isEmpty(mEmail.getText())) {
                Toast.makeText(getApplicationContext(), "One or more required fields are empty", Toast.LENGTH_LONG).show();
            }
            else {
                pocId = numPocs+1;
                String fName = mFName.getText().toString();
                String lName = mLName.getText().toString();
                String email = mEmail.getText().toString();
                String phone = mPhone.getText().toString();

                replyIntent.putExtra("pocId", pocId);
                replyIntent.putExtra("pocFName", fName);
                replyIntent.putExtra("pocLName", lName);
                replyIntent.putExtra("pocEmail", email);
                replyIntent.putExtra("pocPhone", phone);
                replyIntent.putExtra("pocClientId",clientId);

                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
