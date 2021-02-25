package com.example.customerordertracker.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.customerordertracker.Entities.Employee;
import com.example.customerordertracker.Entities.POC;
import com.example.customerordertracker.R;
import com.example.customerordertracker.ViewModel.POCViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class PoCEdit extends AppCompatActivity {

    private POCViewModel mPOCViewModel;
    private EditText mFName;
    private EditText mLName;
    private EditText mPhone;
    private EditText mEmail;
    private int clientId;
    private int pocId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poc_edit);
        setTitle("Edit Point of Contact");
        mPOCViewModel = new ViewModelProvider(this).get(POCViewModel.class);
        mFName = findViewById(R.id.inputFName);
        mLName = findViewById(R.id.inputLName);
        mPhone = findViewById(R.id.inputPhone);
        mEmail = findViewById(R.id.inputEmail);
        pocId = getIntent().getIntExtra("pocId",0);
        clientId = getIntent().getIntExtra("pocClientId", 0);
        mFName.setText(getIntent().getStringExtra("pocFName"));
        mLName.setText(getIntent().getStringExtra("pocLName"));
        mPhone.setText(getIntent().getStringExtra("pocPhone"));
        mEmail.setText(getIntent().getStringExtra("pocEmail"));

        mPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        FloatingActionButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(view -> {
            finish();
        });

        FloatingActionButton save = findViewById(R.id.btnSave);
        save.setOnClickListener(view -> {
            Intent replyIntent = new Intent(this, ClientInfo.class);
            if (TextUtils.isEmpty(mFName.getText()) ||
                    TextUtils.isEmpty(mLName.getText()) ||
                    TextUtils.isEmpty(mPhone.getText()) ||
                    TextUtils.isEmpty(mEmail.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
                Toast.makeText(getApplicationContext(), "One or more required fields are empty", Toast.LENGTH_LONG).show();
            }
            else {
                String fName = mFName.getText().toString();
                String lName = mLName.getText().toString();
                String email = mEmail.getText().toString();
                String phone = mPhone.getText().toString();

                replyIntent.putExtra("pocId", pocId);
                replyIntent.putExtra("pocClientId", clientId);
                replyIntent.putExtra("pocFName", fName);
                replyIntent.putExtra("pocLName", lName);
                replyIntent.putExtra("pocEmail", email);
                replyIntent.putExtra("pocPhone", phone);

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
